package com.zexly.videogameapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zexly.videogameapp.model.Result
import com.zexly.videogameapp.servis.GameDatabase
import kotlinx.coroutines.launch

class FavoriViewModel(application: Application):BaseViewModel(application) {

    val gamesResult= MutableLiveData<List<Result>>()


    fun sqLiteVeriGetir(){
        launch {
            val gameListesi= GameDatabase(getApplication()).gameDao().getFavoriGames(1)
            gamesGoster(gameListesi)
        }
    }
    private fun gamesGoster(gamesListesi:List<Result>){
        gamesResult.value=gamesListesi

    }
}