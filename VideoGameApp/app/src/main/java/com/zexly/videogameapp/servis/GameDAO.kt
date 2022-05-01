package com.zexly.videogameapp.servis

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.zexly.videogameapp.model.Result

@Dao
interface GameDAO {

    @Insert
    suspend fun insertAll(vararg result:Result):List<Long>


    @Query("SELECT * FROM result")
    suspend fun getAllGame():List<Result>

}