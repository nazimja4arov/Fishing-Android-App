package edu.gvsu.cis.jafarovn.fishingandroidapp.ui.theme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import edu.gvsu.cis.jafarovn.fishingandroidapp.R
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
//import androidx.compose.runtime.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


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

//class HomeScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            FishingAndroidAppTheme {
//                BackgroundImageScreen(
//                    modifier = Modifier.padding(horizontal = 0.dp, vertical = 0.dp)
//                )
//            }
//        }
//    }
//}
