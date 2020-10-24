package com.daydreamapplications.sample

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.bindingrecycler.BindingRecyclerView
import com.daydreamapplications.sample.databinding.ActivityAddRemoveBinding

class DiffUtilAddRemoveActivity : AppCompatActivity() {

    private val adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.diff_util_add_remove)

        adapter.viewModels = listOf("0", "1", "2", "3")

        ActivityAddRemoveBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter

            // Add new item to end of list
            buttonAddItem.setOnClickListener {
                val currentItems = adapter.viewModels
                val newItem = currentItems.size.toString()
                adapter.viewModels = currentItems + newItem
            }

            // Remove last item in list
            buttonRemoveItem.setOnClickListener {
                val currentItems = adapter.viewModels

                if (currentItems.isNotEmpty()) {
                    adapter.viewModels = currentItems.subList(0, currentItems.lastIndex)
                }
            }
        }
    }

    class Adapter : BindingRecyclerView.Adapter<String>() {

        @LayoutRes
        override fun getItemLayoutRes(position: Int): Int = R.layout.item_string
    }
}