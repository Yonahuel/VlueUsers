package com.ivlue.vlueusers.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivlue.vlueusers.ui.DetailsScreen
import com.ivlue.vlueusers.ui.HomeScreen
import com.ivlue.vlueusers.viewmodel.AppViewModel

/**
 * Composable function responsible for setting up navigation within the application.
 * Uses a NavHost to define navigation routes between different screens.
 *
 * @param navController The NavHostController responsible for managing navigation actions.
 * @param viewModel The AppViewModel instance used to provide data to the screens.
 */
@Composable
fun Navigate(
    navController: NavHostController,
    viewModel: AppViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Home.name) {
        // Defines the home screen destination
        composable(Screen.Home.name) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        // Defines the details screen destination
        composable(Screen.Details.name) {
            DetailsScreen(viewModel = viewModel)
        }
    }
}