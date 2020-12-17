# DataBindingRecyclerView

Simple RecyclerView & Adapter for use in projects using DataBinding.

[ ![Download](https://api.bintray.com/packages/daydreamapplications/Common/DataBinding-RecyclerView/images/download.svg) ](https://bintray.com/daydreamapplications/Common/DataBinding-RecyclerView/_latestVersion) [![Build Status](http://141.0.158.139:8080/buildStatus/icon?job=DataBinding+RecyclerView%2Fmaster)](http://141.0.158.139:8080/job/DataBinding%20RecyclerView/job/master/)

# Introduction
Andorid RecyclerViews do not need to be complex.
DataBindingRecyclerView provides a simple solution to common implementation scenarios, with no boiler-plate code.

# Key Features
 - No custom ViewHolders - Adapter of type ViewModel
 - no getItemViewType - use item layout instead
 - LifecycleOwner passed into item ViewDataBinding
 - click listener for all items
 - automatic DiffUtil - animate compostional/ordering changer
 - pre-set LayoutManager - no more frustration
 - set divider from drawable in layout

## Use

Add `BindingRecyclerView` to layout of Activity/Fragment.
```xml
    <com.daydreamapplications.bindingrecycler.BindingRecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

Create ViewModel for Item
```kotlin
data class ItemViewModel(@StringRes val title: Int)
```


Create item layout layout
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">
    <data>
        <variable
            name="viewModel"
            type=".project.ViewModel" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{viewModel.title}" />

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
```

Create Adapter to link ViewModel to Layout.

```kotlin
class Adapter : BindingRecyclerView.Adapter<ViewModel>() {

    @LayoutRes
    override fun getItemLayoutRes(position: Int): Int = R.layout.item
}
```

Assign adapter to RecyclerView from code/layout
```kotlin
    recycler_view.adapter = Adapter()
```
```xml
    <com.daydreamapplications.bindingrecycler.BindingRecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:adapter="@{adapter}"/>
```
