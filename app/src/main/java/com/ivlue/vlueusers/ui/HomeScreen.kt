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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ivlue.vlueusers.model.network.entities.User
import com.ivlue.vlueusers.ui.navigation.Screen
import com.ivlue.vlueusers.viewmodel.AppViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    navController: NavController
) {
    //val users by viewModel.users.collectAsState()
    val state = viewModel.state
    
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.items.size) { i ->
            val item = state.items[i]
            if(i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                viewModel.loadNextItems()
            }
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                UserItem(user = item, navController = navController, viewModel = viewModel)
            }
        }
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

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    user: User,
    navController: NavController
) {
    Card(
        modifier = modifier.clickable {
            viewModel.setUser(user)
            navController.navigate(Screen.Details.name)
        }
    ) {
        Row {
            AsyncImage(
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
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
                    color = Color.Black
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(text = user.email)
            }
        }
    }
}