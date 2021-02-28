package com.openclassrooms.magicgithub.repository

import com.openclassrooms.magicgithub.api.ApiService
import com.openclassrooms.magicgithub.model.User

class UserRepository(private val apiService: ApiService) {

    val users: List<User>
        get() {
            return apiService.getUsers()
        }

    fun generateRandomUser() {
        apiService.generateRandomUser()
    }

    fun deleteUser(user: User?) {
        if (user != null) {
            apiService.deleteUser(user)
        }
    }
}
