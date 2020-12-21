package com.noxpa.cocktaildb.model

import com.google.gson.annotations.SerializedName

data class DrinksResponse(
    @SerializedName("drinks")
    val drinks : List<Drink>
)