package com.example.shoppinglistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglistapp.ui.theme.ShoppingListAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
    fun checkOperate(){
        val numbers = listOf(1,2,3)
        val doubled = numbers.map{ it *2} //그러면 결과는 2배씩 처리된 목록이 나옴
        //map은 list등의 목록의 원소들에 모두 적용되어 반복적으로 실행됨
        val blueRoseVase = Vase(color = "Blue", design = "Rose")
        val redRoseVase = blueRoseVase.copy(color= "Red")
        //copy() 이대로 쓰면 모든 속성이 그대로
        //copy(바꾸고 싶은 속성 = 바꿀 값)이렇게 처리하면 모든 값은 그대로에 바꾸고 싶은 속성만 바뀌어 진행
    }
}

data class Vase(val color: String, val design: String)
//shopping list app이 필요

//google maps예시를 쓰려면 google maps api 활성화가 필요함
//
//또한 api key를 설정할 때 특정 프로그램으로만 api를 접근하게 하려면...?
//
//package이름을 등록하고(com.~~.~~~)
//gradle 중에(오른쪽의 코끼리 아이콘) : 맨 왼쪽의 Run anything을 눌러
//gradle signingreport를 실행할 것
//그러면 android debugkey가 여럿 생성되는데 그 중에 SHA1이라고하는 것을 복사하여 구글 키 만들기에 집어넣을 것

//api 키를 어떻게 확인하느냐??
//key&Credentials 쪽을 눌러보면 api key를 확인할 수 있음!

//또한 맵의 지도 위치를 확정하려면 Geocoding API도 열어야 함!
//

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val viewModel: LocationViewModel = viewModel()
    val context = LocalContext.current
    val locationUtils = LocationUtils(context)

    NavHost(navController = navController, startDestination = "shoppinglistscreen")
    {
        composable("shoppinglistscreen"){
            ShoppingListApp(
                locationUtils = locationUtils,
                viewModel = viewModel,
                navController = navController,
                context = context,
                address = viewModel.address.value.firstOrNull()?.formatted_address ?: "no address"
            )
        }
        dialog("locationscreen"){
            backstack -> viewModel.location.value?.let{
                it1 -> LocationSelectionScreen(location = it1, onLocationSelected = {locationdata->
                    viewModel.fetchAddress("${locationdata.latitude}, ${locationdata.longitude}")
            navController.popBackStack()
        })
        }
        }
    }
}
