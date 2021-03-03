package com.pedroluis.fastshoptest.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.pedroluis.fastshoptest.BuildConfig
import com.pedroluis.fastshoptest.R

fun ImageView.loadImage(path: String?) {
    if (path != null) {
        Glide.with(context).load(BuildConfig.IMAGE_URL + path)
            .into(this)
    } else {
        this.setImageResource(R.drawable.ic_baseline_local_movies_100)
    }
}