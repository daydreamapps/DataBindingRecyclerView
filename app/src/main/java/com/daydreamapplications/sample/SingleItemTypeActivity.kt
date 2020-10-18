package com.daydreamapplications.sample

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.bindingrecycler.BindingRecyclerView
import com.daydreamapplications.sample.databinding.ActivitySampleBinding

class SingleItemTypeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.single_item_type)

        val adapter = Adapter()
        adapter.viewModels = listOf("Hello", "World")

        ActivitySampleBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter
        }
    }

    class Adapter : BindingRecyclerView.Adapter<String>() {

        @LayoutRes
        override fun getItemLayoutRes(position: Int): Int = R.layout.item_string
    }
}