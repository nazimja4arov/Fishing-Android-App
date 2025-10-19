package edu.gvsu.cis.jafarovn.fishingandroidapp
import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object AddFishScreen

    @Serializable
    data object HomeScreen

    @Serializable
    data object LeaderBoardScreen

    @Serializable
    data object UserProfileScreen
}
