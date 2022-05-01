package com.zexly.videogameapp.servis

import com.zexly.videogameapp.model.GameDetailJSon
import com.zexly.videogameapp.model.GamesJSON
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GameAPI {

   //api.rawg.io/api/games?key=bf04d7cb269340eab8979a03b76528e7&dates=2019-09-01,2019-09-30&platforms=18,1,7

    //BASE URL -> api.rawg.io/api/
    // games?key=bf04d7cb269340eab8979a03b76528e7&dates=2019-09-01,2019-09-30&platforms=18,1,7

    @GET("/api/games?key=bf04d7cb269340eab8979a03b76528e7")
    fun getGames(): Single<GamesJSON>

    @GET("/api/games/{id}?key=bf04d7cb269340eab8979a03b76528e7")
    fun getGamesDetail(
        @Path("id") id:Int
    ):Single<GameDetailJSon>

    //https://api.rawg.io/api/games/3498?key=bf04d7cb269340eab8979a03b76528e7
}