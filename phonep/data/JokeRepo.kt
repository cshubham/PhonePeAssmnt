package com.dg.phonep.data

import com.dg.phonep.model.JokeModel
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Qualifier

class JokeRepoImpl @Inject constructor(
    @Joke val retrofit: Retrofit,
    val jokeDao: JokeDao
) : JokeRepoInterface {
    lateinit var callInstance : CallInteface
    //lateinit var jokeDao : JokeDao
    init {
      callInstance = retrofit.create(CallInteface::class.java)
      //  jokeDao = roomJokeDatabse.jokeDao()
    }

    override suspend fun fetchJoke(): JokeModel {
        return callInstance.callJoke()
    }

    override suspend fun delete(id: Int) {
        jokeDao.delete(id)
    }

    override suspend fun markfav(id: Int) {
        val joke = jokeDao.getJoke(id)
        joke.isFav = true
        jokeDao.add(joke)
    }

    override suspend fun addJoke(joke: JokeModel) {
        jokeDao.add(joke.toEntity())
    }

    override suspend fun fetchFavs(): List<JokeModel> {
        return jokeDao.fetchFav().map { it.toJoke() }
    }

}



@Qualifier
@Retention(AnnotationRetention.BINARY)
 annotation class Joke


fun JokeEntity.toJoke() : JokeModel {
    return JokeModel(id = this.id, joke = this.joke, fav = isFav)
}

fun JokeModel.toEntity() : JokeEntity {
    return JokeEntity(this.id, this.joke, this.fav ?: false)
}