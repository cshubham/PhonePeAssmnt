package com.dg.phonep.data

import com.dg.phonep.model.JokeModel

interface JokeRepoInterface {
    suspend fun fetchJoke() : JokeModel
    suspend fun delete(id: Int)
    suspend fun markfav(id: Int)
    suspend fun addJoke(joke: JokeModel)
    suspend fun fetchFavs() : List<JokeModel>
}