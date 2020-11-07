package com.daydreamapplications.sample

import androidx.annotation.LayoutRes
import com.daydreamapplications.bindingrecycler.BindingRecyclerView

class SimpleStringAdapter(viewModels: Collection<String> = emptyList()) : BindingRecyclerView.Adapter<String>(viewModels) {

    @LayoutRes
    override fun getItemLayoutRes(position: Int): Int = R.layout.item_string
}