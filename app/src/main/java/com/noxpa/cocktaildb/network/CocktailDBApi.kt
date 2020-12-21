package com.noxpa.cocktaildb.network

import com.noxpa.cocktaildb.model.DrinksResponse
import com.noxpa.cocktaildb.model.DrinksCategoriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailDBApi {
    @GET("filter.php")
    fun getDrinks(@Query("c") drinksCategory: String): Call<DrinksResponse>

    @GET("list.php")
    fun getDrinksCategories(@Query("c") filterType: String): Call<DrinksCategoriesResponse>
}