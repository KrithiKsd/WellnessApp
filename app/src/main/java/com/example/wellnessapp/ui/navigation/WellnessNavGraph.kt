package com.example.wellnessapp.ui.navigation

/*
* Filename: WellnessNavGraph.kt
* Author: Krithika Kasaragod
* */

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wellnessapp.ui.home.HomeDestination
import com.example.wellnessapp.ui.home.HomeScreen
import com.example.wellnessapp.ui.task.*


/**
 * Provides Navigation graph for the application.
 */
@Composable
fun WellnessNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(TaskEntryDestination.route) },
            )
        }
        composable(route = TaskEntryDestination.route) {
            TaskEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}
