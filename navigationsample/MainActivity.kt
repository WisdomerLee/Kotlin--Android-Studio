package com.example.navigationsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationsample.ui.theme.NavigationSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationSampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp(){
    //이것을 쓰려면 navigation을 gradle쪽에 implementation해야 함
    //또한 navigation을 쓰려면 navigation controller가 필요!!
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "firstscreen"){
        composable("firstscreen"){
            FirstScreen {name->
                navController.navigate("secondscreen/$name")
            }
        }
        composable("secondscreen/{name}"){
            val name = it.arguments?.getString("name") ?: "no name"
            SecondScreen(name) {
                navController.navigate("firstscreen")
                //navController.navigate("thirdscreen")
            }
        }
        composable("thirdscreen"){
            ThirdScreen {
                navController.navigate("firstscreen")
            }
        }

    }
}