package com.zexly.videogameapp.servis

import com.zexly.videogameapp.model.GameDetailJSon
import com.zexly.videogameapp.model.GamesJSON
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GameAPIServis {


    private val BASE_URL="https://api.rawg.io"
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(GameAPI::class.java)


    fun getData(): Single<GamesJSON> {
        return api.getGames()
    }

    fun getDataDetail(id:Int):Single<GameDetailJSon>{
        return api.getGamesDetail(id)
    }

}