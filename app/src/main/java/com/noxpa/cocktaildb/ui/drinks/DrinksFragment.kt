package com.noxpa.cocktaildb.ui.drinks

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.noxpa.cocktaildb.ui.CocktailDBViewModel
import com.noxpa.cocktaildb.R
import com.noxpa.cocktaildb.model.DrinkCategory
import kotlinx.android.synthetic.main.fragment_drinks.*
import kotlinx.android.synthetic.main.view_toolbar.view.*

class DrinksFragment : Fragment() {

    private lateinit var listener: DrinksFragmentListener

    private var categories = mutableListOf<DrinkCategory>()
    private var categoriesIndex = 1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DrinksFragmentListener) listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drinks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity()).get(CocktailDBViewModel::class.java)
        val adapter = DrinksSectionAdapter()
        drinks_sections_recycler_view.adapter = adapter
        drinks_sections_recycler_view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (recyclerView.canScrollVertically(1).not() && dy > 0) {
                    if (categories.isNotEmpty() && categoriesIndex < categories.size) {
                        viewModel.getDrinksSection(categories[categoriesIndex++].drinkCategory)
                    }
                }
            }
        })

        if (viewModel.isAppRunsFirstTime) {
            viewModel.getDrinksCategories("list")
            viewModel.drinksCategories.observe(viewLifecycleOwner) { drinksCategories ->
                val selectedDrinksCategories = mutableSetOf<String>()
                drinksCategories.forEach { selectedDrinksCategories.add(it.drinkCategory) }
                setSelectedDrinksCategories(selectedDrinksCategories)
                categories = drinksCategories.toMutableList()
                if (getSelectedDrinksCategories().isNullOrEmpty().not()) {
                    viewModel.getDrinksSection(categories[0].drinkCategory)
                }
            }
        } else {
            getSelectedDrinksCategories().forEach { categories.add(DrinkCategory(it)) }
            if (getSelectedDrinksCategories().isNullOrEmpty().not()) {
                viewModel.getDrinksSection(categories[0].drinkCategory)
            }
        }

        viewModel.drinksSection.observe(viewLifecycleOwner) { drinksSection ->
            if (getSelectedDrinksCategories().isNotEmpty()) {
                getSelectedDrinksCategories().forEach {
                    if (it == drinksSection.getDrinksSectionName()) adapter.setData(drinksSection)
                }
            }
        }

        toolbar_view.image_right_image_view.setOnClickListener {
            listener.onFilterImageClick()
        }
    }

    private fun getSelectedDrinksCategories(): MutableSet<String> {
        return requireActivity().getPreferences(MODE_PRIVATE).getStringSet("key", setOf()) ?: mutableSetOf()
    }

    private fun setSelectedDrinksCategories(selectedDrinksCategories: Set<String>) {
        requireActivity().getPreferences(MODE_PRIVATE).edit().putStringSet("key", selectedDrinksCategories).apply()
    }

    interface DrinksFragmentListener {
        fun onFilterImageClick()
    }
}