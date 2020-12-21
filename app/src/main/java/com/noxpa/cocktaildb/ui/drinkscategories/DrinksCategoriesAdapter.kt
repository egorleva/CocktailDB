package com.noxpa.cocktaildb.ui.drinkscategories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noxpa.cocktaildb.R
import com.noxpa.cocktaildb.model.DrinkCategory
import kotlinx.android.synthetic.main.item_drink_category.view.*

class DrinksCategoriesAdapter(
    private var drinksCategories: List<DrinkCategory> = listOf(),
    private var selectedDrinksCategories: MutableSet<String> = mutableSetOf()
) : RecyclerView.Adapter<DrinksCategoriesAdapter.DrinksCategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksCategoriesViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_drink_category, parent, false)

        return DrinksCategoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DrinksCategoriesViewHolder, position: Int) {
        holder.bind(drinksCategories[position], selectedDrinksCategories)
    }

    override fun getItemCount(): Int = drinksCategories.size

    class DrinksCategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: DrinkCategory, selectedDrinksCategories: MutableSet<String>) {
            setDrinkCategory(item.drinkCategory)
            setDrinkCategoryCheckBox(item.drinkCategory, selectedDrinksCategories)

            itemView.rootView.setOnClickListener {
                itemView.drink_category_check_box.isChecked = itemView.drink_category_check_box.isChecked.not()

                val isChecked = itemView.drink_category_check_box.isChecked
                val isSelectedDrinkCategory = selectedDrinksCategories.contains(item.drinkCategory)

                if (isChecked && isSelectedDrinkCategory.not()) {
                    selectedDrinksCategories.add(item.drinkCategory)
                } else if (isChecked.not() && isSelectedDrinkCategory) {
                    selectedDrinksCategories.remove(item.drinkCategory)
                }
            }
        }

        private fun setDrinkCategory(drinkCategory: String) {
            itemView.drink_category_text_view.text = drinkCategory
        }

        private fun setDrinkCategoryCheckBox(drinkCategory: String, selectedDrinksCategories: MutableSet<String>) {
            itemView.drink_category_check_box.isChecked = selectedDrinksCategories.contains(drinkCategory)
        }
    }

    fun setData(drinksCategories: List<DrinkCategory>, selectedDrinksCategories: MutableSet<String>) {
        this.drinksCategories = drinksCategories
        this.selectedDrinksCategories = selectedDrinksCategories
        notifyDataSetChanged()
    }
}