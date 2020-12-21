package com.noxpa.cocktaildb.model

class DrinksSection(
    private var drinksSectionName: String? = null,
    private var drinks: List<Drink>? = null
) {

    fun getDrinksSectionName(): String? {
        return drinksSectionName
    }

    fun getDrinks(): List<Drink>? {
        return drinks
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DrinksSection

        if (drinksSectionName != other.drinksSectionName) return false

        return true
    }

    override fun hashCode(): Int {
        return drinksSectionName?.hashCode() ?: 0
    }
}