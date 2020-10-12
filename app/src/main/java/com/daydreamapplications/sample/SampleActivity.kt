package com.daydreamapplications.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.sample.databinding.ActivitySampleBinding

class SampleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivitySampleBinding.inflate(layoutInflater).apply {
            setContentView(root)

            buttonSingleItemType.setOnClickListener {
                startActivity(Intent(this@SampleActivity, SingleItemTypeSampleActivity::class.java))
            }
        }
    }
}