package com.zexly.videogameapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zexly.videogameapp.model.GameDetailJSon
import com.zexly.videogameapp.model.GamesJSON
import com.zexly.videogameapp.model.Result
import com.zexly.videogameapp.servis.GameAPIServis
import com.zexly.videogameapp.servis.GameDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class GameDetailViewModel(application: Application):BaseViewModel(application) {


    val gamesDetail=MutableLiveData<GameDetailJSon>()
    var favoriValue=MutableLiveData<List<Result>>()

    val detailhatamesajTV=MutableLiveData<Boolean>()
    val detailprogressBarId=MutableLiveData<Boolean>()

    private val gameApiServis= GameAPIServis()
    private val disposable= CompositeDisposable()

    fun verileriInternettenAl(id:Int){
        detailprogressBarId.value=true
        disposable.add(
            gameApiServis.getDataDetail(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<GameDetailJSon>(){
                    override fun onSuccess(t: GameDetailJSon) {
                        gameView(t)

                    }
                    override fun onError(e: Throwable) {
                        detailhatamesajTV.value=true
                        detailprogressBarId.value=false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun gameView(gameDetail:GameDetailJSon){
        gamesDetail.value=gameDetail
        detailhatamesajTV.value=false
        detailprogressBarId.value=false

    }



    fun sqLiteVeriDegistir(id:Int){
        launch {
            val dao= GameDatabase(getApplication()).gameDao()

            dao.updateGames(id,1)

        }
    }

}