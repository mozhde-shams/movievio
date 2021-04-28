package com.example.movievio.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("posterPath")
fun ImageView.setImageUrl(url: String?) {
    Picasso.get().load(url).into(this)
}
