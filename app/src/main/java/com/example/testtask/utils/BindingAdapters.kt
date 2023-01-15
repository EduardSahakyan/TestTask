package com.example.testtask.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


@BindingAdapter("iconUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    Picasso.get()
        .load(imageUrl)
        .placeholder(com.google.android.material.R.drawable.ic_m3_chip_close)
        .error(com.google.android.material.R.drawable.ic_m3_chip_close)
        .into(view)
}