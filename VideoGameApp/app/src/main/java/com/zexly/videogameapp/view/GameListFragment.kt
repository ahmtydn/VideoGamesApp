package com.zexly.videogameapp.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import com.zexly.videogameapp.R
import com.zexly.videogameapp.adapter.GameRecyclerAdapter
import com.zexly.videogameapp.adapter.ViewPagerAdapter
import com.zexly.videogameapp.viewmodel.GameListViewModel
import kotlinx.android.synthetic.main.fragment_game_list.*


class GameListFragment : Fragment() {

    private lateinit var gameListesiViewModel:GameListViewModel
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        pagerAdapter= ViewPagerAdapter(requireContext(), arrayListOf())
    }
    private val recyclerGameAdapter=GameRecyclerAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_list, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gameListesiViewModel=ViewModelProviders.of(this).get(GameListViewModel::class.java)
        gameListesiViewModel.refreshData()
        gameListrcyclerview.layoutManager=LinearLayoutManager(context)
        gameListrcyclerview.adapter=recyclerGameAdapter

        viewpagerId.adapter= pagerAdapter
        viewpagerId.setCurrentItem(1, true)

        viewpagerId.autoScroll(3000)
        viewpagerId.setPadding(100,0,100,0 )

        observeLiveDataResault()
        search()

    }

   private fun ViewPager.autoScroll(interval: Long) {

        val handler = Handler()
        var scrollPosition = 0

        val runnable = object : Runnable {

            override fun run() {

                val count = adapter?.count ?: 0
                setCurrentItem(scrollPosition++ % count, true)

                handler.postDelayed(this, interval)
            }
        }

        addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                scrollPosition = position + 1
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }
        })

        handler.post(runnable)
    }


    fun observeLiveDataResault(){
        gameListesiViewModel.gamesResult.observe(viewLifecycleOwner) { games ->
            games?.let {
                gameListrcyclerview.visibility = View.VISIBLE
                viewpagerId.visibility = View.VISIBLE
                serchbarId.visibility = View.VISIBLE
                recyclerGameAdapter.resaultGameListesiniGuncelle(it)


            }
        }
        gameListesiViewModel.gamesResultPager.observe(viewLifecycleOwner) { games ->
            games?.let {
                gameListrcyclerview.visibility = View.VISIBLE
                viewpagerId.visibility = View.VISIBLE
                serchbarId.visibility = View.VISIBLE
                pagerAdapter.resaultGameListesiniGuncelle(it)
            }
        }

        gameListesiViewModel.hataMesaji.observe(viewLifecycleOwner) { hata ->
            hata?.let {

                if (it) {
                    hatamesajTV.visibility = View.VISIBLE
                    gameListrcyclerview.visibility = View.GONE
                    progressBarId.visibility = View.GONE
                    viewpagerId.visibility = View.GONE
                    serchbarId.visibility = View.GONE
                } else {
                    hatamesajTV.visibility = View.GONE
                }
            }
        }

        gameListesiViewModel.gameYukleniyor.observe(viewLifecycleOwner) { yukleniyor ->

            yukleniyor?.let {
                if (it) {
                    gameListrcyclerview.visibility = View.GONE
                    hatamesajTV.visibility = View.GONE
                    progressBarId.visibility = View.VISIBLE
                    viewpagerId.visibility = View.GONE
                    serchbarId.visibility = View.GONE
                } else {
                    progressBarId.visibility = View.GONE
                }
            }
        }

    }

    fun search(){

        serchbarId.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null){
                    searchKontrol(query)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query!=null){
                    searchKontrol(query)
                }
                return true
            }
        })

    }
    private fun searchKontrol(query: String){
        if (query.length>2){
            searchDatabase(query)
        }else {
            viewpagerId.visibility = View.VISIBLE
            observeLiveDataResault()
        }

    }

    private fun searchDatabase(query: String?){
        val searcQuery="%$query%"

        gameListesiViewModel.searchDatabase(searcQuery)

        gameListesiViewModel.searchResult.observe(viewLifecycleOwner) { games ->
            games?.let {
                gameListrcyclerview.visibility = View.VISIBLE
                viewpagerId.visibility = View.GONE
                serchbarId.visibility = View.VISIBLE
                hatamesajTV.visibility = View.GONE
                hatamesajTV.text="Hata oluştu!"
                recyclerGameAdapter.resaultGameListesiniGuncelle(it)
                if (it.isEmpty()){
                    hatamesajTV.text="Aradığınız sonuç bulunamadı!"
                    hatamesajTV.visibility=View.VISIBLE
                    gameListrcyclerview.visibility = View.GONE
                    viewpagerId.visibility = View.GONE
                    recyclerGameAdapter.resaultGameListesiniGuncelle(it)
                }
            }
        }
    }
}