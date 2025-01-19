package com.example.cookbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class ThirdFragment : Fragment() {

    private val recipeList = mutableListOf<Recipe>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeName = view.findViewById<EditText>(R.id.et_recipe_name)
        val recipeDescription = view.findViewById<EditText>(R.id.et_recipe_description)
        val recipeIngredients = view.findViewById<EditText>(R.id.et_recipe_ingredients)
        val recipeSteps = view.findViewById<EditText>(R.id.et_recipe_steps)
        val saveButton = view.findViewById<Button>(R.id.btn_save_recipe)

        saveButton.setOnClickListener {
            val name = recipeName.text.toString()
            val description = recipeDescription.text.toString()
            val ingredients = recipeIngredients.text.toString()
            val steps = recipeSteps.text.toString()

            if (name.isNotBlank() && description.isNotBlank() && ingredients.isNotBlank() && steps.isNotBlank()) {
                val recipe = Recipe(name, description, ingredients, steps)
                recipeList.add(recipe)

                recipeName.text.clear()
                recipeDescription.text.clear()
                recipeIngredients.text.clear()
                recipeSteps.text.clear()
            }
        }
    }
}

data class Recipe(val name: String, val description: String, val ingredients: String, val steps: String)
