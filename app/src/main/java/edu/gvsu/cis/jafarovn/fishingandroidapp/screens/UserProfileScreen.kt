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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
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
    modifier: Modifier = Modifier,
    transparentLightBlue: Color = Color(red = 0f, green = 0.5f, blue = 1f, alpha = 0.25f),
    LightBlue: Color = Color(red = 0f, green = 0.5f, blue = 1f, alpha = 0.75f)
) {

    val current_user = userViewModel.users["JT4"]
    val users_fish_list = userViewModel.fish["JT4"] ?: emptyList()


    Column(
        modifier = modifier
            .fillMaxSize()
            .background(transparentLightBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

        Column(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
        )
        {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(),
                    painter = painterResource(id = R.drawable.fishook_logo),
                    contentDescription = "FisHook App Logo",
                )
            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome ${current_user?.userName}",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                )
            }
        }


        Column(Modifier.padding(12.dp))
        {
            if (current_user != null)
            {
                Row(modifier = Modifier.padding())
                {
                    Image(
                        painter = painterResource(id = current_user.userProfilePicture),
                        contentDescription = "Profile Picture",
                        Modifier.fillMaxWidth().size(200.dp)
                    )
                }
                Column(Modifier.padding(12.dp).fillMaxWidth())
                {
                    Text(
                        text = "Full Name: ${current_user.userFullName}", fontSize = 24.sp, textAlign = TextAlign.Center,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "Current Points: ${current_user.userPoints}", fontSize = 24.sp, textAlign = TextAlign.Center,
                        modifier = Modifier.padding(5.dp)
                    )
                }
                Row()
                {
                    Text(text = "You've caught: ", fontSize = 24.sp)
                }
                LazyColumn {
                    items(users_fish_list) { fish ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(fish.fishImage),
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
            } else {
                Text("Error: User does not exist")
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(5.dp).fillMaxWidth().fillMaxHeight()
            )
            {
                Button(onClick = { onNavigateToMain() },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(140.dp)
                        .height(75.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightBlue)) {
                    Text("Back to Map", fontSize = 20.dp.value.sp,
                        textAlign = TextAlign.Center)
                }
                Button(onClick = onNavigateToCaughtFish,
                    modifier = Modifier
                        .padding(8.dp)
                        .width(140.dp)
                        .height(75.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightBlue)
                ) {
                    Text("Add Fish", fontSize = 20.dp.value.sp,
                        textAlign = TextAlign.Center)
                }
            }
        }
    }
}