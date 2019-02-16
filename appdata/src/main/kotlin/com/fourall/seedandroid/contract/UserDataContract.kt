package com.fourall.seedandroid.contract

import com.fourall.seedandroid.model.User
import kotlinx.coroutines.Deferred

interface UserDataContract {

    interface Remote {

        suspend fun getUsers(): Deferred<List<User>>
    }
}