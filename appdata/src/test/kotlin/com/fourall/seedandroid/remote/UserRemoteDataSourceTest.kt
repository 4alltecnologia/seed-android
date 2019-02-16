package com.fourall.seedandroid.remote

import com.fourall.seedandroid.model.User
import com.fourall.seedandroid.remote.endpoint.UserApiClient
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class UserRemoteDataSourceTest {

    private lateinit var userApiClientMock: UserApiClient

    private lateinit var userRemoteDataSource: UserRemoteDataSource

    @Before fun setUp() {

        userApiClientMock = mock()

        userRemoteDataSource = UserRemoteDataSource(userApiClientMock)
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

        whenever(userApiClientMock.getUsers()).thenReturn(expectedDeferredUsers)

        // ACT

        var users: List<User>? = null

        runBlocking { users = userRemoteDataSource.getUsers().await() }

        // ASSERT

        assertEquals(expectedUsers, users)
    }
}