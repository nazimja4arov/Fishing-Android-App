package edu.gvsu.cis.jafarovn.fishingandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
<<<<<<< HEAD
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.gvsu.cis.jafarovn.fishingandroidapp.screens.AddFishScreen
import edu.gvsu.cis.jafarovn.fishingandroidapp.screens.HomeScreen
import edu.gvsu.cis.jafarovn.fishingandroidapp.screens.LeaderBoardScreen
import edu.gvsu.cis.jafarovn.fishingandroidapp.screens.UserProfileScreen
import edu.gvsu.cis.jafarovn.fishingandroidapp.ui.theme.FishingAndroidAppTheme
import edu.gvsu.cis.jafarovn.fishingandroidapp.ui.theme.BackgroundImageScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FishingAndroidAppTheme {
                BackgroundImageScreen()





//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                

		val navigation_controller = rememberNavController()
                val mapViewModel: MapViewModel = viewModel()
                NavHost(navigation_controller, startDestination = "home_screen"){
                    composable ("home_screen")
                    {
                        HomeScreen(mapViewModel = mapViewModel, onNavigateToCaughtFish = {navigation_controller.navigate("add_fish_screen")})
                    }
                    composable ("add_fish_screen")
                    {
                        AddFishScreen(mapViewModel = mapViewModel, onNavigateToMain= {navigation_controller.navigate("home_screen")})
                    }
                    composable ("leaderboard_screen")
                    {
                        LeaderBoardScreen(mapViewModel = mapViewModel, onNavigateToMain = {navigation_controller.navigate("home_screen")})
                    }
                    composable ("profile_screen")
                    {
                        UserProfileScreen(mapViewModel = mapViewModel, onNavigateToMain= {navigation_controller.navigate("home_screen")})
                    }
                }
            }
        }
    }
