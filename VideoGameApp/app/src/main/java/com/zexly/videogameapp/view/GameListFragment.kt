package com.zexly.videogameapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.zexly.videogameapp.R
import com.zexly.videogameapp.adapter.GameRecyclerAdapter
import com.zexly.videogameapp.adapter.ViewPagerAdapter
import com.zexly.videogameapp.viewmodel.GameListViewModel
import com.zexly.videogameapp.viewmodel.ViewPagerViewModel
import kotlinx.android.synthetic.main.fragment_game_list.*


class GameListFragment : Fragment() {


    private lateinit var gameListesiViewModel:GameListViewModel
    private lateinit var pagerListeViewModel:ViewPagerViewModel

    private val recyclerGameAdapter=GameRecyclerAdapter(arrayListOf())
//    private  val pagerAdapter= ViewPagerAdapter(requireContext(), arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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


        pagerListeViewModel=ViewModelProviders.of(this).get(ViewPagerViewModel::class.java)
        pagerListeViewModel.verileriSQLitetanAl()
        //viewpagerId.adapter= pagerAdapter

        gameListrcyclerview.layoutManager=LinearLayoutManager(context)
        gameListrcyclerview.adapter=recyclerGameAdapter

        //viewpagerId.setPadding(100,0,100,0 )


        observeLiveDataResault()
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

       /* pagerListeViewModel.gamesResult.observe(viewLifecycleOwner, Observer { games->
            games?.let {
                gameListrcyclerview.visibility=View.VISIBLE
                viewpagerId.visibility=View.VISIBLE
                serchbarId.visibility=View.VISIBLE
                pagerAdapter.resaultGameListesiniGuncelle(it)
            }
        })*/


        gameListesiViewModel.gamesResult.observe(viewLifecycleOwner, Observer { games->
            games?.let {
                gameListrcyclerview.visibility=View.VISIBLE
                viewpagerId.visibility=View.VISIBLE
                serchbarId.visibility=View.VISIBLE
                recyclerGameAdapter.resaultGameListesiniGuncelle(it)
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