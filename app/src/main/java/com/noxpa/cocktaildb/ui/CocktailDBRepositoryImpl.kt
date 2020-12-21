package com.noxpa.cocktaildb.ui

import com.noxpa.cocktaildb.model.DrinksResponse
import com.noxpa.cocktaildb.model.DrinksCategoriesResponse
import com.noxpa.cocktaildb.network.NetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CocktailDBRepositoryImpl : CocktailDBRepository {

    private val api = NetworkService.getCocktailDBApi()

    override fun getDrinks(drinksCategory: String, onResponse: (response: DrinksResponse?) -> Unit) {
        api
            .getDrinks(drinksCategory)
            .enqueue(object : Callback<DrinksResponse> {
                override fun onResponse(call: Call<DrinksResponse>, response: Response<DrinksResponse>) {
                    if (response.isSuccessful) onResponse(response.body())
                }
                override fun onFailure(call: Call<DrinksResponse>, t: Throwable) {}
        })
    }

    override fun getDrinksCategories(filterType: String, onResponse: (response: DrinksCategoriesResponse?) -> Unit) {
        api
            .getDrinksCategories(filterType)
            .enqueue(object : Callback<DrinksCategoriesResponse> {
                override fun onResponse(call: Call<DrinksCategoriesResponse>, response: Response<DrinksCategoriesResponse>) {
                    if (response.isSuccessful) onResponse(response.body())
                }
                override fun onFailure(call: Call<DrinksCategoriesResponse>, t: Throwable) {}
        })
    }
}