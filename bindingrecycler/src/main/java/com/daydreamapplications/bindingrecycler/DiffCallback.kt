package com.daydreamapplications.bindingrecycler

import androidx.recyclerview.widget.DiffUtil

internal class DiffCallback<T>(
    private val new: List<T>,
    private val old: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = old.size
    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = old[oldItemPosition] === new[newItemPosition]
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = old[oldItemPosition] == new[newItemPosition]
}