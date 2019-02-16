package com.fourall.seedandroid.repository

import com.fourall.seedandroid.contract.UserDataContract
import com.fourall.seedandroid.contract.repository.UserRepository
import com.fourall.seedandroid.model.User
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class UserDataRepositoryTest {

    private lateinit var userRemoteDataSourceMock: UserDataContract.Remote

    private lateinit var userDataRepository: UserRepository

    @Before fun setUp() {

        userRemoteDataSourceMock = mock()

        userDataRepository = UserDataRepository(userRemoteDataSourceMock)
    }

    @Test fun `Get users, when I want to get users, then returns users list`() {

        // ARRANGE

        val USER_ID_1 = 1L
        val USER_NAME_1 = "zerenato"

        val USER_ID_2 = 2L
        val USER_NAME_2 = "ricardogalho"

        val user1 = User(USER_ID_1, USER_NAME_1)
        val user2 = User(USER_ID_2, USER_NAME_2)

        val expectedUsers = listOf<User>(user1, user2)

        val expectedDeferredUsers = CompletableDeferred(expectedUsers)

        runBlocking { whenever(userRemoteDataSourceMock.getUsers()).thenReturn(expectedDeferredUsers) }

        // ACT

        var users: List<User>? = null

        runBlocking { users = userDataRepository.getUsers().await() }

        // ASSERT

        assertEquals(expectedUsers, users)
    }
}