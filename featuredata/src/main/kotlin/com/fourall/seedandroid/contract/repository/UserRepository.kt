package com.fourall.seedandroid.contract.repository

import com.fourall.seedandroid.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface UserRepository {

    suspend fun getUsers(): Deferred<Response<List<User>>>
}