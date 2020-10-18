package com.daydreamapplications.sample

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

/**
 * Required as android:src is interpreting viewModel.icon as ColorRes instead of DrawableRes (Reason unknown)
 *
 * see app/main/res/layout/item_title_with_icon.xml
 */
@BindingAdapter("android:src")
fun bindImageSrc(imageView: ImageView, @DrawableRes res: Int?) {
    res ?: return
    imageView.setImageResource(res)
}