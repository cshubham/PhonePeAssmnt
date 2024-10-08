package com.dg.phonep.data

import com.dg.phonep.model.JokeModel
import retrofit2.http.GET

interface CallInteface {
    @GET("Any/")
    suspend fun callJoke(): JokeModel
}