package com.example.gorjeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorjeta.ui.theme.GorjetaTheme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GorjetaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    var valor = remember {
        mutableStateOf(0.00)
    }

    var gorjetaVar = remember {
        mutableStateOf(0f)
    }

    val df = DecimalFormat("#.##")

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth() ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Valor R$")

            OutlinedTextField(
                value = valor.value.toString(),
                onValueChange = {
                    valor.value = it.toDouble()
                },
                label = {
                    Text(text = "Valor R$")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth() ,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Porcentagem %")

            Slider(
                value = gorjetaVar.value,
                onValueChange = { gorjetaVar.value = it },
                valueRange = 15f..100f,
                steps = 86,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(text = "            ")
            Text(text = "15%")

            Text(
                text = "${gorjetaVar.value.toInt()}%",
                textAlign = TextAlign.Center
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(text = "Gorjeta R$")

            Text(
                text = "${df.format(calcularGorjeta15(valor.value.toDouble()))}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(8.dp)
            )

            Text(
                text = "${df.format(calcularGorjetaVar(valor.value.toDouble(), gorjetaVar.value))}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(8.dp)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(text = "Total R$    ")

            Text(
                text = "${df.format(calcularTotal15(valor.value.toDouble()))}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(8.dp)
            )

            Text(
                text = "${df.format(calcularTotal(valor.value.toDouble(), gorjetaVar.value))}",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(Color.Gray)
                    .padding(8.dp)
            )
        }
    }
}



fun calcularGorjeta15(valor: Double): Double {
    return valor * 0.15
}

fun calcularTotal15(valor: Double): Double {
    return valor + calcularGorjeta15(valor)
}

fun calcularGorjetaVar(valor: Double, gorjetaVar: Float): Double {
    return valor * (gorjetaVar / 100)
}

fun calcularTotal(valor: Double, gorjetaVar: Float): Double {
    return valor + calcularGorjetaVar(valor, gorjetaVar)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GorjetaTheme {
        Greeting("Android")
    }
}