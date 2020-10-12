package com.daydreamapplications.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.sample.databinding.ActivitySampleBinding

class SampleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivitySampleBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }
}