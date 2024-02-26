package com.ivlue.vlueusers.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ivlue.vlueusers.viewmodel.AppViewModel

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
    navController: NavController
) {
    val user = viewModel.user.collectAsState()

    Surface(
        modifier = modifier.fillMaxSize(),
        color = Color.Blue
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = user.value?.picture?.large,
                contentDescription = "User Image",
                modifier = modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = "${user.value?.name?.title} ${user.value?.name?.first} ${user.value?.name?.last}",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
                )
            Spacer(modifier = modifier.height(24.dp))
            user.value?.let { UserDetailsItem(label = "Email: ", value = it.email) }
            user.value?.let { UserDetailsItem(label = "Phone: ", value = it.phone) }
            UserDetailsItem(
                label = "Location: ",
                value = "${user.value?.location?.street?.name} ${user.value?.location?.street?.number}")
            user.value?.registered?.date?.let { UserDetailsItem(label = "Registered Date: ", value = it) }
        }
    }
}

@Composable
private fun UserDetailsItem(label: String, value: String) {
    Column(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}