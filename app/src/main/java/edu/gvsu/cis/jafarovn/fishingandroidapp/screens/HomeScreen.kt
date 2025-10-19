package edu.gvsu.cis.jafarovn.fishingandroidapp.screens

import android.graphics.Paint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.gvsu.cis.jafarovn.fishingandroidapp.MapViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, mapViewModel: MapViewModel,
               onNavigateToCaughtFish: () -> Unit
)
{
    Text("This is the HomeScreen")
}


