package com.daydreamapplications.sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.bindingrecycler.BindingRecyclerView
import com.daydreamapplications.sample.databinding.ActivitySampleBinding

class SortingActivity : AppCompatActivity() {

    private val adapter = Adapter()

    private val items: List<String> = listOf(
        "Hello",
        "World",
        "What",
        "A",
        "Nice",
        "Day"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.sorting)

        adapter.viewModels = items

        ActivitySampleBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val newList = when (item.itemId) {
            R.id.sort_alphabetical -> items.sorted()
            R.id.sort_ordinal -> items
            R.id.sort_reverse_alphabetical -> items.sortedDescending()
            R.id.sort_reverse_ordinal -> items.reversed()
            else -> emptyList()
        }

        adapter.viewModels = newList // if useDiff = true items will animate to new positions

        return super.onOptionsItemSelected(item)
    }

    class Adapter : BindingRecyclerView.Adapter<String>() {

        @LayoutRes
        override fun getItemLayoutRes(position: Int): Int = R.layout.item_string
    }
}