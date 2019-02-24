package com.fourall.seedandroid.contract

import com.fourall.seedandroid.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface UserDataContract {

    interface Remote {

        suspend fun getUsers(): Deferred<Response<List<User>>>
    }
}