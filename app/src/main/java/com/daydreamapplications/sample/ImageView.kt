package com.daydreamapplications.sample

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun bindImageSrc(imageView: ImageView, @DrawableRes res: Int?) {
    res ?: return
    imageView.setImageResource(res)
}