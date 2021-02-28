package com.openclassrooms.magicgithub.repository

import com.openclassrooms.magicgithub.api.ApiService
import com.openclassrooms.magicgithub.model.User

class UserRepository(private val apiService: ApiService) {

    val users: MutableList<User>
        get() {
            return apiService.getUsers() as MutableList<User>
        }

    /**
     * Add a random generated user to users list
     */
    fun generateRandomUser() {
        apiService.generateRandomUser()
    }

    /**
     * Delete a specific user
     */
    fun deleteUser(user: User?) {
        if (user != null) {
            apiService.deleteUser(user)
        }
    }
}
