package com.daydreamapplications.sample

import android.os.Bundle
import android.os.Handler
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daydreamapplications.bindingrecycler.BindingRecyclerView
import com.daydreamapplications.sample.databinding.ActivitySampleBinding

class SingleItemTypeAsyncActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.single_item_type_async)

        val adapter = Adapter()
        lifecycle.addObserver(adapter) // Data will not update without this line!!
        adapter.viewModels = listOf(
            LoopingTitleItem(0.5, "Loading...", "Loaded"),
            LoopingTitleItem(1, "Hello", "World"),
            LoopingTitleItem(2, "What", "a", "Nice", "Day")
        )

        ActivitySampleBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter
        }
    }

    class Adapter : BindingRecyclerView.Adapter<LoopingTitleItem>() {

        @LayoutRes
        override fun getItemLayoutRes(position: Int): Int = R.layout.item_looping_title
    }

    /**
     * ViewModel for item. Title String updates at given frequency
     *
     * @param frequency of title loop in Hz
     * @param titles title strings to display
     */
    class LoopingTitleItem(
        private val frequency: Number,
        vararg titles: String
    ) {

        val loopingTitle: LiveData<String> by lazy {
            MutableLiveData<String>().apply {
                val period = (1000 / frequency.toDouble()).toLong()
                var index = 0

                val handler = Handler()
                val runnable: Runnable = object : Runnable {
                    override fun run() {
                        postValue(titles[index])
                        index = (index + 1) % titles.size
                        handler.postDelayed(this, period)
                    }
                }

                handler.post(runnable)
            }
        }
    }
}