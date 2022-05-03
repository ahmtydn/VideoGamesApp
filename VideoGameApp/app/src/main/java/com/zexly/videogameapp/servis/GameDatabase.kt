package com.zexly.videogameapp.servis

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zexly.videogameapp.model.Result

@Database(entities = arrayOf(Result::class), version = 7)
abstract class GameDatabase: RoomDatabase() {

    abstract fun gameDao():GameDAO

    //Singleton

    companion object{

        @Volatile private var instance:GameDatabase?=null //@Volatile -> diğer threadlere görünür yapma

        private val lock=Any()

        operator fun invoke(context: Context)= instance?: synchronized(lock){
            instance?: databaseOlustur(context).also {
                instance=it
            }

        }


        private fun databaseOlustur(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            GameDatabase::class.java,
            "gamedatabase7").build()
    }
}