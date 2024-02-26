package com.ivlue.vlueusers.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ivlue.vlueusers.ui.DetailsScreen
import com.ivlue.vlueusers.ui.HomeScreen
import com.ivlue.vlueusers.viewmodel.AppViewModel

@Composable
fun Navigate(
    navController: NavHostController,
    viewModel: AppViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Home.name) {
        composable(Screen.Home.name) {
            HomeScreen(viewModel = viewModel, navController = navController)
        }
        composable(Screen.Details.name) {
            DetailsScreen(viewModel = viewModel, navController = navController)
        }
    }
}

