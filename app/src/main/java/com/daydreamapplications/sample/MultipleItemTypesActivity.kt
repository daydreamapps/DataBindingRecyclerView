package com.daydreamapplications.sample

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.bindingrecycler.BindingRecyclerView
import com.daydreamapplications.sample.databinding.ActivitySampleSingleItemTypeBinding

class MultipleItemTypesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.multiple_item_types)

        val adapter = Adapter()
        adapter.viewModels = listOf(
            Models.TitleWithIcon("Hello", R.drawable.ic_launcher_foreground),
            Models.Title("World")
        )

        ActivitySampleSingleItemTypeBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter
        }
    }

    class Adapter : BindingRecyclerView.Adapter<Models>() {

        @LayoutRes
        override fun getItemLayoutRes(position: Int): Int {
            return when (viewModels[position]) {
                is Models.Title -> R.layout.item_title
                is Models.TitleWithIcon -> R.layout.item_title_with_icon
            }
        }
    }

    sealed class Models {
        data class Title(val title: String) : Models()
        data class TitleWithIcon(val title: String, @DrawableRes val icon: Int) : Models()
    }
}