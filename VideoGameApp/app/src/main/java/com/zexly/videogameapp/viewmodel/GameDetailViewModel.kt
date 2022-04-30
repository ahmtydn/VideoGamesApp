package com.zexly.videogameapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zexly.videogameapp.model.Game

class GameDetailViewModel:ViewModel() {

    val gameLiveData=MutableLiveData<Game>()

    fun roomVerisiniAl(){
        val gta=Game(0,"GTA","2022","zexly.com",15,25,"")
        gameLiveData.value=gta
    }
}