package com.example.fpr

import android.widget.ImageView
import com.bumptech.glide.Glide
import java.text.NumberFormat

fun languagesToString(languages: List<Language>): String {
    return languages.joinToString { it.name }
}

fun formatNumber(number: Long): String {
    return NumberFormat.getInstance().format(number)
}

fun downloadFlag(activity: MainActivity, url: String, imageView: ImageView) {
    Glide.with(activity).load(url).into(imageView)
}