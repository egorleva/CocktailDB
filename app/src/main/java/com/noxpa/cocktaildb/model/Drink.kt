package com.noxpa.cocktaildb.model

import com.google.gson.annotations.SerializedName

data class Drink(
    @SerializedName("strDrink")
    val drinkDescription : String,

    @SerializedName("strDrinkThumb")
    val drinkPictureURL : String,

    @SerializedName("idDrink")
    val drinkID : String
)