package com.example.shoppinglistapp

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController

data class ShoppingItem(
    val id:Int, var name: String,
    var quantity:Int, var isEditing: Boolean = false,
    var address: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp(
    locationUtils: LocationUtils,
    viewModel: LocationViewModel,
    navController: NavController,
    context: Context,
    address: String
){

    var sItems by remember{ mutableStateOf(listOf<ShoppingItem>()) }

    var showDialog by remember { mutableStateOf(false) }

    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

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
                )|| ActivityCompat.shouldShowRequestPermissionRationale(
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



    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.CenterHorizontally)

        ) {
            val doubleNummer: (Int) -> Int = { it*2 }
            //람다 : (입력 데이터 타입들) -> 돌려주는 데이터 타입들 {그리고 그 함수의 내용}
            //it은 입력된 값을 의미함
            //또한 () -> Unit으로 처리된 람다는 입력도 없고 출력도 없는 함수
            //
            Text(text = "Add Item")
        }
        //LazyColumn은 아이템이 몹시 많을 경우 화면에 보이는 일부 목록만 먼저 보여주는 형태
        //
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ){
            items(sItems){
                item ->
                if(item.isEditing){
                    ShoppingItemEditor(item = item, onEditComplete = {
                        editedName, editedQuantity ->
                        sItems = sItems.map{it.copy(isEditing = false)}
                        val editedItem = sItems.find {it.id == item.id}
                        editedItem?.let{
                            it.name = editedName
                            it.quantity = editedQuantity
                            it.address = address
                        }
                    })
                }else{
                    //어느 아이템이 편집중인지 확인!
                    ShoppingListItem(item = item, onEditClick = { sItems = sItems.map{it.copy(isEditing = it.id ==item.id)} },
                        onDeleteClick = {
                            sItems = sItems-item
                        })
                }

            }
        }
    }
    if(showDialog){
        //알람 다이알로그는 별도의 UI!
        //
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween) {
                                Button(onClick = {
                                //아이템 이름이 빈 칸이 아니라면
                                    if(itemName.isNotBlank()){
                                        val newItem = ShoppingItem(
                                            id = sItems.size+1,
                                            name = itemName,
                                            quantity = itemQuantity.toInt(),
                                            address = address
                                        )
                                        sItems = sItems + newItem
                                        showDialog= false
                                        itemName = ""

                                    }
                                }) {
                                    Text("Add")
                                }
                                Button(onClick = { /*TODO*/ }) {
                                    Text("Cancel")
                                }
                            }
                            },
            title = { Text("Add Shopping Item")},
            text = {
                Column(content = {

                    OutlinedTextField(
                        value = itemName, onValueChange = { itemName = it },
                        singleLine = true, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    OutlinedTextField(
                        value = itemQuantity, onValueChange = { itemQuantity = it },
                        singleLine = true, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    )
                    Button(onClick = { /*TODO*/ }) {
                        if(locationUtils.hasLocationPermission(context)){
                            locationUtils.requestLocationUpdates(viewModel)
                            navController.navigate("locationscreen"){
                                //navigation에 navigate를 넣으면 메모리 한계까지 계속 불필요하게 늘어날 수 있으므로
                                //아래와 같이 수정하는 것 : 하나만 남겨두고 나머지는 nav에서
                                this.launchSingleTop
                            }
                        }
                        else{
                            requestPermissionLauncher.launch(arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                            ))
                        }
                    }
                })
            }
            )
    }
}

@Composable
fun ShoppingItemEditor(item: ShoppingItem, onEditComplete: (String, Int)-> Unit){
    var editedName by remember {
        mutableStateOf(item.name)
    }
    var editedQuantity by remember {
        mutableStateOf(item.quantity.toString())
    }
    var isEditing by remember {
        mutableStateOf(item.isEditing)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly){
        Column {
            BasicTextField(value = editedName, onValueChange = {editedName=it},
                singleLine = true,
                //정해진 크기 내부에서 컨텐츠 크기 자체 조절
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
            BasicTextField(value = editedQuantity, onValueChange = {editedQuantity=it},
                singleLine = true,
                //정해진 크기 내부에서 컨텐츠 크기 자체 조절
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            )
        }
        Button(onClick = {
            isEditing = false
            onEditComplete(editedName, editedQuantity.toIntOrNull() ?: 1)
        }) {
            Text(text = "Save")
        }
    }

}


@Composable
fun ShoppingListItem(
    item: ShoppingItem,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
){
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(2.dp, Color(0XFF018786)),
                shape = RoundedCornerShape(20)
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(modifier = Modifier
            .weight(1f)
            .padding(8.dp)) {
            Row{
                Text(text = item.name, modifier = Modifier.padding(8.dp))
                Text(text = "Qty: ${item.quantity}", modifier = Modifier.padding(8.dp))
            }
            Row(modifier= Modifier.fillMaxWidth()){
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                Text(text = item.address)
            }
        }

        Row(modifier = Modifier.padding(8.dp)){
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }

    }
}

//shoppinglist
fun ChecktheLet(){
    //null값을 가질 수도 있는 문자열을 설정
    val name: String? = "Ella"
    name?.let{
        println(it.toUpperCase())
    }
    //let의 경우 : nullable의 경우 null이 아닌 것들만 let 내부로 들어가 처리된다는 특징이 있음
    //또한 반환의 형태가 다 달라질 수 있음! let 내부에서 처리하는 방법에 따라...
    //
}