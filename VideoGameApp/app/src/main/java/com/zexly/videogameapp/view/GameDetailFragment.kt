package com.zexly.videogameapp.view

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zexly.videogameapp.R
import com.zexly.videogameapp.util.gorselIndir
import com.zexly.videogameapp.util.placeholderYap
import com.zexly.videogameapp.viewmodel.GameDetailViewModel
import kotlinx.android.synthetic.main.fragment_game_detail.*
import kotlinx.android.synthetic.main.fragment_game_list.*
import kotlinx.android.synthetic.main.game_list_recycler_row.*
import kotlinx.android.synthetic.main.game_list_recycler_row.view.*

class GameDetailFragment : Fragment() {
    private lateinit var viewModel:GameDetailViewModel
    private var gameId=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {

            gameId=GameDetailFragmentArgs.fromBundle(it).gameID

        }
        viewModel=ViewModelProviders.of(this).get(GameDetailViewModel::class.java)
        viewModel.verileriInternettenAl(gameId)


        fallowIBId.setOnClickListener {
            viewModel.sqLiteDataControl(gameId)
            viewModel.fid.observe(viewLifecycleOwner, Observer { fid->
                fid?.let {
                    if (it.favori==0){

                        viewModel.sqLiteVeriDegistir(gameId,1)
                        fallowIBId.setImageResource(R.drawable.ic_action_favorite)
                    }else {

                        viewModel.sqLiteVeriDegistir(gameId,0)
                        fallowIBId.setImageResource(R.drawable.ic_action_not_favorite)
                    }
                }
            })

        }

        viewModel.sqLiteDataControl(gameId)

        observeLiveData()
    }

    fun observeLiveData(){

        viewModel.gamesDetail.observe(viewLifecycleOwner, Observer { games->
            games?.let {
                gameIsım.text=it.name
                gameDetailDescription.text=it.description
                gameDetailDescription.setMovementMethod(ScrollingMovementMethod());
                gameMetacritic.text=it.metacritic.toString()
                gameRelease.text=it.released
                imageViewgamedetay.gorselIndir(games.backgroundImage, placeholderYap(requireContext()))
            }
        })

        viewModel.fid.observe(viewLifecycleOwner, Observer { fid->
            fid?.let {
                if (it.favori==0){
                    fallowIBId.setImageResource(R.drawable.ic_action_not_favorite)

                }else{
                    fallowIBId.setImageResource(R.drawable.ic_action_favorite)
                }
            }
        })

        viewModel.detailhatamesajTV.observe(viewLifecycleOwner, Observer { hata->
            hata?.let {

                if (it)
                {
                    detailhatamesajTV.visibility=View.VISIBLE
                    detailprogressBarId.visibility=View.GONE
                    gameDetailDescription.visibility=View.GONE
                    detayRelativeLayout.visibility=View.GONE
                    gameIsım.visibility=View.GONE
                    gameRelease.visibility=View.GONE
                    gameMetacritic.visibility=View.GONE

                }
                else{
                    detailhatamesajTV.visibility=View.GONE
                }

            }
        })

        viewModel.detailprogressBarId.observe(viewLifecycleOwner, Observer { hata->
            hata?.let {

                if (it)
                {
                    detailhatamesajTV.visibility=View.GONE
                    detailprogressBarId.visibility=View.VISIBLE
                    gameDetailDescription.visibility=View.GONE
                    detayRelativeLayout.visibility=View.GONE
                    gameIsım.visibility=View.GONE
                    gameRelease.visibility=View.GONE
                    gameMetacritic.visibility=View.GONE
                    gameDetailDescription.visibility=View.GONE


                }
                else{
                    detailprogressBarId.visibility=View.GONE
                    gameDetailDescription.visibility=View.VISIBLE
                    detayRelativeLayout.visibility=View.VISIBLE
                    gameIsım.visibility=View.VISIBLE
                    gameRelease.visibility=View.VISIBLE
                    gameMetacritic.visibility=View.VISIBLE
                }

            }
        })
    }
}