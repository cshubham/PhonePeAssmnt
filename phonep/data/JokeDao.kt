package com.dg.phonep.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface JokeDao {
    @Upsert
    fun add(joke: JokeEntity)

    @Query("SELECT * from joke where id =:id")
    fun getJoke(id: Int) : JokeEntity

    @Query("SELECT * from joke")
    fun getAll() : List<JokeEntity>

    @Query("DELETE from joke where id = :id")
    fun delete(id: Int)

    @Query("SELECT * from joke where isFav = :isFav")
    fun fetchFav(isFav : Boolean = true) : List<JokeEntity>
}