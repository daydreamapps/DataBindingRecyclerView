package com.daydreamapplications.sample

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.bindingrecycler.BindingRecyclerView
import com.daydreamapplications.sample.databinding.ActivityAddRemoveBinding
import kotlin.random.Random

class DiffUtilAddRemoveActivity : AppCompatActivity() {

    private val adapter = Adapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.diff_util_add_remove)

        adapter.viewModels = listOf("0", "1", "2", "3")

        ActivityAddRemoveBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter

            buttonAddItem.setOnClickListener {
                val current = adapter.viewModels
                adapter.viewModels = (current + Random.nextInt(10).toString()).sorted()
//                adapter.viewModels = current + (current.size).toString()
            }

            buttonRemoveItem.setOnClickListener {
                val current = adapter.viewModels
                if (current.isNotEmpty()) {
//                    adapter.viewModels = current.toMutableList().minusElement(current[Random.nextInt(current.lastIndex)])
                    adapter.viewModels = current.minus(current[Random.nextInt(current.lastIndex)])
                }
            }
        }
    }

    class Adapter : BindingRecyclerView.Adapter<String>() {

        @LayoutRes
        override fun getItemLayoutRes(position: Int): Int = R.layout.item_string
    }
}