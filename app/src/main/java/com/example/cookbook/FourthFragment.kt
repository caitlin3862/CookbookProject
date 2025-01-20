package com.example.cookbook

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FourthFragment : Fragment() {

    private val allRecipes = listOf("Pasta", "Pizza", "Salad", "Soup", "Burger", "Sandwich")
    private val filteredRecipes = mutableListOf<String>()
    private lateinit var pageNumTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_fourth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity: MainActivity = context as MainActivity
        val pageNum: Int = activity.getPageNum()
        activity.setPageNum(4)

        pageNumTextView = view.findViewById(R.id.pageNum4)
        pageNumTextView.text = "Pg. " + pageNum

        val searchEditText = view.findViewById<EditText>(R.id.et_search_recipe)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_search_results)

        filteredRecipes.addAll(allRecipes)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = SimpleRecipeAdapter(filteredRecipes)

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterRecipes(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterRecipes(query: String) {
        filteredRecipes.clear()
        if (query.isNotEmpty()) {
            filteredRecipes.addAll(allRecipes.filter { it.contains(query, ignoreCase = true) })
        } else {
            filteredRecipes.addAll(allRecipes)
        }
        view?.findViewById<RecyclerView>(R.id.rv_search_results)?.adapter?.notifyDataSetChanged()
    }

    class SimpleRecipeAdapter(private val recipes: List<String>) :
        RecyclerView.Adapter<SimpleRecipeAdapter.RecipeViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
            val textView = TextView(parent.context)
            textView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            textView.setPadding(16, 16, 16, 16)
            return RecipeViewHolder(textView)
        }

        override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
            holder.textView.text = recipes[position]
        }

        override fun getItemCount(): Int {
            return recipes.size
        }

        class RecipeViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
    }
}
