package com.dg.phonep.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dg.phonep.data.Joke
import com.dg.phonep.data.JokeRepoImpl
import com.dg.phonep.model.JokeModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    val jokerepo : JokeRepoImpl,
): ViewModel() {

    private val _uiState = MutableStateFlow(JokeModel(-1, "dummyJoke"))
    val uiState: StateFlow<JokeModel> = _uiState

    private val _uiStateFav = MutableStateFlow(mutableListOf<JokeModel>())
    val uiStateFav: StateFlow<MutableList<JokeModel>> = _uiStateFav

init {
    Log.d("shch", "init")
   fetchJoke()
}
    val listOfJokes = listOf(
        JokeModel(1, "hey" ,"hello" , "jokkk"),
        JokeModel(2, "hey33" ,"hello444" , "jokkk"),
        JokeModel(3, "hey323" ,"hellore" , "jokkk"),
        JokeModel(4, "hey4444" ,"hellore" , "jokkk")
    )

    var i =0
    fun fetchJoke()   = viewModelScope.launch(Dispatchers.IO) {
        kotlin.runCatching {
            //val joke = jokerepo.fetchJoke()
listOfJokes.forEach {

    jokerepo.addJoke(it)
}
            Log.d("shch", " fetched joke " + "joke")
        }.onFailure {
            Log.d("shch", "failure " + " joke  error " + it)
        }

    }


    fun showJoke() = viewModelScope.launch {
        if(i<= listOfJokes.size-1) {
            val joke = listOfJokes.get(i)
            _uiState.emit(joke)
            i++
        }

    }

    fun markFav(id: Int) = viewModelScope.launch(Dispatchers.IO){
        jokerepo.markfav(id)
    }

     fun fetchFav()  = viewModelScope.launch(Dispatchers.IO) {
         _uiStateFav.value = jokerepo.fetchFavs().toMutableList()
    }

}