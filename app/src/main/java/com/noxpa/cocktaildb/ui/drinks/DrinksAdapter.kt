package com.noxpa.cocktaildb.ui.drinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.noxpa.cocktaildb.R
import com.noxpa.cocktaildb.model.Drink
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_drink.view.*

class DrinksAdapter(
    private var drinks: List<Drink> = mutableListOf()
) : RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder {

        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_drink, parent, false)

        return DrinksViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        holder.bind(drinks[position])
    }

    override fun getItemCount(): Int = drinks.size

    class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(drink: Drink) {
            setDrinkPictureURL(drink.drinkPictureURL)
            setDrinkDescription(drink.drinkDescription)
        }

        private fun setDrinkPictureURL(drinkPictureURL: String?) {
            try {
                Picasso
                    .get()
                    .load(drinkPictureURL)
                    .error(R.drawable.default_drink_picture)
                    .into(itemView.drink_picture_image_view)
            } catch (e: IllegalArgumentException) {
                itemView.drink_picture_image_view.setImageDrawable(getDrawable(itemView.context,
                    R.drawable.default_drink_picture
                ))
            }
        }

        private fun setDrinkDescription(drinkDescription: String?) {
            itemView.drink_description_text_view.text = drinkDescription
        }
    }
}