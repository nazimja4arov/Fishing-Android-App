package edu.gvsu.cis.jafarovn.fishingandroidapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel(){
    var users by mutableStateOf<Map<String, UserDataClass>> (emptyMap())

    var fish by mutableStateOf<Map<String, List<FishDataClass>>> (emptyMap())

    /// Used For Testing, Delete later
    val default_image = "android.resource://edu.gvsu.cis.jafarovn.fishingandroidapp/${R.drawable.bass_image}"
    init {
        AddUser("JT4", R.drawable.app_pfp, "John Turner", 4)
        AddFish("JT4", "Bass", default_image, 15, 2)
        //AddFish("JT4", "Salmon", R.drawable.bass_image, 17, 13)
        //AddFish("JT4", "Catfish", R.drawable.bass_image, 22, 28)
    }

    fun AddUser(user_name: String, user_pic: Int, user_full_name: String, user_rank: Int)
    {
        if (user_name !in users)
        {
            val new_entry = UserDataClass(user_name, user_pic, user_full_name,user_rank)
            users += (user_name to new_entry)
        }
    }

    fun AddFish(user_name: String, fish_name: String, fish_image: String, fish_length: Int, fish_weight: Int)
    {
        val current_fish_list = fish[user_name] ?: emptyList()
        val new_entry = FishDataClass(user_name, fish_name, fish_image,fish_length, fish_weight)
        fish += (user_name to (current_fish_list + new_entry))
    }
    fun GetUserFish(user_name: String): List<FishDataClass>
    {
        return fish[user_name] ?: emptyList()
    }
}