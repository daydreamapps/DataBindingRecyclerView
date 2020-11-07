package com.daydreamapplications.sample

import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import com.daydreamapplications.bindingrecycler.BindingRecyclerView

class MultiTypeAdapter(viewModels: Collection<Models> = emptyList()) : BindingRecyclerView.Adapter<MultiTypeAdapter.Models>(viewModels) {

    @LayoutRes
    override fun getItemLayoutRes(position: Int): Int {
        return when (viewModels[position]) {
            is Models.Text -> R.layout.item_text
            is Models.TextWithImage -> R.layout.item_text_with_image
            is Models.LargeImage -> R.layout.large_image
        }
    }

    sealed class Models {
        data class Text(val title: String) : Models()
        data class TextWithImage(val title: String, @DrawableRes val icon: Int) : Models()
        data class LargeImage(@DrawableRes val image: Int) : Models()
    }
}