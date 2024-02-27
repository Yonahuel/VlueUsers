package com.ivlue.vlueusers.ui.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import com.ivlue.vlueusers.ui.HomeScreen
import com.ivlue.vlueusers.viewmodel.AppViewModel
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * UI test class for navigation.
 */
class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun create() {
        // Mock NavHostController and AppViewModel
        val navController = mockk<NavHostController>()
        val viewModel = mockk<AppViewModel>()
        // Set up the HomeScreen composable
        composeTestRule.setContent {
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
    }

    /**
     * Test case for navigation to DetailsScreen.
     */
    @Test
    fun testNavigationToDetailsScreen() {
        // Trigger click action on the UI element with tag "UserCard"
        composeTestRule.onNodeWithTag("UserCard").performClick()

        composeTestRule.onNodeWithText("Email: ").assertIsDisplayed()
    }
}