package com.zexly.videogameapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zexly.videogameapp.R
import com.zexly.videogameapp.viewmodel.GameDetailViewModel
import kotlinx.android.synthetic.main.fragment_game_detail.*

class GameDetailFragment : Fragment() {
    private lateinit var viewModel:GameDetailViewModel
    private var gameId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       viewModel=ViewModelProviders.of(this).get(GameDetailViewModel::class.java)
       viewModel.roomVerisiniAl()

        arguments?.let {
            gameId=GameDetailFragmentArgs.fromBundle(it).gameID
            println(gameId)
        }

        //observeLiveData()
    }

    /*fun observeLiveData(){

        viewModel.gameLiveData.observe(viewLifecycleOwner, Observer { games->
            games?.let {

                gameIsım.text=it.GameIsim
                gameDescription.text=it.GameDescription
                gameMetacritic.text=it.GameMetacritic.toString()
                gameRelease.text=it.GameReleased

            }
        })
    }*/
}