package com.zexly.videogameapp.model

import com.google.gson.annotations.SerializedName

data class GameDetailJSon(

    @SerializedName("background_image")
    val backgroundImage: String,


    @SerializedName("id")
    var id: Int,


    @SerializedName("metacritic")
    val metacritic: Int,


    @SerializedName("name")
    val name: String,


    @SerializedName("rating")
    val rating: Double,

    @SerializedName("released")
    val released: String,

    @SerializedName("description")
    val description: String,) {

}