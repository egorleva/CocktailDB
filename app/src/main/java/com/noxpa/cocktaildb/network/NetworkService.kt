package com.noxpa.cocktaildb.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

    private val provideRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCocktailDBApi(): CocktailDBApi = provideRetrofit.create(CocktailDBApi::class.java)
}