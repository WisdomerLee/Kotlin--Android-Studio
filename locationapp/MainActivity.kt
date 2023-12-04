package com.example.locationapp

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.locationapp.ui.theme.LocationAppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //viewModel을 넣으려면 종속성이 추가되어야 함
            //implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
            val viewModel: LocationViewModel = viewModel()
            LocationAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun MyApp(viewModel: LocationViewModel){
    //현재 실행중인 창!!으로 context를 지정하고
    val context = LocalContext.current
    val locationUtils = LocationUtils(context)

    LocationDisplay(locationUtils = locationUtils, viewModel, context = context)
}


@Composable
fun LocationDisplay(
    locationUtils: LocationUtils,
    viewModel: LocationViewModel,
    context: Context
){

    val location = viewModel.location.value

    val address = location?.let{
        locationUtils.reverseGeocodeLocation(location)
    }


    //프로그램 실행 시에 결과를 확인하고 저장한 뒤에 확인
    //권한 허가 요청들
    //다른 권한들 요청할 때도 큰 틀에서 보면 아래와 같음...

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if(permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
                && permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true){
                //위치에 접근하여 무언가를 처리하기!
                locationUtils.requestLocationUpdates(viewModel = viewModel)
            }else{
                //권한 묻기를 실행해야 함
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    //다른 창에서 열지 않고 지금 실행중인 창에서 열 것
                    context as MainActivity,
                    //
                    Manifest.permission.ACCESS_FINE_LOCATION
                )||ActivityCompat.shouldShowRequestPermissionRationale(
                    //다른 창에서 열지 않고 지금 실행중인 창에서 열 것
                    context as MainActivity,
                    //
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                if(rationalRequired){
                    //별도의 팝업 창
                    Toast.makeText(context, "LocationPermission is required for this feature to work", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(context, "LocationPermission is required Please enable it in the Android Settings", Toast.LENGTH_LONG).show()

                }
            }
        })


    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        if(location != null){
            Text(text = "Address: ${location.latitude} ${location.longitude} \n $address")
        }else{
            Text(text = "Location not available")
        }

        Button(onClick = { if(locationUtils.hasLocationPermission(context)){
            //권한이 이미 부여된 상태
            locationUtils.requestLocationUpdates(viewModel = viewModel)
        }else{
            //권한을 요청할 것!
            requestPermissionLauncher.launch(arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ))
        } }) {
            Text(text = "Get Location")
        }
    }
}