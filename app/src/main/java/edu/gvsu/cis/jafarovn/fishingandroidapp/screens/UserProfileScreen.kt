@file:OptIn(ExperimentalMaterial3Api::class)

package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.gvsu.cis.jafarovn.fishingandroidapp.MapViewModel
import edu.gvsu.cis.jafarovn.fishingandroidapp.R
import edu.gvsu.cis.jafarovn.fishingandroidapp.UserViewModel

@Composable
fun UserProfileScreen(
    mapViewModel: MapViewModel,
    userViewModel: UserViewModel,
    onNavigateToMain: () -> Unit,
    onNavigateToCaughtFish: () -> Unit,
    onNavigateToLeaderBoard: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
)
{
    userViewModel.AddUser("JT4", R.drawable.app_pfp)
    var current_user = userViewModel.users["JT4"]

    Row (modifier = Modifier
        .background(Color.LightGray)
        .height(175.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.fishook_logo),
                contentDescription = "FisHook App Logo",
            )
        }
    }
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
            Column {

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
            Spacer(Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp).fillMaxWidth())
            {
                Button(onClick = { onNavigateToMain() }, modifier = Modifier.padding(8.dp)) {
                    Text("Back to Map")
                }
                Button(onClick = onNavigateToCaughtFish, modifier = Modifier.padding(8.dp)) {
                    Text("Add Fish")
                }

            }

            }
        }
    )
}