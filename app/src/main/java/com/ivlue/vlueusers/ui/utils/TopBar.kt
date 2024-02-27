package com.ivlue.vlueusers.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ivlue.vlueusers.ui.navigation.Screen
import com.ivlue.vlueusers.ui.theme.DarkBlue
import com.ivlue.vlueusers.ui.theme.LightBlue

/**
 * Composable function responsible for displaying the top app bar.
 * The appearance and behavior of the app bar are customized based on the current screen.
 *
 * @param navController The NavController responsible for managing navigation actions.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController
) {
    // Retrieves the current screen from the navigation controller
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination

    // Displays a customized top app bar based on the current screen
    CenterAlignedTopAppBar(
        title = {
            // Displays different titles based on the current screen
            if (currentScreen?.route == Screen.Home.name) {
                Text(
                    text = "Vlue Users",
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp,
                    color = Color.White
                )
            } else {
                Text(
                    text = "User Details",
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp,
                    color = Color.White
                )
            }
        },
        // Sets custom colors for the app bar
        colors = topAppBarColors(containerColor = DarkBlue),
        navigationIcon = {
            // Displays a back button if the current screen is the details screen
            if (currentScreen?.route == Screen.Details.name) {
                IconButton(
                    onClick = { navController.navigateUp() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back button",
                        tint = Color.White
                    )
                }
            }
        }
    )
}