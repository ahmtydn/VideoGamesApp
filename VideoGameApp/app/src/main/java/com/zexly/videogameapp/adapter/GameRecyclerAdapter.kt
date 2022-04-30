package com.zexly.videogameapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.zexly.videogameapp.R
import com.zexly.videogameapp.model.Game
import com.zexly.videogameapp.view.GameListFragmentDirections
import kotlinx.android.synthetic.main.game_list_recycler_row.view.*

class GameRecyclerAdapter(val gameListesi:ArrayList<Game>):RecyclerView.Adapter<GameRecyclerAdapter.GameViewHolder>() {

    class GameViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.game_list_recycler_row,parent,false)
        return GameViewHolder(view)

    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        holder.itemView.gameisim.text=gameListesi.get(position).GameIsim
        holder.itemView.gameRankedRlsd.text=gameListesi.get(position).GameReleased
        //görsel eklenecek

        holder.itemView.setOnClickListener {
            val action=GameListFragmentDirections.actionGameListFragmentToGameDetailFragment2(0)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return gameListesi.size
    }

    fun gameListesiniGuncelle(yeniGameListesi:List<Game>){
        gameListesi.clear()
        gameListesi.addAll(yeniGameListesi)
        notifyDataSetChanged()

    }
}