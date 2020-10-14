package com.daydreamapplications.bindingrecycler

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BindingRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        layoutManager = LinearLayoutManager(context)
    }


    abstract class Adapter<VM> : LifecycleRecyclerAdapter<ViewHolder>(),
        LifecycleOwner,
        LifecycleObserver {

        override fun getLifecycle(): Lifecycle = lifecycleRegistry

        /**
         * Switch value to determine how updates are dispatched upon setViewModels being called
         *
         *
         * If true: DiffUtil is used to dispatch precise changes in data composition.
         * If false: notifyDataSetChanged is invoked.
         *
         * Note:
         * When set to true, multiple rapid calls to setViewModels can result in unusual behaviour as diffUtil dispatches interfer with one another.
         * If this applies, set to false, or use custom RecyclerView.Adapter for this scenario.
         *
         *
         * @see viewModels
         * @see notifyDataSetChanged
         */
        var useDiff: Boolean = true

        var viewModels: List<VM> = ArrayList()
            set(models) {

                if (useDiff) {
                    val callback = DiffCallback(models, field)
                    field = models
                    DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
                } else {
                    field = models
                    notifyDataSetChanged()
                }
            }


        /**
         * Higher-order function invoked on item click, can be set from outside class
         *
         * e.g.
         * class Foo(val adapter: Adapter<VM>) {
         *
         *     init {
         *         adapter.onClick = {view, vm -> /* DO SOMETHING */}
         *     }
         * }
         *
         * Invoked by function Adapter.onClick (if not overridden)
         */
        var doOnClick: (View, VM) -> Unit = { _, _ -> }


        /**
         * Called on recycler item click.
         *
         * onBindViewHolder calls `setOnClickListener` on root view of item.
         *
         * By default delegates to doOnClick
         */
        open fun onClick(rootView: View, viewModel: VM) {
            doOnClick.invoke(rootView, viewModel)
        }

        /**
         * Creates viewHolder using view inflated from
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding: ViewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                viewType,
                parent,
                false
            )

            return ViewHolder(binding)
        }

        /**
         * Return layoutRes id for view model in position
         *
         * Layout MUST use databinding, have generated ViewDataBinding file and include variable `viewModel` of type matching item in position.
         *
         * e.g.
         * <layout>
         *     <data>
         *
         *     <variable
         *         name="viewModel"
         *         type="VM" />
         *     </data>
         *
         *     // layout content
         *
         * </layout>
         */
        @LayoutRes
        abstract fun getItemLayoutRes(position: Int): Int

        /**
         * Default implementation overidden to delegate to getItemLayoutRes, required in onCreateViewHolder
         *
         * Do not override.
         * @see getItemLayoutRes
         */
        @Deprecated(message = "Do not override. Override Adapter.getItemLayoutRes(position) instead")
        override fun getItemViewType(position: Int): Int = getItemLayoutRes(position)

        /**
         * Default implementation overidden to delegate to getItemLayoutRes, required in onCreateViewHolder
         *
         * Do not override.
         * @see getItemLayoutRes
         */
        override fun getItemCount(): Int = viewModels.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val viewModel = viewModels[position]

            holder.bind(viewModel)
            holder.dataBinding.root.setOnClickListener { view -> onClick(view, viewModel) }
            holder.dataBinding.lifecycleOwner = this
        }
    }

    class ViewHolder(val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {

        fun <T> bind(viewModel: T) {
            dataBinding.setVariable(BR.viewModel, viewModel)
            dataBinding.executePendingBindings()
        }
    }


    fun addDivider(drawable: Drawable) {
        val divider = DividerItemDecoration(context, VERTICAL).apply {
            setDrawable(drawable)
        }
        addItemDecoration(divider)
    }

    companion object {

        @Suppress("UNCHECKED_CAST")
        @JvmStatic
        @BindingAdapter("viewModels")
        fun <T : Any> setRecyclerItems(lifecycleRecyclerView: BindingRecyclerView, items: List<T>?) {
            (lifecycleRecyclerView.adapter as? Adapter<T>)?.viewModels = items ?: emptyList()
        }

        @JvmStatic
        @BindingAdapter("lifecycleOwner")
        fun observeLifecycle(recyclerView: RecyclerView, lifecycleOwner: LifecycleOwner?) {
            val adapter = (recyclerView.adapter as? Adapter<*>) ?: return
            lifecycleOwner?.lifecycle?.addObserver(adapter)
        }

        @JvmStatic
        @BindingAdapter("dividerDrawable")
        fun <T : Any> setDivider(lifecycleRecyclerView: BindingRecyclerView, drawable: Drawable?) {
            if (drawable != null) {
                lifecycleRecyclerView.addDivider(drawable)
            }
        }
    }
}