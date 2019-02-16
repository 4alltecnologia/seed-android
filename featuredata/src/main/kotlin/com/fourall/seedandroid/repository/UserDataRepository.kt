package com.fourall.seedandroid.repository

import com.fourall.seedandroid.contract.UserDataContract
import com.fourall.seedandroid.contract.repository.UserRepository
import com.fourall.seedandroid.model.User
import kotlinx.coroutines.Deferred

class UserDataRepository(private val userRemoteDataSource: UserDataContract.Remote)
    : UserRepository {

    override suspend fun getUsers(): Deferred<List<User>> = userRemoteDataSource.getUsers()
}