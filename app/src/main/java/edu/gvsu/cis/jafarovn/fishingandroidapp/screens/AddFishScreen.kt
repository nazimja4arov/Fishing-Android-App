package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import edu.gvsu.cis.jafarovn.fishingandroidapp.MapViewModel
import edu.gvsu.cis.jafarovn.fishingandroidapp.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFishScreen(
    mapViewModel: MapViewModel,
    userViewModel: UserViewModel,
    onNavigateToMain: () -> Unit,
    onNavigateToCaughtFish: () -> Unit,
    onNavigateToLeaderBoard: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
)
{
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("AddFish") },
                navigationIcon = {
                    IconButton(onClick = onNavigateToProfile) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
        ,content = { innerPadding ->
            Column {
        Row(modifier = Modifier.padding(innerPadding))
            {
                Text(text = "This is the Add fish screen")
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(12.dp)) {
                Button(onClick = { onNavigateToMain() }, modifier = Modifier.padding(8.dp)) {
                    Text("Back to Map")
                }
                Button(onClick = onNavigateToProfile, modifier = Modifier.padding(8.dp)) {
                    Text("Back to Profile")
                }
            }
            }
}
    )
}