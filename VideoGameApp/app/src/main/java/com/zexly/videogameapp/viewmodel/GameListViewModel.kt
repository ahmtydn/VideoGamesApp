package com.zexly.videogameapp.viewmodel


import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zexly.videogameapp.model.GamesJSON
import com.zexly.videogameapp.model.Result
import com.zexly.videogameapp.servis.GameAPIServis
import com.zexly.videogameapp.servis.GameDatabase
import com.zexly.videogameapp.util.OzelSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class GameListViewModel(application: Application):BaseViewModel(application) {

    val gamesResult=MutableLiveData<List<Result>>()
    val searchResult=MutableLiveData<List<Result>>()
    val gamesResultPager=MutableLiveData<List<Result>>()
    private val guncellemeZamani=10*60*1000*1000*1000L
    private val ozelSharedPreferences= OzelSharedPreferences(getApplication())

    val hataMesaji=MutableLiveData<Boolean>()
    val gameYukleniyor=MutableLiveData<Boolean>()

    private val gameApiServis=GameAPIServis()
    private val disposable=CompositeDisposable()


    fun refreshData(){


        val kaydedilmeZamani=ozelSharedPreferences.zamaniAl()
        if (kaydedilmeZamani!=null && kaydedilmeZamani!=0L && System.nanoTime()-kaydedilmeZamani<guncellemeZamani){

            verileriSQLitetanAl()
        }
        else
        {
            verileriInternettenAl()
        }
    }

    private fun verileriInternettenAl(){
        gameYukleniyor.value=true

        disposable.add(
        gameApiServis.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<GamesJSON>(){
                override fun onSuccess(t: GamesJSON) {
                    sqLiteSakla(t.results)
                    gamesGoster(t.results)
                }
                override fun onError(e: Throwable) {
                    hataMesaji.value=true
                    gameYukleniyor.value=false
                }

            })
        )
    }
    private fun gamesGoster(gamesListesi:List<Result>){

        val gamePagerList=gamesListesi.subList(0,3)
        val gameRecyclerList=gamesListesi.subList(3,gamesListesi.size)

        gamesResult.value=gameRecyclerList
        gamesResultPager.value=gamePagerList

        hataMesaji.value=false
        gameYukleniyor.value=false
    }


    fun searchDatabase(searchQuery: String){
        launch {
            val gameListesi= GameDatabase(getApplication()).gameDao().searchDatabase(searchQuery)
            searchResult.value=gameListesi

        }
    }

    fun verileriSQLitetanAl(){

        gameYukleniyor.value=true
        launch {
            val gameListesi= GameDatabase(getApplication()).gameDao().getAllGame()
            gamesGoster(gameListesi)
        }
    }

    fun sqLiteSakla(gameListesi:List<Result>){
        launch {
            val dao= GameDatabase(getApplication()).gameDao()
            dao.deleteAllGame()
            val uuidListesi=dao.insertAll(*gameListesi.toTypedArray())    // toTypedArray -> öğeleri tek tek gönderir
            var i=0
            while (i<gameListesi.size)
            {
                gameListesi[i].uuid=uuidListesi[i].toInt()
                i=i+1
                gamesGoster(gameListesi)
            }
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }


}