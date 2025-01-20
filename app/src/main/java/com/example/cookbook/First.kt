package com.example.cookbook

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

class First : Fragment() {

    private lateinit var pageNumTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity: MainActivity = context as MainActivity
        var pageNum: Int = activity.getPageNum()
        activity.setPageNum(1)

        pageNumTextView = view.findViewById(R.id.pageNum)
        pageNumTextView.text = "Pg. " + pageNum


        val cookbookButton: ImageButton = view.findViewById(R.id.imageButton3)
        cookbookButton.setOnClickListener {
            val navController: NavController = view.findNavController()
            activity.setPageNum(2)
            navController.navigate(R.id.action_first_to_second2)
        }

        val recipesButton: ImageButton = view.findViewById(R.id.imageButton)
        recipesButton.setOnClickListener {
            val navController: NavController = view.findNavController()
            activity.setPageNum(3)
            navController.navigate(R.id.action_first_to_third)
        }
        val searchButton: ImageButton = view.findViewById(R.id.imageButton2)
        searchButton.setOnClickListener {
            val navController: NavController = view.findNavController()
            activity.setPageNum(4)
            navController.navigate(R.id.action_first_to_fourth)
        }
    }

}