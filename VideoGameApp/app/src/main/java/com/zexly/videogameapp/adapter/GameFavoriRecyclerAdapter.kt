package com.zexly.videogameapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zexly.videogameapp.R
import com.zexly.videogameapp.model.GamesJSON
import com.zexly.videogameapp.model.Result
import com.zexly.videogameapp.util.gorselIndir
import com.zexly.videogameapp.util.placeholderYap
import com.zexly.videogameapp.view.FavoriteFragment
import com.zexly.videogameapp.view.FavoriteFragmentDirections
import com.zexly.videogameapp.view.GameListFragmentDirections
import kotlinx.android.synthetic.main.game_list_recycler_row.view.*

class GameFavoriRecyclerAdapter(val gameListesi:ArrayList<Result>): RecyclerView.Adapter<GameFavoriRecyclerAdapter.GameViewHolder>() {

    class GameViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater= LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.game_list_recycler_row,parent,false)
        return GameFavoriRecyclerAdapter.GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.itemView.gameisim.text=gameListesi.get(position).name
        holder.itemView.gameRankedRlsd.text=gameListesi.get(position).released
        holder.itemView.imageID.gorselIndir(gameListesi.get(position).backgroundImage,
            placeholderYap(holder.itemView.context)
        )
        holder.itemView.setOnClickListener {
            val action= FavoriteFragmentDirections.actionFavoriteFragmentToGameDetailFragment2(gameListesi.get(position).id)
            Navigation.findNavController(it).navigate(action)
    }
    }

    override fun getItemCount(): Int {
        return gameListesi.size
    }

    fun gameListesiniGuncelle(yeniGameListesi: List<Result>){
        gameListesi.clear()
        gameListesi.addAll(yeniGameListesi)
        notifyDataSetChanged()
    }

    fun resaultGameListesiniGuncelle(yeniBesinListesi:List<Result>){
        gameListesi.clear()
        gameListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()
    }


}