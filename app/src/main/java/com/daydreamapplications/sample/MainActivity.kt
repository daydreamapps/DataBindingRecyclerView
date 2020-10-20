package com.daydreamapplications.sample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.daydreamapplications.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityMainBinding.inflate(layoutInflater).apply {
            setContentView(root)

            buttonSingleItemType.setOnClickListener {
                startActivity(Intent(this@MainActivity, SingleItemTypeActivity::class.java))
            }

            buttonSingleItemTypeAsync.setOnClickListener {
                startActivity(Intent(this@MainActivity, SingleItemTypeAsyncActivity::class.java))
            }

            buttonMultipleItemTypes.setOnClickListener {
                startActivity(Intent(this@MainActivity, MultipleItemTypesActivity::class.java))
            }

            buttonSorting.setOnClickListener {
                startActivity(Intent(this@MainActivity, SortingActivity::class.java))
            }
        }
    }
}