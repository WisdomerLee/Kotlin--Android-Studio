package com.example.bankaccount

class Basics{
    fun CheckOperate(){
        //변경 불가 리스트 : 초기에 지정한 값 외에는 추가될 수 없음!
        val shoppingList = listOf("Processor", "RAM", "Graphics Card", "SSD")
        // 변경 가능 리스트 : 아이템을 더하거나 뺄 수 있음
        val shoppingListmutable = mutableListOf("Processor", "RAM", "Graphics Card RTX 3060", "SSD")

        //리스트에 아이템 더하기
        shoppingListmutable.add("Cooling System") //이 함수는 리스트의 맨 뒤에 집어넣음

        //리스트에 아이템 지우기
        shoppingListmutable.remove("Graphics Card RTX 3060")
        shoppingListmutable.add("Graphics Card RTX 4090")

        //리스트를 출력하게 하면 리스트의 모든 내용이 출력!
        println(shoppingListmutable)
        shoppingListmutable.removeAt(2) //n번째에 해당되는 목록을 지움!
        shoppingListmutable.add(2, "RAM") //n번째에 해당되는 곳에 집어넣기!
        //
        shoppingListmutable[3] = "Graphics Card RX 7800XT" //n번째에 해당되는 원소 교체!
        //
        shoppingListmutable.set(1, "Water Cooling") //n번째에 해당되는 원소교체 : 위의 것과 같은 효과
        //
        val hasRam = shoppingListmutable.contains("RAM") //해당 원소가 리스트에 포함되어있는지 확인
        if(hasRam){
            println("Has RAM in the list")
        }else{
            println("No RAM in the list")
        }
        //while의 반복도 있지만 for 반복도 있음!
        //특정 리스트의 원소들을 차례로 끌어들여
        for(item in shoppingListmutable){
            println(item)
            //만약 특정 원소가 어떤 조건이 되면
            if(item == "RAM"){
                //그외 다양한 함수 등을 넣을 수 있음
                break //반복을 멈춤
            }
        }
        //위의 경우는 리스트를 반복 출력하거나 할 때 좋음. 그런데 만약 몇 번째에 해당되는 부분을 바꾸거나 하고 싶다면...?
        //숫자1 until 숫자2 : 반복을 해당 숫자1부터 숫자2-1까지 반복
        //아래의 경우는 0번부터 (즉 리스트의 처음부터 size 크기-1만큼-끝까지) 반복할 것 : 몇번째에 해당할 때 등의 조건을 넣을 수 있음
        for(index in 0 until shoppingListmutable.size){
            println(index) //해당 항목들의 index가 반환됨
            println("item ${shoppingListmutable[index]} is at index $index")
            //index를 통한 접근이 가능

        }
        //혹은 아래와 같이 숫자1 .. 숫자2와 같은 형태로도 쓸 수 있음 : 아래는 0부터 6까지 7번의 반복이 진행됨 :여기는 숫자2까지도 포함되므로 주의
        for(index in 0 .. 6){

        }

    }
}
