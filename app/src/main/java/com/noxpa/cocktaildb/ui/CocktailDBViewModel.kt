package com.noxpa.cocktaildb.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noxpa.cocktaildb.model.DrinkCategory
import com.noxpa.cocktaildb.model.DrinksSection

class CocktailDBViewModel : ViewModel() {

    private val repository = CocktailDBRepositoryImpl()

    val drinksSection = MutableLiveData<DrinksSection>()
    val drinksCategories = MutableLiveData<List<DrinkCategory>>()

    var isAppRunsFirstTime = true

    fun getDrinksSection(drinksCategory: String) {
        repository.getDrinks(drinksCategory) {
            it?.let { drinksSection.value = DrinksSection(drinksCategory, it.drinks) }
        }
    }

    fun getDrinksCategories(filterType: String) {
        repository.getDrinksCategories(filterType) {
            it?.let { drinksCategories.value = it.drinksCategories }
        }
    }
}