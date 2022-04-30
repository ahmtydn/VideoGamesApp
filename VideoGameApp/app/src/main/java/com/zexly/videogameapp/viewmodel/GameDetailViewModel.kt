package com.zexly.videogameapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zexly.videogameapp.model.GameDetailJSon
import com.zexly.videogameapp.model.GamesJSON
import com.zexly.videogameapp.model.Result
import com.zexly.videogameapp.servis.GameAPIServis
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class GameDetailViewModel:ViewModel() {


    val gamesDetail=MutableLiveData<List<GameDetailJSon>>()

    val hataMesaji=MutableLiveData<Boolean>()
    val gameYukleniyor=MutableLiveData<Boolean>()

    private val gameApiServis= GameAPIServis()
    private val disposable= CompositeDisposable()

    fun verileriInternettenAl(id:Int){
        gameYukleniyor.value=true
        disposable.add(
            gameApiServis.getDataDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<GameDetailJSon>>(){
                    override fun onSuccess(t: List<GameDetailJSon>) {

                        println("${t.get(id).name}uygulandı")
                        gameView(t)
                    }
                    override fun onError(e: Throwable) {
                        hataMesaji.value=true
                        gameYukleniyor.value=false
                    }

                })
        )
    }

    private fun gameView(gameDetail:List<GameDetailJSon>){
        gamesDetail.value=gameDetail
        hataMesaji.value=false
        gameYukleniyor.value=false

    }
}