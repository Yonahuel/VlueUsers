package com.ivlue.vlueusers.ui.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import com.ivlue.vlueusers.ui.HomeScreen
import com.ivlue.vlueusers.viewmodel.AppViewModel
import io.mockk.mockk
import org.mockito.kotlin.verify
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testNavigationToDetailsScreen() {
        val navController = mock<NavHostController>()
        val viewModel = mockk<AppViewModel>()

        composeTestRule.setContent {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }

        composeTestRule.onNodeWithTag("UserCard").performClick()

        verify(navController).navigate(Screen.Details.name)
    }
}