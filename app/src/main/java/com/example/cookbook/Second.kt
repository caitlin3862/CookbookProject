package com.example.cookbook

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.findNavController

class Second : Fragment() {

    private lateinit var pageNumTextView: TextView
    private lateinit var recipesTextView: TextView
    private lateinit var recipesButton: Button
    private lateinit var list: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipesButton = view.findViewById(R.id.button)
        recipesTextView = view.findViewById(R.id.textView3)
        pageNumTextView = view.findViewById(R.id.pageNum2)

        val activity: MainActivity = context as MainActivity
        val pageNum: Int = activity.getPageNum()
        activity.setPageNum(2)

        list = activity.getRecipes()

        val pref: SharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
        val savedList = pref.getStringSet("recipesList", list.toSet())
        if (savedList != null) {
            list = savedList.toMutableList()
        }

        if (list.isNotEmpty()) {
            for (recipe in list) {
                recipesTextView.text = recipe + "\n"
            }
            recipesButton.visibility = View.INVISIBLE
        } else {
            recipesTextView.text = "Add Recipes to your cookbook!"
            recipesButton.visibility = View.VISIBLE
        }

        pageNumTextView.text = "Pg. " + pageNum

        recipesButton.setOnClickListener {
            val navController: NavController = view.findNavController()
            activity.setPageNum(3)
            navController.navigate(R.id.action_second_to_third2)
        }

    }

    override fun onStop() {
        super.onStop()
        val activity: MainActivity = context as MainActivity
        val prefs: SharedPreferences = activity.getPreferences(Context.MODE_PRIVATE)
        val prefEditor: SharedPreferences.Editor = prefs.edit()
        prefEditor.putStringSet("recipesList", list.toSet())
        prefEditor.apply()
    }

}