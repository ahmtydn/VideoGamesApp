package com.zexly.videogameapp.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
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
        viewpagerId.setCurrentItem(1, true);

        viewpagerId.autoScroll(3000)
        viewpagerId.setPadding(100,0,100,0 )

        observeLiveDataResault()
    }

    fun ViewPager.autoScroll(interval: Long) {

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
        gameListesiViewModel.gamesResult.observe(viewLifecycleOwner, Observer { games->
            games?.let {
                gameListrcyclerview.visibility=View.VISIBLE
                viewpagerId.visibility=View.VISIBLE
                serchbarId.visibility=View.VISIBLE
                recyclerGameAdapter.resaultGameListesiniGuncelle(it)


            }
        })
            gameListesiViewModel.gamesResultPager.observe(viewLifecycleOwner, Observer { games->
                games?.let {
                    gameListrcyclerview.visibility=View.VISIBLE
                    viewpagerId.visibility=View.VISIBLE
                    serchbarId.visibility=View.VISIBLE
                    pagerAdapter.resaultGameListesiniGuncelle(it)
                }
            })

        gameListesiViewModel.hataMesaji.observe(viewLifecycleOwner, Observer { hata->
            hata?.let {

                if (it)
                {
                    hatamesajTV.visibility=View.VISIBLE
                    gameListrcyclerview.visibility=View.GONE
                    progressBarId.visibility=View.GONE
                    viewpagerId.visibility=View.GONE
                    serchbarId.visibility=View.GONE
                }
                else{
                    hatamesajTV.visibility=View.GONE
                }
            }
        })

        gameListesiViewModel.gameYukleniyor.observe(viewLifecycleOwner, Observer { yukleniyor->

            yukleniyor?.let {
                if (it){
                    gameListrcyclerview.visibility=View.GONE
                    hatamesajTV.visibility=View.GONE
                    progressBarId.visibility=View.VISIBLE
                    viewpagerId.visibility=View.GONE
                    serchbarId.visibility=View.GONE
                }else{
                    progressBarId.visibility=View.GONE
                }
            }
        })

    }
}