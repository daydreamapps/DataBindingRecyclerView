package com.daydreamapplications.sample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.sample.databinding.ActivitySampleBinding

class DiffUtilSortActivity : AppCompatActivity() {

    private val adapter = SimpleStringAdapter()

    private val items: List<String> = listOf("Hello", "World", "What", "A", "Nice", "Day")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setTitle(R.string.diff_util_sorting)
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
}