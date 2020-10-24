package com.daydreamapplications.sample

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.bindingrecycler.BindingRecyclerView
import com.daydreamapplications.sample.databinding.ActivityAddRemoveBinding
import kotlin.random.Random

/**
 * Sample activity to demonstrate randomly adding/removing items from a list.
 *
 * Activity consists of a RecyclerView with a list of numbers [0..9] in ascending order and two buttons that modify the contents of the recycler.
 *
 * Add Item: adds a item to the list. Random number [0..9].
 * Remove Item: removes a random item from the list.
 */
class DiffUtilAddRemoveRandomActivity : AppCompatActivity() {

    private val adapter = Adapter()
    var random: Random = Random(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.diff_util_add_remove_random)

        adapter.viewModels = listOf("0", "1", "2", "3")

        ActivityAddRemoveBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter

            // Add random item
            buttonAddItem.setOnClickListener {
                val newItem = random.nextInt(10).toString()
                val currentItems = adapter.viewModels

                adapter.viewModels = (currentItems + newItem).sorted()
            }

            // Remove random item
            buttonRemoveItem.setOnClickListener {
                val currentItems = adapter.viewModels

                if (currentItems.isNotEmpty()) {
                    val itemToRemove = currentItems[random.nextInt(currentItems.lastIndex)]
                    adapter.viewModels = currentItems.minus(itemToRemove)
                }
            }
        }
    }

    class Adapter : BindingRecyclerView.Adapter<String>() {

        @LayoutRes
        override fun getItemLayoutRes(position: Int): Int = R.layout.item_string
    }
}