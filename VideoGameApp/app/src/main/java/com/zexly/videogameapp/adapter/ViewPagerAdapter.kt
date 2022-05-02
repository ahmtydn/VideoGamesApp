package com.zexly.videogameapp.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.viewpager.widget.PagerAdapter
import com.zexly.videogameapp.R
import com.zexly.videogameapp.model.Result
import com.zexly.videogameapp.util.gorselIndir
import com.zexly.videogameapp.util.placeholderYap
import com.zexly.videogameapp.view.GameListFragmentDirections
import kotlinx.android.synthetic.main.card_item.view.*


class ViewPagerAdapter( val context: Context, val pagerArrayList:ArrayList<Result>): PagerAdapter() {



    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return  view==`object`
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

    override fun getCount(): Int =3

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view=LayoutInflater.from(context).inflate(R.layout.card_item,container,false)
        view.bannerIv.gorselIndir(pagerArrayList[position].backgroundImage, placeholderYap(context))

        view.setOnClickListener {
            val action= GameListFragmentDirections.actionGameListFragmentToGameDetailFragment2(pagerArrayList.get(position).id)
            Navigation.findNavController(it).navigate(action)
        }

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object`as View)
    }

    fun resaultGameListesiniGuncelle(yeniGameListesi:List<Result>){
        pagerArrayList.clear()
        pagerArrayList.addAll(yeniGameListesi)
        notifyDataSetChanged()
    }

}