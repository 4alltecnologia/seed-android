package com.fourall.seedandroid.remote.endpoint

import com.fourall.seedandroid.model.User
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserApiClient {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    ) @GET("5c732f853300005600760266") fun getUsers(): Deferred<Response<List<User>>>
}