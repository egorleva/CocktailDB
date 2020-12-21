package com.noxpa.cocktaildb.ui.drinks

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.noxpa.cocktaildb.R
import com.noxpa.cocktaildb.model.Drink
import com.noxpa.cocktaildb.model.DrinksSection
import kotlinx.android.synthetic.main.item_drink_section.view.*

class DrinksSectionAdapter(
    private var drinksSections: MutableSet<DrinksSection> = mutableSetOf()
) : RecyclerView.Adapter<DrinksSectionAdapter.DrinksSectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksSectionViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_drink_section, parent, false)

        return DrinksSectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DrinksSectionViewHolder, position: Int) {
        holder.bind(drinksSections.toMutableList()[position])
    }

    override fun getItemCount(): Int = drinksSections.size

    class DrinksSectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(drinksSection: DrinksSection) {
            setDrinksSectionName(drinksSection.getDrinksSectionName())
            setDrinks(drinksSection.getDrinks())
        }

        private fun setDrinksSectionName(drinksSectionName: String?) {
            itemView.drinks_section_name_text_view.text = drinksSectionName
        }

        private fun setDrinks(drinks: List<Drink>?) {
            itemView.drinks_recycler_view.adapter = DrinksAdapter(drinks!!)
        }
    }

    fun setData(drinksSection: DrinksSection) {
        this.drinksSections.add(drinksSection)
        notifyDataSetChanged()
    }
}