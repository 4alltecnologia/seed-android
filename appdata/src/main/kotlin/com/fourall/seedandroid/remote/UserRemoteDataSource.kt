package com.fourall.seedandroid.remote

import com.fourall.seedandroid.contract.UserDataContract
import com.fourall.seedandroid.model.User
import com.fourall.seedandroid.remote.endpoint.UserApiClient
import kotlinx.coroutines.Deferred

class UserRemoteDataSource(private val userApiClient: UserApiClient) : UserDataContract.Remote {

    override suspend fun getUsers(): Deferred<List<User>> = userApiClient.getUsers()
}