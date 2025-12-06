package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
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

@OptIn(ExperimentalMaterial3Api::class)
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
    val user = userViewModel.getCurrentUser()
    val fishList = userViewModel.getCurrentUserFish()
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(transparentLightBlue)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // HEADER ---------------------------------------------------------
        Column(
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fishook_logo),
                    contentDescription = "FisHook App Logo",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Box(
                modifier = Modifier
                    .weight(0.5f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome ${user?.userName ?: "User"}",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                )
            }
        }

        // BODY ------------------------------------------------------------
        Column(Modifier.padding(12.dp)) {

            if (user != null) {

                // Profile Image
                Row {
                    Image(
                        painter = painterResource(id = user.userProfilePicture),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(200.dp)
                    )
                }

                // Full Name + Points
                Column(
                    Modifier
                        .padding(12.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Full Name: ${user.userFullName}",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "Current Points: ${user.userPoints}",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(5.dp)
                    )
                }

                // Fish list
                Row {
                    Text(
                        text = "You've caught:",
                        fontSize = 24.sp
                    )
                }

                Column {
                    fishList.forEach { fish ->
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
                            Column(Modifier.padding(start = 8.dp)) {
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

            // FOOTER --------------------------------------------------------

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(5.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = onNavigateToMain,
                        modifier = Modifier
                            .padding(8.dp)
                            .width(140.dp)
                            .height(75.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LightBlue)
                    ) {
                        Text("Back to Map", fontSize = 20.sp)
                    }
                    Button(
                        onClick = onNavigateToCaughtFish,
                        modifier = Modifier
                            .padding(8.dp)
                            .width(140.dp)
                            .height(75.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = LightBlue)
                    ) {
                        Text("Add Fish", fontSize = 20.sp)
                    }
                }

                Button(
                    onClick = { userViewModel.resetCurrentUserData() },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(220.dp)
                        .height(60.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = LightBlue)
                ) {
                    Text(
                        text = "Reset Fish & Points",
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
