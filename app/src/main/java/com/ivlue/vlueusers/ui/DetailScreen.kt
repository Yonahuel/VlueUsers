package com.ivlue.vlueusers.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.ivlue.vlueusers.ui.theme.DarkBlue
import com.ivlue.vlueusers.ui.theme.LightGray
import com.ivlue.vlueusers.viewmodel.AppViewModel

/**
 * Composable function responsible for displaying the details screen for a user.
 *
 * @param modifier Modifier to apply to the composable.
 * @param viewModel View model containing user data.
 */
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel,
) {
    // Collects user data from the view model as a state
    val user = viewModel.user.collectAsState()
    val context = LocalContext.current

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ElevatedCard(
            colors = CardDefaults.cardColors(containerColor = DarkBlue),
            modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 76.dp, bottom = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                // Displays user image
                Surface(
                    shadowElevation = 8.dp,
                    modifier = modifier
                        .clip(RoundedCornerShape(16.dp))
                        .size(200.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    SubcomposeAsyncImage(
                        model = user.value?.picture?.large,
                        contentDescription = "User Image",
                        modifier = modifier.fillMaxSize(),
                        loading = {
                            CircularProgressIndicator(
                                modifier = modifier
                                    .align(Alignment.Center)
                                    .padding(16.dp)
                            )
                        }
                    )
                }
                Spacer(modifier = modifier.height(16.dp))
                // Displays user name
                Text(
                    text = "${user.value?.name?.title} ${user.value?.name?.first} ${user.value?.name?.last}",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(16.dp))
                // Displays user details items
                UserDetailsItem(
                    label = "Email: ",
                    value = user.value?.email ?: "null",
                    icon = Icons.Filled.Email,
                    onClick = { email ->
                        val intent = Intent(Intent.ACTION_SENDTO)
                        intent.data = Uri.parse("mailto:$email")
                        context.startActivity(intent)
                    }
                )
                UserDetailsItem(
                    label = "Phone: ",
                    value = user.value?.phone ?: "null",
                    icon = Icons.Filled.Phone,
                    onClick = { phone ->
                        val intent = Intent(Intent.ACTION_DIAL)
                        intent.data = Uri.parse("tel:$phone")
                        context.startActivity(intent)
                    }
                )
                UserDetailsItem(
                    label = "Location: ",
                    value = "${user.value?.location?.street?.name} ${user.value?.location?.street?.number}, ${user.value?.location?.city}",
                    icon = Icons.Filled.LocationOn,
                    onClick = {
                        openLocationInMaps(user.value?.location?.coordinates?.latitude ?: 0.0, user.value?.location?.coordinates?.longitude ?: 0.0, context)
                    }
                )
                UserDetailsItem(
                    label = "Registered Date: ",
                    value = user.value?.registered?.date ?: "null",
                    icon = Icons.Filled.DateRange
                )
            }
        }
    }
}

/**
 * Opens the location in Maps using the provided latitude and longitude coordinates.
 *
 * @param latitude Latitude coordinate of the location.
 * @param longitude Longitude coordinate of the location.
 */
@SuppressLint("QueryPermissionsNeeded")
fun openLocationInMaps(
    latitude: Double,
    longitude: Double,
    context: Context
) {
    val gmmIntentUri = Uri.parse("geo:$latitude,$longitude?z=1")
    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
    mapIntent.setPackage("com.google.android.apps.maps")
    context.startActivity(mapIntent)
}

/**
 * Composable function representing a single user details item.
 *
 * @param modifier Modifier to apply to the composable.
 * @param label Label text for the details item.
 * @param value Value text for the details item.
 * @param onClick Callback function invoked when the item is clicked.
 * @param icon Icon associated with the details item.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserDetailsItem(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onClick: ((String) -> Unit)? = null,
    icon: ImageVector
) {
    // Renders a clickable elevated card with the specified details
    ElevatedCard(
        colors = CardDefaults.cardColors(containerColor = LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = { onClick?.invoke(value) },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = modifier.padding(4.dp)
        ) {
            // Renders the icon associated with the details item
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                // Renders the label text
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                // Renders the value text
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}