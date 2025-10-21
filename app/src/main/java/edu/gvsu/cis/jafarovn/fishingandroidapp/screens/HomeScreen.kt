package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    modifier: Modifier = Modifier,
    transparentLightBlue: Color = Color(red = 0f, green = 0.5f, blue = 1f, alpha = 0.25f)
) {
    // Wrap everything in a Column to arrange children vertically
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(transparentLightBlue),
        horizontalAlignment = Alignment.CenterHorizontally // Center its children horizontally
    ) {
        // First Row (for the logo)
        Column (
            modifier = Modifier
                .height(160.dp) // The height might need adjustment depending on the content
                .fillMaxWidth()
        ) {
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
                    .weight(0.5f) // The text usually needs less space than the image
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome to FisHook",
                    style = TextStyle(
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    )
                )
            }
        }
        Row {
            Text(
                "Locate Fishing Spots with the Map!",
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = 20.sp
                ),
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .padding(top = 8.dp),
            )
        }
        // Second Row (for the map)
        Row {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.michigan_counties_map),
                    contentDescription = "Map of Michigan counties",
                    modifier = Modifier
                        .size(400.dp)
                        .border(
                            BorderStroke(width = 8.dp, Color.LightGray)
                        )
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
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable( onClick = onNavigateToProfile),
                painter = painterResource(id = R.drawable.frutiger_aero_icon),
                contentDescription = "FisHook App Logo",
            )
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
