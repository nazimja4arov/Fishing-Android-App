package edu.gvsu.cis.jafarovn.fishingandroidapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(
    private val repo: FishRepository
) : ViewModel() {

    private val currentUserId = "Guest"
    private val basePoints = 0

    var users by mutableStateOf<Map<String, UserDataClass>>(emptyMap())
        private set

    var fish by mutableStateOf<Map<String, List<FishDataClass>>>(emptyMap())
        private set

    val defaultImage: String =
        "android.resource://edu.gvsu.cis.jafarovn.fishingandroidapp/${R.drawable.bass_image}"

    init {
        seedData()
        loadFish()
    }

    fun getCurrentUser(): UserDataClass? = users[currentUserId]

    fun getCurrentUserFish(): List<FishDataClass> = fish[currentUserId] ?: emptyList()

    fun addFish(name: String, image: String?, length: Int, weight: Int) {
        val img = image ?: defaultImage

        val newEntry = FishDataClass(
            userName = currentUserId,
            fishName = name,
            fishImage = img,
            fishLength = length,
            fishWeight = weight
        )

        val updatedList = getCurrentUserFish() + newEntry
        fish = fish + (currentUserId to updatedList)

        val user = getCurrentUser()
        if (user != null) {
            val newPoints = user.userPoints + length + weight * 2
            users = users + (currentUserId to user.copy(userPoints = newPoints))
        }

        viewModelScope.launch {
            repo.saveFish(
                FishEntity(
                    userId = currentUserId,
                    name = name,
                    length = length,
                    weight = weight,
                    image = img
                )
            )
        }
    }

    private fun loadFish() {
        viewModelScope.launch {
            val list = repo.loadFish(currentUserId)

            val mapped = list.map {
                FishDataClass(
                    userName = it.userId,
                    fishName = it.name,
                    fishImage = it.image,
                    fishLength = it.length,
                    fishWeight = it.weight
                )
            }

            fish = fish + (currentUserId to mapped)

            val extraPoints = mapped.sumOf { it.fishLength + it.fishWeight * 2 }

            val baseUser = users[currentUserId]
            if (baseUser != null) {
                val updatedUser = baseUser.copy(userPoints = basePoints + extraPoints)
                users = users + (currentUserId to updatedUser)
            }
        }
    }

    fun resetCurrentUserData() {
        viewModelScope.launch {
            repo.clearFishForUser(currentUserId)
        }

        fish = fish - currentUserId

        val user = users[currentUserId]
        if (user != null) {
            users = users + (currentUserId to user.copy(userPoints = basePoints))
        }
    }

    private fun seedData() {
        users = users + ("Guest" to UserDataClass("Guest", R.drawable.app_pfp, "Guest User", basePoints))
        users = users + ("TARA" to UserDataClass("Tara", R.drawable.app_pfp, "Tara Barnett", 80))
        users = users + ("ZEKE" to UserDataClass("Zeke", R.drawable.app_pfp, "Zeke Turnbough", 120))
        users = users + ("NAZIM" to UserDataClass("Nazim", R.drawable.app_pfp, "Nazim Jafarov", 90))
    }
}
