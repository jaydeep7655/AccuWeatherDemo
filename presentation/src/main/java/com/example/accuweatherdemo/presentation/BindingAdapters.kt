package com.example.accuweatherdemo.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:image_url")
    fun setImage(view: ImageView, url: String) {
        if (!url.isNullOrEmpty()) {
            Glide.with(view.context).load(url)
                .into(view)
        }
    }
}