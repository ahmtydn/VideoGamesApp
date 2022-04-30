package com.zexly.videogameapp.model

import com.google.gson.annotations.SerializedName

data class GamesJSON(

    @SerializedName("results")
    val results: List<Result>,

)