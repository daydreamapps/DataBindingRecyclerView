package com.daydreamapplications.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.sample.MultiTypeAdapter.Models
import com.daydreamapplications.sample.databinding.ActivitySampleBinding

class MultipleItemTypesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.multiple_item_types)

        val adapter = MultiTypeAdapter(
            viewModels = listOf(
                Models.TextWithImage("Hello", R.drawable.ic_outline_face_24),
                Models.Text("World"),
                Models.Text("Here"),
                Models.Text("Is"),
                Models.Text("A"),
                Models.TextWithImage("Bike", R.drawable.ic_baseline_two_wheeler_24),
                Models.LargeImage(R.drawable.ic_baseline_remove_red_eye_24)
            )
        )

        ActivitySampleBinding.inflate(layoutInflater).apply {
            setContentView(root)
            recyclerView.adapter = adapter
        }
    }
}