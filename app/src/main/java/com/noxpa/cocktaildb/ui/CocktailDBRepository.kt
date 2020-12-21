package com.noxpa.cocktaildb.ui

import com.noxpa.cocktaildb.model.DrinksResponse
import com.noxpa.cocktaildb.model.DrinksCategoriesResponse

interface CocktailDBRepository {
    fun getDrinks(drinksCategory: String, onResponse: (response: DrinksResponse?) -> Unit)
    fun getDrinksCategories(filterType: String, onResponse: (response: DrinksCategoriesResponse?) -> Unit)
}