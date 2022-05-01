package com.zexly.videogameapp.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zexly.videogameapp.model.GameDetailJSon


@Dao
interface GameDAO {

    @Insert
    suspend fun insertAll(vararg gameDetailJSon: GameDetailJSon):List<Long>


    @Query("SELECT * FROM gameDetailJSon")
    suspend fun getAllBesin():List<GameDetailJSon>

}