package com.noxpa.cocktaildb.model

import com.google.gson.annotations.SerializedName

data class DrinkCategory(
    @SerializedName("strCategory")
    val drinkCategory : String
)