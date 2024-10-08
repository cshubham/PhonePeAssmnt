package com.dg.phonep.data

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase


@Database(
    entities = [JokeEntity::class],
    version = 2,
    exportSchema = false
)
abstract class RoomJokeDatabse : RoomDatabase() {
    abstract fun jokeDao(): JokeDao
}




@Entity(tableName = "joke")
data class JokeEntity(
    @PrimaryKey
    val id: Int,
    val joke : String,
    var isFav : Boolean = false
)