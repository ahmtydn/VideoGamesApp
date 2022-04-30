package com.zexly.videogameapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Game (

    @ColumnInfo(name="id")
    @SerializedName("id")
    val GameId:Int?,

    @ColumnInfo(name="name")
    @SerializedName("name")
    val GameIsim:String?,

    @ColumnInfo(name="released")
    @SerializedName("released")
    val GameReleased:String?,

    @ColumnInfo(name="background_image")
    @SerializedName("background_image")
    val GameImage:String?,

    @ColumnInfo(name="rating")
    @SerializedName("rating")
    val GameRating:Int?,

    @ColumnInfo(name="metacritic")
    @SerializedName("metacritic")
    val GameMetacritic:Int?,

    @ColumnInfo(name="updated")
    @SerializedName("updated")
    val GameDescription:String?

    ){
}