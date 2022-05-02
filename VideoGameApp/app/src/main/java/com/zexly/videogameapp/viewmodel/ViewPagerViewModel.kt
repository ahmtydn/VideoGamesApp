package com.zexly.videogameapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.zexly.videogameapp.model.Result
import com.zexly.videogameapp.servis.GameDatabase
import kotlinx.coroutines.launch

class ViewPagerViewModel(application: Application):BaseViewModel(application) {

    val gamesResult= MutableLiveData<List<Result>>()

    fun verileriSQLitetanAl(){


        launch {
            val gameListesi= GameDatabase(getApplication()).gameDao().getAllGame()
            gamesGoster(gameListesi)
            Toast.makeText(getApplication(),"Oyunları Roomdan Aldık", Toast.LENGTH_LONG).show()
        }
    }

    private fun gamesGoster(gamesListesi:List<Result>){
        gamesResult.value=gamesListesi

    }
}