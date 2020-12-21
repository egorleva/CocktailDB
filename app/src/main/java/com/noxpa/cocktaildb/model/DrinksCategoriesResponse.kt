package com.noxpa.cocktaildb.model

import com.google.gson.annotations.SerializedName

data class DrinksCategoriesResponse(
    @SerializedName("drinks")
    val drinksCategories : List<DrinkCategory>
)