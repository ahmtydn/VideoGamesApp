package com.zexly.videogameapp.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.zexly.videogameapp.R
import com.zexly.videogameapp.model.Result
import com.zexly.videogameapp.util.gorselIndir
import com.zexly.videogameapp.util.placeholderYap
import kotlinx.android.synthetic.main.card_item.view.*


class ViewPagerAdapter( val context: Context, val pagerArrayList:ArrayList<Result>): PagerAdapter() {
    override fun getCount(): Int {
        return  pagerArrayList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return  view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view=LayoutInflater.from(context).inflate(R.layout.card_item,container,false)
        view.bannerIv.gorselIndir(pagerArrayList[position].backgroundImage, placeholderYap(context))

        view.setOnClickListener {
            Toast.makeText(context,"${pagerArrayList[position].name} seçildi",Toast.LENGTH_LONG).show()
        }

        container.addView(view,position)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View)
    }

}