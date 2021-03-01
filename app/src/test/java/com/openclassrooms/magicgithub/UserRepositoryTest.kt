package com.openclassrooms.magicgithub

import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator
import com.openclassrooms.magicgithub.di.Injection.createUserRepository
import com.openclassrooms.magicgithub.model.User
import com.openclassrooms.magicgithub.repository.UserRepository
import org.hamcrest.collection.IsIterableContainingInAnyOrder
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.stream.Collectors

/**
 * Unit test, which will execute on a JVM.
 * Testing UserRepository.
 */
@RunWith(JUnit4::class)
class UserRepositoryTest {
    private var userRepository: UserRepository? = null
    private val FAKE_USERS: List<User> = FakeApiServiceGenerator.FAKE_USERS
    private val FAKE_USERS_RANDOM = FakeApiServiceGenerator.FAKE_USERS_RANDOM

    @Before
    fun setup() {
        userRepository = createUserRepository()
    }

    @Test
    fun getUserWithSuccess(){
        val usersActual = userRepository!!.users
        val usersExpected = FAKE_USERS
        Assert.assertThat(usersActual, IsIterableContainingInAnyOrder.containsInAnyOrder<Any>(*usersExpected.toTypedArray()))
    }

    @Test
    fun generateRandomUserWithSuccess() {
        userRepository!!.users.clear()
        userRepository!!.generateRandomUser()
        val (id, login, avatarUrl) = userRepository!!.users[0]
        Assert.assertEquals(21, userRepository!!.users.size.toLong())
        Assert.assertTrue(FAKE_USERS_RANDOM.stream().map(User::avatarUrl).collect(Collectors.toList()).contains(avatarUrl))
        Assert.assertTrue(FAKE_USERS_RANDOM.stream().map(User::id).collect(Collectors.toList()).contains(id))
        Assert.assertTrue(FAKE_USERS_RANDOM.stream().map(User::login).collect(Collectors.toList()).contains(login))
    }

    @Test
    fun deleteUserWithSuccess() {
        val userToDelete = userRepository!!.users[0]
        userRepository!!.deleteUser(userToDelete)
        Assert.assertFalse(userRepository!!.users.contains(userToDelete))
    }
}