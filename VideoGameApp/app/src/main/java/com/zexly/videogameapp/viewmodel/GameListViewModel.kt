package com.zexly.videogameapp.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zexly.videogameapp.model.Game
import com.zexly.videogameapp.servis.GameAPI
import com.zexly.videogameapp.servis.GameAPIServis
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class GameListViewModel:ViewModel() {
    val games=MutableLiveData<List<Game>>()
    val hataMesaji=MutableLiveData<Boolean>()
    val gameYukleniyor=MutableLiveData<Boolean>()

    private val gameApiServis=GameAPIServis()
    private val disposable=CompositeDisposable()


    fun refreshData(){
        verileriInternettenAl()
    }

    private fun verileriInternettenAl(){
        gameYukleniyor.value=true

        disposable.add(
        gameApiServis.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Game>>(){
                override fun onSuccess(t: List<Game>) {
                    //başarılı ise
                    games.value=t
                    hataMesaji.value=false
                    gameYukleniyor.value=false
                }
                override fun onError(e: Throwable) {
                    //hatalı ise
                    hataMesaji.value=true
                    gameYukleniyor.value=false
                    e.printStackTrace()
                }

            })
        )
    }
}