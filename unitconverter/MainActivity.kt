package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //onCreate 함수의 기본 내용을 실행하고!
        super.onCreate(savedInstanceState)
        //그 뒤에 덮어쓰는 추가 내용이 아래에 있음
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme : 바탕화면
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


// Composable은 import androidx.compose.runtime.Composable을 먼저 선언해두어야 하고
//또한 화면에 보이는 UI쪽에 관여하는 것
//그리고 composable도 역시 함수의 일부
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(){

    //내부 state들 여기에 두기!
    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var inputUnit by remember {
        mutableStateOf("Meters")
    }
    var outputUnit by remember {
        mutableStateOf("Meters")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    val iConversionFactor = remember{ mutableStateOf(1.0) }
    val oConversionFactor = remember {
        mutableStateOf(1.0)
    }

    val customTextStyle = androidx.compose.ui.text.TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        color = Color.Red
    )

    //유닛 변환 함수 : 일단 모두 meter로 바꾼뒤 그 meter를 출력 단위로 변경!
    fun convertUnits(){
        //?: - elvis operator
        //해당 값이 null이면 : 뒤의 값을 집어넣음

        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        //toDoubleOrNull(): 문자열의 값을 숫자 혹은 null로 반환함!
        //문자열의 값이 숫자가 아닌 경우엔 null이 됨
        val result = (inputValueDouble * iConversionFactor.value * 100/ oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()

    }

    Column(
        //UI들을 그냥 composable들로 두면 화면의 구석 등에 배치되어있음
        //이를 화면의 어느 쪽에 둘 것인지를 설정할 수 있는데
        //modifier = 크기를 결정할 수 있음, 가로로 꽉 채울지, 세로로 채울지, 전체를 채울지
        modifier = Modifier.fillMaxSize(),
        //세로 위치 : vertical
        verticalArrangement = Arrangement.Center,
        //가로 위치 : horizontal
        horizontalAlignment = Alignment.CenterHorizontally


    ){
        //여기에 쌓인 UI 요소들은 스택처럼 쌓여 보이게 됨 : 위에서 아래로
        //또한 아무 조건없이 그냥 여러 요소들이 들어가면 UI요소들이 딱 붙어 있게 됨
        //
        Text("Unit Converter", style = customTextStyle)
        //UI들 간의 위치를 띄우는데 padding이라는 것이 있고
        //spacer라는 것이 있는데
        //padding은 각 UI마다 개별적으로 들어가는 것
        //spacer: 공통으로 적용되는 것이라고 보면 됨 : 재사용성이 좋음
        Spacer(modifier = Modifier.height(16.dp))
        //사용자가 입력가능한 입력 창은 TextField, BasicTextField, OutlinedTextField 세 종류가 있음
        OutlinedTextField(value = inputValue, onValueChange = {
            //텍스트 값이 바뀌면 일어나야 할 함수들이라던가 등등의 내용이 들어가야 함
            inputValue = it
            convertUnits()
        },
            label = {Text("Enter value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row{
            //여기에 쌓이면 바로 옆에 차곡차곡 쌓이게 됨
            //왼쪽에서 오른쪽으로 보이게 됨!

            //context는 어플리케이션의 내용? 같은 것??
            //Button : 클릭할 수 있는 UI
            //onClick = : 버튼에서 클릭이 실행되면 실행될 것
            //Toast.makeText : 문자 메시지를 보여줄 것..

            //Dropdown을 만들어 보기
            //input box
            Box{
                Button(onClick = { iExpanded = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                //dropdownmenu에서 onDismissRequest = : 이 부분은 드롭다운이 열렸을 때 다른 곳을 클릭하거나 할 경우 발생할 이벤트
                //대개 dropdownmenu를 닫을 것이기 때문에...


                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            //입력된 값이 제대로된 값인지 확인
                            //열린 메뉴 닫기(dropdown)
                            iExpanded = false
                            inputUnit = "Centimeters"
                            iConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Meters"
                            iConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            iConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milimeters"
                            iConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }
            //처음에 dropdown menu가 열려있는지를 결정할 수 있음
            //DropdownMenu{}안에 DropdownMenuItem을 넣어 dropdown이 확장되면 보이게 될 메뉴아이콘들을 설정할 수 있음
            Spacer(modifier = Modifier.width(16.dp))
            //output box
            Box{
                Button(onClick = { oExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Meters"
                            oConversionFactor.value = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Milimeters"
                            oConversionFactor.value = 0.001
                            convertUnits()
                        }
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(16.dp))
        //Text에 style을 넣으면 ? 해당 폰트, 크기 등으로 변경됨
        Text("Result: $outputValue $outputUnit",
            style = MaterialTheme.typography.headlineMedium
            )
    }
}

//프리뷰는 미리보고 싶은 UI 변화를 전체 코드 실행 없이 바로 미리 볼 수 있음!
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}

//UI에서 State가 중요한데
//UI를 통해 state를 변화시키고 역으로 그 값을 통해 UI로 상태가 바뀐 것을 사용자가 인지해야 하기 때문

//UI에 텍스트를 띄우고 클릭하는 방향이 있어서 지시를 하고 등의 내용을 집어넣을 땐

@Composable
fun CaptainGame(){
    val treasuresFound = remember{ mutableStateOf(0) }
    //val은 변화하지 않는 값인데 아래에 보면 value값이 바뀜
    //val은 변하지 않는 것은 맞으나 mutableStateOf()로 remember를 넣어두면
    //변수 자체에 상자를 넣어두는 것
    //상자 자체는 바뀌지 않으나 상자 속에 든 내용이 계속 바뀌는 것으로 이해하면 된다

    val direction = remember {
        mutableStateOf("North")
    }

    val stormOrTreasure = remember{ mutableStateOf("") }
    //state를 쓰는 다른 방법도 있음!
    val treasuresFoundOther by remember{ mutableStateOf(0) }
    //위와 같이 처리하면 .value 없이 그 값을 바로 불러올 수 있음


    Column{
        Text(text="Treasures Found: ${treasuresFound.value}")
        Text(text="Current Direction: ${direction.value}")
        Text(text = stormOrTreasure.value)

        Button(onClick = {
            direction.value = "East"
            //아래의 Random.nextBoolean() : 50% 확률로 참, 거짓이 됨
            if(Random.nextBoolean()){
                treasuresFound.value += 1
                stormOrTreasure.value = "Found a Treasure!"
            }
            else{
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "Sail East")
        }
        Button(onClick = {
            direction.value = "West"
            //아래의 Random.nextBoolean() : 50% 확률로 참, 거짓이 됨
            if(Random.nextBoolean()){
                treasuresFound.value += 1
                stormOrTreasure.value = "Found a Treasure!"
            }
            else{
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "Sail West")
        }
        Button(onClick = {
            direction.value = "North"
            //아래의 Random.nextBoolean() : 50% 확률로 참, 거짓이 됨
            if(Random.nextBoolean()){
                treasuresFound.value += 1
                stormOrTreasure.value = "Found a Treasure!"
            }
            else{
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "Sail North")
        }
        Button(onClick = {
            direction.value = "South"
            //아래의 Random.nextBoolean() : 50% 확률로 참, 거짓이 됨
            if(Random.nextBoolean()){
                treasuresFound.value += 1
                stormOrTreasure.value = "Found a Treasure!"
            }
            else{
                stormOrTreasure.value = "Storm Ahead!"
            }
        }) {
            Text(text = "Sail South")
        }
    }
}

