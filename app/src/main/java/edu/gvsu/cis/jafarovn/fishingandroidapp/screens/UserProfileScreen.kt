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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.sp
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

    val current_user = userViewModel.users["JT4"]
    val users_fish_list = userViewModel.fish["JT4"] ?: emptyList()

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
                title = { Text(text = "${current_user?.userName}") },
                navigationIcon = {
                    IconButton(onClick = onNavigateToMain) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
        ,content = { innerPadding ->
            Column(Modifier.padding(12.dp)) {
                if (current_user != null) {
                    Row(modifier = Modifier.padding(innerPadding))
                    {
                        Image(
                            painter = painterResource(id = current_user.userProfilePicture),
                            contentDescription = "Profile Picture",
                            Modifier.fillMaxWidth().size(250.dp)
                        )
                    }
                    Column(Modifier.padding(12.dp))
                    {
                        Text(
                            text = "Welcome: ${current_user.userFullName}", fontSize = 20.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                        Text(
                            text = "You're ranked: ${current_user.userRank}", fontSize = 20.sp,
                            modifier = Modifier.padding(5.dp)
                        )
                    }
                    Row()
                    {
                        Text(text = "You've caught: ", fontSize = 20.sp)
                    }
                    LazyColumn {
                        items(users_fish_list) { fish ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(173, 216, 230))
                                    .padding(8.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = fish.fishImage),
                                    contentDescription = fish.fishName,
                                    modifier = Modifier.size(64.dp)
                                )
                                Column(modifier = Modifier.padding(start = 8.dp)) {
                                    Text("Name: ${fish.fishName}")
                                    Text("Length: ${fish.fishLength} in")
                                    Text("Weight: ${fish.fishWeight} lbs")
                                }
                            }
                        }
                    }
                }
                else{
                    Text("Error: User does not exist")
                }

            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(5.dp).fillMaxWidth().fillMaxHeight())
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