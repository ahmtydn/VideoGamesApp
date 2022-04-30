package com.zexly.videogameapp.viewmodel


import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zexly.videogameapp.model.GamesJSON
import com.zexly.videogameapp.servis.GameAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class GameListViewModel(application: Application):BaseViewModel(application) {
    val games=MutableLiveData<GamesJSON>()

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
            .subscribeWith(object : DisposableSingleObserver<GamesJSON>(){
                override fun onSuccess(t: GamesJSON) {
                   gameView(t)
                }

                override fun onError(e: Throwable) {
                    hataMesaji.value=true
                    gameYukleniyor.value=false
                }


            })
        )
    }

    private fun gameView(gameNesnesi:GamesJSON){
        games.value=gameNesnesi
        hataMesaji.value=false
        gameYukleniyor.value=false

    }


}