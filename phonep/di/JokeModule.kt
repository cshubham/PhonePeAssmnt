package com.dg.phonep.di

import android.content.Context
import androidx.room.Room
import com.dg.phonep.data.Joke
import com.dg.phonep.data.JokeDao
import com.dg.phonep.data.RoomJokeDatabse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object JokeModule {
    @Joke
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(
            "https://v2.jokeapi.dev/joke/"
        ).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideRoomDb(
        @ApplicationContext  context: Context
    ) : RoomJokeDatabse {
        return Room.databaseBuilder(
            context = context.applicationContext,
            name = "JokeDB",
            klass = RoomJokeDatabse::class.java
        ).fallbackToDestructiveMigration().build()

    }

    @Provides
    fun getDao(room : RoomJokeDatabse) : JokeDao{
        return room.jokeDao()
    }

}