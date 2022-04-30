package com.zexly.videogameapp.servis

import com.zexly.videogameapp.model.Game
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class GameAPIServis {


    private val BASE_URL="https://api.rawg.io"
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(GameAPI::class.java)


    fun getData(): Single<List<Game>> {
        return api.getGames()

    }
}