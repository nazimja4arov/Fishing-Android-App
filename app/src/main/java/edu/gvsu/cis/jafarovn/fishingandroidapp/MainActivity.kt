package edu.gvsu.cis.jafarovn.fishingandroidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
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
                val userViewModel: UserViewModel = viewModel()
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
                                userViewModel = userViewModel, // <-- Make sure this line is here
                                onNavigateToCaughtFish = { navController.navigate("AddFishScreen") },
                                onNavigateToLeaderBoard = { navController.navigate("LeaderBoardScreen") },
                                onNavigateToProfile = { navController.navigate("UserProfileScreen") }
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
                                userViewModel = userViewModel,
                                onNavigateToMain = { navController.navigate("HomeScreen") }
                            )
                        }
                        composable("UserProfileScreen") {
                            UserProfileScreen(
                                mapViewModel = mapViewModel,
                                userViewModel = userViewModel,
                                onNavigateToProfile = {navController.navigate("UserProfileScreen")},
                                onNavigateToMain = { navController.navigate("HomeScreen") }
                            )
                        }
                    }
                }
            }
        }
    }
}
