package edu.gvsu.cis.jafarovn.fishingandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
                val mapViewModel: MapViewModel = viewModel()
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "HomeScreen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("HomeScreen") {
                            HomeScreen(
                                mapViewModel = mapViewModel,
                                onNavigateToCaughtFish = { navController.navigate("AddFishScreen") }
                            )
                        }
                        composable("AddFishScreen") {
                            AddFishScreen(
                                mapViewModel = mapViewModel,
                                onNavigateToMain = { navController.navigate("HomeScreen") }
                            )
                        }
                        composable("LeaderBoardScreen") {
                            LeaderBoardScreen(
                                mapViewModel = mapViewModel,
                                onNavigateToMain = { navController.navigate("HomeScreen") }
                            )
                        }
                        composable("UserProfileScreen") {
                            UserProfileScreen(
                                mapViewModel = mapViewModel,
                                onNavigateToMain = { navController.navigate("HomeScreen") }
                            )
                        }
                    }
                }
            }
        }
    }
}
