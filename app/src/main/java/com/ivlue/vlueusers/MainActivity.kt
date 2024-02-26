package com.ivlue.vlueusers

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.ivlue.vlueusers.ui.HomeScreen
import com.ivlue.vlueusers.ui.navigation.Navigate
import com.ivlue.vlueusers.ui.theme.VlueUsersTheme
import com.ivlue.vlueusers.ui.utils.TopBar
import com.ivlue.vlueusers.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainApp(
    viewModel: AppViewModel
) {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar(title = "Vlue Users", showBackButton = true, navController) },
        content = { Navigate(navController = navController, viewModel = viewModel) }
    )
}



