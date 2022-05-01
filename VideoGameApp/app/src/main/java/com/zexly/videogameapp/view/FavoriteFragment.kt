package com.zexly.videogameapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.Observer
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.zexly.videogameapp.R
import com.zexly.videogameapp.adapter.GameFavoriRecyclerAdapter
import com.zexly.videogameapp.adapter.GameRecyclerAdapter
import com.zexly.videogameapp.viewmodel.FavoriViewModel
import com.zexly.videogameapp.viewmodel.GameListViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_game_list.*

class FavoriteFragment : Fragment() {

    //favoriRecyclerView

    private lateinit var favoriViewModel: FavoriViewModel
    private val favoriGameAdapter= GameFavoriRecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoriViewModel= ViewModelProviders.of(this).get(FavoriViewModel::class.java)
        favoriViewModel.sqLiteVeriGetir()
        favoriRecyclerView.layoutManager= LinearLayoutManager(context)
        favoriRecyclerView.adapter=favoriGameAdapter

        observeLiveData()

    }

    fun observeLiveData() {
        favoriViewModel.gamesResult.observe(viewLifecycleOwner, Observer { games ->
            games?.let {
                favoriRecyclerView.visibility = View.VISIBLE
                favoriGameAdapter.gameListesiniGuncelle(it)
            }
        })
    }

}