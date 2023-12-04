package com.example.locationapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import java.util.Locale

//context는 무엇???
//일종의 프로그램 전체에 해당되는 간략한 내용을 모두 담고 있는 것

class LocationUtils(val context : Context) {

    private val _fusedLocationClient : FusedLocationProviderClient
    = LocationServices.getFusedLocationProviderClient(context)
    
    @SuppressLint("MissingPermission")
    //위치 업데이트 함수
    fun requestLocationUpdates(viewModel: LocationViewModel){
        val locationCallback = object: LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                locationResult.lastLocation?.let{
                    val location = LocationData(latitude = it.latitude, longitude = it.longitude)
                    viewModel.updateLocation(location)
                }
            }
        }

        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 1000).build()
        //아래의 함수는 권한이 없을 때는 실행할 수 없는 함수여서 권한이 주어진 상태일 때에만 실행가능한 함수라고 위에 선언해두면...!
        //권한 관련 문제가 생길 수도 있음의 에러가 뜨는데 그 에러를 무시하는 것..
        _fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

    }

    //권한이 허용되었는지 확인하는 함수
    //또한 해당 권한을 물으려면 AndroidManifest.xml에 해당 권한을 사용할 것이라고 "선언"해두어야 함
    fun hasLocationPermission(context:Context):Boolean{
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }
    //위도와 경도를 사람이 읽을 수 있는 위치, 등의 정보로 변환하는 과정이 필요함!
    fun reverseGeocodeLocation(location: LocationData) : String{
        val geocoder = Geocoder(context, Locale.getDefault())
        //com.google.android.gms:play-services-maps부분을 gradle에 넣어야 LatLng()함수를 쓸 수 있음!

        val coordinate = LatLng(location.latitude, location.longitude)
        val addresses:MutableList<Address>? = geocoder.getFromLocation(coordinate.latitude, coordinate.longitude, 1)
        return if(addresses?.isNotEmpty() == true){
            addresses[0].getAddressLine(0)
        }else{
            "Address not found"
        }

    }

}