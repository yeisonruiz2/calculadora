package com.universidad.primer

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.universidad.calculadora.ui.theme.CalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CalculatorApp()
            }
        }
    }
}

@Composable
fun CalculatorApp() {
    var display by remember { mutableStateOf("0") }
    var firstNumber by remember { mutableStateOf("") }
    var secondNumber by remember { mutableStateOf("") }
    var operation by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = display, modifier = Modifier.padding(16.dp))

        Spacer(modifier = Modifier.height(32.dp))

        Column {
            Row {
                CalculatorButton("1", onClick = { handleInput("1", firstNumber, secondNumber, operation, ::updateDisplay) })
                CalculatorButton("2", onClick = { handleInput("2", firstNumber, secondNumber, operation, ::updateDisplay) })
                CalculatorButton("3", onClick = { handleInput("3", firstNumber, secondNumber, operation, ::updateDisplay) })
            }
            Row {
                CalculatorButton("4", onClick = { handleInput("4", firstNumber, secondNumber, operation, ::updateDisplay) })
                CalculatorButton("5", onClick = { handleInput("5", firstNumber, secondNumber, operation, ::updateDisplay) })
                CalculatorButton("6", onClick = { handleInput("6", firstNumber, secondNumber, operation, ::updateDisplay) })
            }
            Row {
                CalculatorButton("7", onClick = { handleInput("7", firstNumber, secondNumber, operation, ::updateDisplay) })
                CalculatorButton("8", onClick = { handleInput("8", firstNumber, secondNumber, operation, ::updateDisplay) })
                CalculatorButton("9", onClick = { handleInput("9", firstNumber, secondNumber, operation, ::updateDisplay) })
            }
            Row {
                CalculatorButton("0", onClick = { handleInput("0", firstNumber, secondNumber, operation, ::updateDisplay) })
                CalculatorButton("+", onClick = { operation = "+" })
                CalculatorButton("-", onClick = { operation = "-" })
            }
            Row {
                CalculatorButton("=", onClick = { display = calculateResult(firstNumber, secondNumber, operation) })
                CalculatorButton("C", onClick = { resetCalculator(::updateDisplay, ::resetInputs) })
                CalculatorButton("*", onClick = { operation = "*" })
            }
        }
    }
}

@Composable
fun CalculatorButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp)
            .size(80.dp)
    ) {
        Text(text = text)
    }
}

fun handleInput(
    input: String,
    firstNumber: String,
    secondNumber: String,
    operation: String,
    updateDisplay: (String) -> Unit
) {

    var tempFirstNumber = firstNumber
    var tempSecondNumber = secondNumber

    if (operation.isEmpty()) {
        tempFirstNumber += input
        updateDisplay(tempFirstNumber)
    } else {
        tempSecondNumber += input
        updateDisplay(tempSecondNumber)
    }
}

fun calculateResult(firstNumber: String, secondNumber: String, operation: String): String {
    val num1 = firstNumber.toDoubleOrNull() ?: return "Error"
    val num2 = secondNumber.toDoubleOrNull() ?: return "Error"
    return when (operation) {
        "+" -> (num1 + num2).toString()
        "-" -> (num1 - num2).toString()
        "*" -> (num1 * num2).toString()
        else -> "Error"
    }
}

fun updateDisplay(value: String) {
}

fun resetCalculator(updateDisplay: (String) -> Unit, resetInputs: () -> Unit) {
    updateDisplay("0")
    resetInputs()
}

fun resetInputs() {
}

@Preview
@Composable
fun PreviewCalculator() {
    MaterialTheme {
        CalculatorApp()
    }
}