package com.ivlue.vlueusers.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ivlue.vlueusers.model.network.entities.User
import com.ivlue.vlueusers.ui.navigation.Screen
import com.ivlue.vlueusers.ui.theme.DarkBlue
import com.ivlue.vlueusers.viewmodel.AppViewModel

/**
 * Composable function responsible for displaying the home screen, showing a list of users.
 *
 * @param modifier Modifier to apply to the composable.
 * @param viewModel View model containing the state and logic for the home screen.
 * @param navController Navigation controller used for navigating to other screens.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    navController: NavController
) {
    // Retrieves the state from the view model
    val state = viewModel.state

    // Renders the home screen UI
    Surface(
        color = MaterialTheme.colorScheme.background
    ) {
        // LazyColumn to efficiently display a list of items
        LazyColumn(
            modifier = Modifier
                .padding(top = 60.dp)
                .fillMaxSize()
        ) {
            // Iterates through the list of items in the state
            items(state.items.size) { i ->
                val item = state.items[i]
                // Loads more items if reaching the end of the list
                if(i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                    viewModel.loadNextItems()
                }
                // Renders a user item
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    UserItem(user = item, navController = navController, viewModel = viewModel)
                }
            }
            // Renders a loading indicator if data is still loading
            item {
                if (state.isLoading) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}

/**
 * Composable function representing a single user item in the list.
 *
 * @param modifier Modifier to apply to the composable.
 * @param viewModel View model containing user-related data and logic.
 * @param user User data to display.
 * @param navController Navigation controller used for navigating to other screens.
 */
@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    user: User,
    navController: NavController
) {
    // Renders a clickable card representing a user item
    Card(
        modifier = modifier
            .testTag("UserCard")
            .clickable {
                viewModel.setUser(user)
                navController.navigate(Screen.Details.name)
            },
        colors = CardDefaults.cardColors(containerColor = DarkBlue)
    ) {
        // Displays user information within the card
        Row {
            AsyncImage(
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 8.dp, horizontal = 12.dp)
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                model = user.picture.thumbnail,
                contentDescription = "User Image"
            )

            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "${user.name.first} ${user.name.last}",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(text = user.email)
            }
        }
    }
}