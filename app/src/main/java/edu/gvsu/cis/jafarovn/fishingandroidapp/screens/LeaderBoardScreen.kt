package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderBoardScreen(
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
    val users_list = userViewModel.users.values
    val sorted_users = users_list.sortedByDescending { it.userPoints }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(transparentLightBlue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //Header
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.fishook_logo),
                contentDescription = "FisHook App Logo",
            )

            Text(
                text = "Leaderboard",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        // Leaderboard
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color.White)
        ) {

            val jt = userViewModel.users["JT4"]

            val firstRow = Triple(
                "1",
                jt?.userName ?: "John Turner",
                (jt?.userPoints ?: 0).toString()
            )

            val restRows: List<Triple<String, String, String>> =
                List(9) { i -> Triple("${i + 2}", "User ${i + 2}", "${1000 - i * 37}") }

            val rows = sorted_users.mapIndexed { index, user ->
                Triple(
                    (index + 1).toString(),
                    user.userName,
                    user.userPoints.toString()
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item { LeaderTableHeader() }

                items(rows) { (rank, name, points) ->
                    LeaderTableRow(rank = rank, name = name, points = points)
                }
            }
        }

        // Footer
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Button(
                onClick = { onNavigateToMain() },
                modifier = Modifier
                    .padding(8.dp)
                    .width(140.dp)
                    .height(75.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LightBlue)
            ) {
                Text("Back to Map", fontSize = 20.sp, textAlign = TextAlign.Center)
            }

            Button(
                onClick = onNavigateToProfile,
                modifier = Modifier
                    .padding(8.dp)
                    .width(140.dp)
                    .height(75.dp),
                colors = ButtonDefaults.buttonColors(containerColor = LightBlue)
            )
            {
                Text("Back to Profile", fontSize = 20.sp, textAlign = TextAlign.Center)
            }
        }
    }
}


// Leaderboard Composable
@Composable
fun LeaderTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text("Rank", modifier = Modifier.weight(0.6f), fontWeight = FontWeight.Bold)
        Text("User", modifier = Modifier.weight(1.8f), fontWeight = FontWeight.Bold)
        Text(
            "Points",
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End
        )
    }
    Divider()
}

@Composable
fun LeaderTableRow(rank: String, name: String, points: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(rank, modifier = Modifier.weight(0.6f))
        Text(name, modifier = Modifier.weight(1.8f))
        Text(points, modifier = Modifier.weight(1f), textAlign = TextAlign.End)
    }
    Divider()
}
