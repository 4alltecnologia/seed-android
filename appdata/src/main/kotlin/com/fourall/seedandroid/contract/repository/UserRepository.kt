package com.fourall.seedandroid.contract.repository

import com.fourall.seedandroid.model.User
import kotlinx.coroutines.Deferred

interface UserRepository {

    suspend fun getUsers(): Deferred<List<User>>
}