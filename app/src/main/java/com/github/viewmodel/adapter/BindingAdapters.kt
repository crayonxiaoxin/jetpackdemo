package com.github.viewmodel.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.github.viewmodel.R

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    imageUrl?.let {
        Glide.with(view.context)
            .load(imageUrl)
//            .placeholder(R.mipmap.ic_launcher)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}