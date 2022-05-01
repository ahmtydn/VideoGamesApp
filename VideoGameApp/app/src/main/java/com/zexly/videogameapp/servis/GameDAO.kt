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

    @Query("DELETE FROM result")
    suspend fun deleteAllGame()

    @Query("SELECT * FROM result WHERE favori=:fid")
    suspend fun getFavoriGames(fid:Int):List<Result>


    @Query("UPDATE result SET favori = :fid WHERE id = :id")
    suspend fun updateGames(id:Int, fid:Int)

}