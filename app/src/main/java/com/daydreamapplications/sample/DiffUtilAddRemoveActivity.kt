package com.daydreamapplications.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.sample.databinding.ActivityAddRemoveBinding

class DiffUtilAddRemoveActivity : AppCompatActivity() {

    private val adapter = MultiTypeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.diff_util_add_remove)

        adapter.viewModels = listOf(
            MultiTypeAdapter.Models.Text("0"),
            MultiTypeAdapter.Models.Text("1"),
            MultiTypeAdapter.Models.Text("2"),
            MultiTypeAdapter.Models.Text("3")
        )

        ActivityAddRemoveBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter

            // Add new item to end of list
            buttonAddItem.setOnClickListener {
                val currentItems = adapter.viewModels
                val newItemText = currentItems.size.toString()
                adapter.viewModels = currentItems + MultiTypeAdapter.Models.Text(newItemText)
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
}