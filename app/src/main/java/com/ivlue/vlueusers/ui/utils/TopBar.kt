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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController
) {
    val currentScreen = navController.currentBackStackEntryAsState().value?.destination

    CenterAlignedTopAppBar(
        title = {
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
        colors = topAppBarColors(containerColor = DarkBlue),
        navigationIcon = {
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