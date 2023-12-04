package com.example.navigationsample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//익명함수나 람다식을 통해 다음으로 넘어가야 할 함수의 내용들을 Composable쪽에 미리 전달!
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstScreen(navigateToSecondScreen:(String) -> Unit){
    val name = remember {
        mutableStateOf("")
    }
    val age = remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "This is the First Screen", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = name.value, onValueChange = {
            name.value = it
        })
        OutlinedTextField(value = age.value.toString(), onValueChange = {
            age.value = it.toInt()
        })
        Button(onClick = {
            navigateToSecondScreen(name.value)
        }) {
            Text(text = "Go to Second Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstPreview(){
    FirstScreen({})
}