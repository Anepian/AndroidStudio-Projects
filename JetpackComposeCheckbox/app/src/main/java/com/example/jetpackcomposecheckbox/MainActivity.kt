package com.example.jetpackcomposecheckbox

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.leanback.widget.Row
import com.example.jetpackcomposecheckbox.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator() {
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Spacer(modifier = Modifier.height(5.dp))
        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )

        val items = listOf("Suma", "Resta", "Multiplicación")
        val checkboxStates = remember {mutableStateMapOf<String,Boolean>().withDefault{false}}

        Column {
            items.forEach { item ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkboxStates.getValue(item),
                        onCheckedChange = {
                            checkboxStates[item]=it
                        }
                    )
                    Text(
                        text = item,
                        // style = MaterialTheme.typography.body1.merge(),
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        Button(
            onClick = {
                val n1: Float? = num1.toFloatOrNull()
                val n2: Float? = num2.toFloatOrNull()
                if (n1 != null && n2 != null) {

                    var res: String? = ""

                    if(checkboxStates["Suma"]==true)
                        res += "$n1 + $n2 = ${n1 + n2}\n"
                    if(checkboxStates["Resta"]==true)
                        res += "$n1 - $n2 = ${n1 - n2}\n"
                    if(checkboxStates["Multiplicación"]==true)
                        res += "$n1 * $n2 = ${n1 * n2}"
                    result = if (res != null) "Resultado:\n $res" else "Por favor, seleccione al menos una operación."
                } else {
                    result = "Por favor, ingrese números válidos."
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Calcular")
        }

        if (result.isNotEmpty()) {
            Text(text = result)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable

fun TaskList() {

    var task by remember { mutableStateOf("") }

    val tasks = remember { mutableStateListOf<String>() }



    Column {

        TextField(

            value = task,

            onValueChange = { task = it },

            label = { Text("Tarea") },

            modifier = Modifier.fillMaxWidth()

        )



        Button(

            onClick = {

                if (task.isNotEmpty()) {

                    tasks.add(task)

                    task = ""

                }

            },

            modifier = Modifier.padding(top = 16.dp)

        ) {

            Text(text = "Agregar")

        }



        LazyColumn {

            items(tasks) { task ->

                Card(modifier = Modifier.padding(5.dp)){

                    Column(modifier = Modifier.padding(5.dp)) {

                        Row(modifier= Modifier.height(50.dp)

                            .fillMaxWidth()

                        ) {

                            Text(text = task)

                        }

                    }



                }



            }

        }

    }

}