package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
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
fun LeaderBoardScreen(
    mapViewModel: MapViewModel,
    userViewModel: UserViewModel,
    onNavigateToMain: () -> Unit,
    onNavigateToCaughtFish: () -> Unit,
    onNavigateToLeaderBoard: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(), // Make the column fill the screen
        horizontalAlignment = Alignment.CenterHorizontally // Center its children horizontally
    ) {
        // First Row (for the logo)
        Row(
            modifier = Modifier
                .background(Color.LightGray)
                .height(175.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNavigateToMain() },
                    painter = painterResource(id = R.drawable.fishook_logo),
                    contentDescription = "FisHook App Logo",
                )
            }
        }
    }
}