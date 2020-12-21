package com.noxpa.cocktaildb.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.noxpa.cocktaildb.ui.drinkscategories.DrinksCategoriesFragment
import com.noxpa.cocktaildb.ui.drinks.DrinksFragment
import com.noxpa.cocktaildb.R

class CocktailDBActivity : AppCompatActivity(),
    DrinksFragment.DrinksFragmentListener,
    DrinksCategoriesFragment.DrinksCategoriesFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktaildb)

        val viewModel = ViewModelProvider(this).get(CocktailDBViewModel::class.java)

        if (getSelectedDrinksCategories() != null) viewModel.isAppRunsFirstTime = false

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, DrinksFragment())
            .commit()
    }

    override fun onFilterImageClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, DrinksCategoriesFragment())
            .commit()
    }

    override fun onArrowBackImageClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, DrinksFragment())
            .commit()
    }

    override fun onApplyButtonClick() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, DrinksFragment())
            .commit()
    }

    private fun getSelectedDrinksCategories(): MutableSet<String>? {
        return getPreferences(MODE_PRIVATE).getStringSet("key", null)
    }
}