package com.fourall.seedandroid.repository

import com.fourall.seedandroid.contract.UserDataContract
import com.fourall.seedandroid.contract.repository.UserRepository
import com.fourall.seedandroid.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Response

class UserDataRepository(private val userRemoteDataSource: UserDataContract.Remote)
    : UserRepository {

    override suspend fun getUsers(): Deferred<Response<List<User>>> = userRemoteDataSource.getUsers()
}