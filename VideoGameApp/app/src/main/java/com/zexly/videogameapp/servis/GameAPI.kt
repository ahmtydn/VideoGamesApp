package com.zexly.videogameapp.servis

import com.zexly.videogameapp.model.Game
import io.reactivex.Single
import retrofit2.http.GET

interface GameAPI {



   //api.rawg.io/api/games?key=bf04d7cb269340eab8979a03b76528e7&dates=2019-09-01,2019-09-30&platforms=18,1,7

    //BASE URL -> api.rawg.io/api/
    // games?key=bf04d7cb269340eab8979a03b76528e7&dates=2019-09-01,2019-09-30&platforms=18,1,7

    @GET("/api/games?key=bf04d7cb269340eab8979a03b76528e7")
    fun getGames(): Single<List<Game>>
}