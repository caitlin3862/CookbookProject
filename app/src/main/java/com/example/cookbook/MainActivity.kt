package com.example.cookbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController
    private var recipesList: MutableList<String> = mutableListOf()
    private var pageNum: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val appBar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(appBar)

        val navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        val navGraph: NavGraph = navController.graph
        appBarConfig = AppBarConfiguration(navGraph)
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {
        val success: Boolean = navController.navigateUp(appBarConfig)
        return success || super.onSupportNavigateUp()
    }

    fun getPageNum(): Int {
        return pageNum
    }

    fun setPageNum(newNum: Int) {
        pageNum = newNum
    }

    fun getRecipes(): MutableList<String>{
        return recipesList
    }

    fun addRecipe(newRecipe: String){
        recipesList.add(newRecipe)
    }
}