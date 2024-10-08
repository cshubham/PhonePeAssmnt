package com.dg.phonep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dg.phonep.ui.theme.PhonePTheme
import com.dg.phonep.vm.JokeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhonePTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel : JokeViewModel = hiltViewModel()) {

    val uiState = viewModel.uiState.collectAsState()
    val uiStateFav = viewModel.uiStateFav.collectAsState()

    Column(modifier = Modifier.padding(50.dp).fillMaxSize()) {

//    Text(
//        text = "Hello !",
//        modifier = Modifier
//            .padding(100.dp)
//            .size(100.dp)
//            .clickable {
//                viewModel.fetchJoke()
//            }
//    )
Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = "Show Joke",
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    viewModel.showJoke()
                }
        )

        Spacer(modifier = Modifier.size(10.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)) {
            Text(
                text = uiState.value.id.toString(),
                modifier = Modifier

            )
            Text(
                text = uiState.value.joke,
                modifier = Modifier

            )
Spacer(modifier = Modifier.size(50.dp))
            Text(
                text = "mark fav",
                modifier = Modifier.clickable {
                    viewModel.markFav(uiState.value.id)
                }

            )


        }


        Spacer(modifier = Modifier.size(10.dp))

        Text(
            text = "Fetch Fav",
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .clickable {
                    viewModel.fetchFav()
                }
        )


        Row(modifier = Modifier
            .size(100.dp)
            .background(Color.Blue)) {
            uiStateFav.value.forEach {
                Row {
                    Text(
                        text = uiState.value.id.toString(),
                        modifier = Modifier

                    )
                    Text(
                        text = uiState.value.joke.toString(),
                        modifier = Modifier

                    )
                }

            }




        }









    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    PhonePTheme {
//        Greeting("Android")
//    }
//}