@file:OptIn(ExperimentalMaterial3Api::class)

package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import edu.gvsu.cis.jafarovn.fishingandroidapp.MapViewModel
import edu.gvsu.cis.jafarovn.fishingandroidapp.R
import edu.gvsu.cis.jafarovn.fishingandroidapp.UserViewModel

@Composable
fun UserProfileScreen(
    modifier: Modifier = Modifier,
    mapViewModel: MapViewModel,
    onNavigateToMain: () -> Unit,
    userViewModel: UserViewModel,
    onNavigateToProfile: () -> Unit
)
{
    userViewModel.AddUser("JT4", R.drawable.fishook_logo)
    var current_user = userViewModel.users["JT4"]

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Profile") },
                navigationIcon = {
                    IconButton(onClick = onNavigateToMain) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
        ,content = { innerPadding ->

            Row(modifier = Modifier.padding(innerPadding))
            {
                if (current_user != null) {
                    Image(
                        painter = painterResource(id = current_user.userProfilePicture),
                        contentDescription = "Profile Picture"
                    )
                    Text(text = "Welcome: ${current_user.userName}")
                } else {
                    Text("Error User does not exist")
                }

            }
            /*Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(12.dp)) {
                Button(onClick = { viewModel.restart() }, modifier = Modifier.padding(8.dp)) {
                    Text("Back to Map")
                }
                Button(onClick = onNavigateToSettings, modifier = Modifier.padding(8.dp)) {
                    Text("Add Fish")
                }
                Button(onClick = onNavigateToStatistics, modifier = Modifier.padding(8.dp)) {
                    Text("Statistics")
                }
            }*/
        }
    )
}