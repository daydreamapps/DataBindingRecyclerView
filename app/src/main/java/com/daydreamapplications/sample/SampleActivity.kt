package com.daydreamapplications.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.sample.databinding.ActivityMainBinding

class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            buttonSingleItemType.setOnClickListener {
                startActivity(Intent(this@SampleActivity, SingleItemTypeActivity::class.java))
            }

            buttonMultipleItemTypes.setOnClickListener {
                startActivity(Intent(this@SampleActivity, MultipleItemTypesActivity::class.java))
            }
        }
    }
}