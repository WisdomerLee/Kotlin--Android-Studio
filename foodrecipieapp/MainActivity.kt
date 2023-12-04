package com.example.foodrecipieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.foodrecipieapp.ui.theme.FoodRecipieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            FoodRecipieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RecipeApp(navController = navController)

                }
            }
        }
    }
}

//통합된 표준화 : 중에 하나로 Json을 씀
//Json(Javascript object notation) : 간단, 누구나 알 수 있음, 가벼움, 중첩된 내용을 알 수 있음
//"key" : "value"형태로 되어있음 : dictionary 형태
//Jsonformatter.org라는 홈페이지를 통해 data를 Json format의 형태로 바꿀 수 있음!
//Json은 기본적으로 문자열로 정의되는데 문자열의 경우는 head가 간단한 편이라고 함...


//<editor-fold desc="Description">
//data class Potion(val name: String, val effect: String, val potency: Int)
//fun main(){
//    val healingPotion = Potion("Healing Potion", "Restore Health", 50)
//    val invisibilityPotion = Potion("Invisibility Potion", "Grants Invisibility", 40)
//    val strengthPotion = Potion("Strength Potion", "Boosts Strength", 60)
//    val potionList = listOf(healingPotion, invisibilityPotion, strengthPotion)
//    println(potionList)
//
//}
//</editor-fold>

//themealdb.com이라는 홈페이지에서 api를 통해 음식 만드는 법을 Json파일로 받을 수 있음

//Gradle Scripts쪽에
//build.gradle.kts (Module:app)쪽에 보면 dependencies가 있는데 이 부분이 현재 응용 어플리케이션에서 쓰고 있는 라이브러리를 나타냄
//    //Compose ViewModel
//    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
//    //Network call
//    implementation("com.squareup.retrofit2:retrofit:2.9.0")
//    //Json to Kotlin object mapping
//    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
//    //Image loading
//    implementation("io.coil-kt:coil-compose:2.5.0")
//등등의 내용을 추가하고나서 sync gradle을 누르자!

//meal json 파일을 분석하면 : 음식 id, 음식 종류, 음식 설명, 음식 만들기 등의 내용으로 나뉨 : 해당내용은 data class의 category로 구현!

//suspend function characteristics
//non-blocking, coroutine context, flexibility

//Internet permission등을 더하고 어플리케이션을 실행하기!
//