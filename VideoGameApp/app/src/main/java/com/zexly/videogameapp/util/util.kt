package com.zexly.videogameapp.util

import android.content.Context
import android.os.Handler
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zexly.videogameapp.R

fun ImageView.gorselIndir(url:String?, placeholder: CircularProgressDrawable){
    val option= RequestOptions().placeholder(placeholder).error(R.drawable.ic_launcher_background)
    Glide.with(context).setDefaultRequestOptions(option).load(url).into(this)
}

fun placeholderYap(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }

}