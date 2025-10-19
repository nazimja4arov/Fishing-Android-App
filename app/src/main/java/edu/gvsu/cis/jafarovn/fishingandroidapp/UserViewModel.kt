package edu.gvsu.cis.jafarovn.fishingandroidapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel(){
    var users by mutableStateOf<Map<String, UserDataClass>> (emptyMap())



    fun AddUser(user_name: String, user_pic: Int)
    {
        if (user_name !in users)
        {
            val new_entry = UserDataClass(user_name, user_pic)
            users += (user_name to new_entry)
        }
    }



    /*fun GetUser(user_name: String) User
    {
        return users[user_name]
    }*/
}