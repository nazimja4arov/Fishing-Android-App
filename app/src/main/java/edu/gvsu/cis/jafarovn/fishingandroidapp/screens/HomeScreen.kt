package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
fun HomeScreen(
    mapViewModel: MapViewModel,
    userViewModel: UserViewModel,
    onNavigateToCaughtFish: () -> Unit,
    onNavigateToLeaderBoard: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Wrap everything in a Column to arrange children vertically
    Column(
        modifier = modifier.fillMaxSize(), // Make the column fill the screen
        horizontalAlignment = Alignment.CenterHorizontally // Center its children horizontally
    ) {
        // First Row (for the logo)
        Row (modifier = Modifier
            .background(Color.LightGray)
            .height(100.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
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
        Row {
            Text("Locate Fishing Spots on our Map!")
        }
        // Second Row (for the map)
        Row {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.michigan_counties_map),
                    contentDescription = "Map of Michigan counties",
                    modifier = Modifier.size(400.dp) // Adjusted size for better visibility
                )
            }
        }

        // Row for bottom nav buttons
        Row ( verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(175.dp)
        ) {
            Button(
                modifier = Modifier
                    .width(125.dp)
                    .height(75.dp),
                onClick = { onNavigateToCaughtFish() }
            ) {
                Text("Add Fish")
            }
            Button(
                modifier = Modifier
                    .width(125.dp)
                    .height(75.dp),
                onClick = { onNavigateToProfile() }
            ) {
                Text("Profile")
            }
            Button(
                modifier = Modifier
                    .width(125.dp)
                    .height(75.dp),
                onClick = { onNavigateToLeaderBoard() }
            ) {
                Text("Leader Board")
            }
//            Spacer(
//                modifier = Modifier.weight(1f)
//            )
        }
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .height(100.dp)
        ) {
            Text(
                "Tara Barnett, Zeke Turnbough, and Nazim Jafarov",
                modifier = Modifier
                    .padding(bottom = 8.dp),
            )
        }
    }
}
