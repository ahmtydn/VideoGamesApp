package com.zexly.videogameapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Result(


    @ColumnInfo(name="background_image")
    @SerializedName("background_image")
    val backgroundImage: String,


    @ColumnInfo(name="description_raw")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name="metacritic")
    @SerializedName("metacritic")
    val metacritic: Int,

    @ColumnInfo(name="name")
    @SerializedName("name")
    val name: String,

    @ColumnInfo(name="rating")
    @SerializedName("rating")
    val rating: Double,

    @ColumnInfo(name="released")
    @SerializedName("released")
    val released: String,
){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int=0
}