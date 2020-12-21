package com.noxpa.cocktaildb.ui.drinkscategories

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.noxpa.cocktaildb.ui.CocktailDBViewModel
import com.noxpa.cocktaildb.R
import kotlinx.android.synthetic.main.fragment_drinks_categories.*
import kotlinx.android.synthetic.main.view_toolbar.view.*

class DrinksCategoriesFragment : Fragment() {

    private lateinit var listener: DrinksCategoriesFragmentListener

    private var selectedDrinksCategories = mutableSetOf<String>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DrinksCategoriesFragmentListener) listener = context
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_drinks_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedDrinksCategories = getSelectedDrinksCategories()

        val viewModel = ViewModelProvider(requireActivity()).get(CocktailDBViewModel::class.java)

        val adapter = DrinksCategoriesAdapter()
        drinks_categories_recycler_view.adapter = adapter

        viewModel.getDrinksCategories("list")
        viewModel.drinksCategories.observe(viewLifecycleOwner) { adapter.setData(it, selectedDrinksCategories) }

        toolbar_view.image_left_image_view.setOnClickListener {
            listener.onArrowBackImageClick()
        }

        apply_button.setOnClickListener {
            viewModel.isAppRunsFirstTime = false
            setSelectedDrinksCategories(selectedDrinksCategories)
            listener.onApplyButtonClick()
        }
    }

    private fun setSelectedDrinksCategories(selectedDrinksCategories: Set<String>) {
        requireActivity().getPreferences(MODE_PRIVATE).edit().putStringSet("key", selectedDrinksCategories).apply()
    }

    private fun getSelectedDrinksCategories(): MutableSet<String> {
        return requireActivity().getPreferences(MODE_PRIVATE).getStringSet("key", mutableSetOf()) ?: mutableSetOf()
    }

    interface DrinksCategoriesFragmentListener {
        fun onArrowBackImageClick()
        fun onApplyButtonClick()
    }
}