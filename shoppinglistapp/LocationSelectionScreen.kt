package com.example.shoppinglistapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun LocationSelectionScreen(
    location: LocationData,
    onLocationSelected: (LocationData) -> Unit
){
    //현재 사용자의 위치를 기반으로...
    val userLocation = remember {
        mutableStateOf(LatLng(location.latitude, location.longitude))
    }
    //구글 지도에서 현재 위치를 찍어서 ~배 단위로 보여줄 것
    //아래의 코드는 10배 확대 상태임을 알려줌
    var cameraPositionState = rememberCameraPositionState(){
        position = CameraPosition.fromLatLngZoom(userLocation.value, 10f)
        
    }

    Column(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                //클릭한 위치를 현재 사용자 위치로 지정할 것!
                userLocation.value = it
            }
        ) {
            Marker(state = MarkerState(position = userLocation.value))
        }

        var newLocation : LocationData

        //버튼 클릭시 새 위치로 지도 위치 변경
        Button(onClick = {
            newLocation = LocationData(userLocation.value.latitude, userLocation.value.longitude)
            onLocationSelected(newLocation)
        }) {
            Text(text = "Set Location")
        }
    }
}