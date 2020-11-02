package com.daydreamapplications.sample

import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.bindingrecycler.BindingRecyclerView
import com.daydreamapplications.sample.MultiTypeAdapter.*
import com.daydreamapplications.sample.databinding.ActivitySampleBinding

class MultipleItemTypesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.multiple_item_types)

        val adapter = MultiTypeAdapter(
            viewModels = listOf(
                Models.TextWithImage("Hello", R.drawable.circle),
                Models.Text("World")
            )
        )

        ActivitySampleBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter
        }
    }
}