package edu.gvsu.cis.jafarovn.fishingandroidapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel

class MapViewModel: ViewModel() {
    @Composable
    fun BackgroundImageScreen() {
        Box(
            Modifier
                .fillMaxSize()
                .paint(
                    painterResource(R.drawable.michigan_counties_map),
                    contentScale = ContentScale.Crop
                )
        )
    }
}