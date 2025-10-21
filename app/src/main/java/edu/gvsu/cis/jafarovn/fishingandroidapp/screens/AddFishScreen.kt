package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import edu.gvsu.cis.jafarovn.fishingandroidapp.MapViewModel
import edu.gvsu.cis.jafarovn.fishingandroidapp.R
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
    var input_text by remember { mutableStateOf("") }
    val current_user = userViewModel.users["JT4"]
    val users_fish_list = userViewModel.fish["JT4"] ?: emptyList()
    var fish_message by remember { mutableStateOf("") }

    Scaffold(modifier = Modifier.fillMaxSize(),
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
                TextField(
                    value = input_text,
                    onValueChange = { user_name_input -> input_text = user_name_input },
                    label = { Text("Enter Your Username: ") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                )
            }
                Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth())
                {
                    Button(onClick = { userViewModel.AddFish("JT4", "Pike",
                        R.drawable.bass_image, 24, 55)
                        fish_message = "Pike added to JT4's profile. "
                    })
                    {
                        Text("Upload Your Fish")
                    }
                    if (fish_message.isNotEmpty())
                    {
                        Text(
                            text = fish_message,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
                Spacer(Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()) {
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