package edu.gvsu.cis.jafarovn.fishingandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.gvsu.cis.jafarovn.fishingandroidapp.screens.AddFishScreen
import edu.gvsu.cis.jafarovn.fishingandroidapp.screens.HomeScreen
import edu.gvsu.cis.jafarovn.fishingandroidapp.screens.LeaderBoardScreen
import edu.gvsu.cis.jafarovn.fishingandroidapp.screens.UserProfileScreen
import edu.gvsu.cis.jafarovn.fishingandroidapp.ui.theme.FishingAndroidAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FishingAndroidAppTheme {
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
}