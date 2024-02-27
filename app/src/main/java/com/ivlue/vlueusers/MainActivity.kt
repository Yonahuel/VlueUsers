package com.ivlue.vlueusers

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.ivlue.vlueusers.ui.navigation.Navigate
import com.ivlue.vlueusers.ui.theme.VlueUsersTheme
import com.ivlue.vlueusers.ui.utils.TopBar
import com.ivlue.vlueusers.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entry point activity for the Vlue Users Android application.
 * Responsible for setting up the activity and displaying the main user interface.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VlueUsersTheme {
                val viewModel = ViewModelProvider(this)[AppViewModel::class.java]
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp(viewModel = viewModel)
                }
            }
        }
    }
}

/**
 * Composable function representing the main content of the application.
 *
 * @param viewModel View model for managing application data.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp(
    viewModel: AppViewModel
) {
    // Creates a NavHostController to manage navigation within the app
    val navController = rememberNavController()

    // Renders the main app content using Scaffold
    Scaffold(
        topBar = { TopBar(navController) },
        content = { Navigate(navController = navController, viewModel = viewModel) }
    )
}