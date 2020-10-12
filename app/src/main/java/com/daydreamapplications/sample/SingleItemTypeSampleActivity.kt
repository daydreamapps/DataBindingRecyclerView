package com.daydreamapplications.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.sample.databinding.ActivitySampleSingleItemTypeBinding

class SingleItemTypeSampleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivitySampleSingleItemTypeBinding.inflate(layoutInflater).apply {
            setContentView(root)
        }
    }
}