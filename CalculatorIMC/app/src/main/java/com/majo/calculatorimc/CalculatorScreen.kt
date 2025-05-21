package com.majo.calculatorimc

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.majo.calculatorimc.viewModel.CalculatorViewModel

@Composable
fun CalculatorScreen(viewModel: CalculatorViewModel = viewModel()) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }

    val result by viewModel.imcResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Calculadora de IMC", style = MaterialTheme.typography.headlineMedium)
        OutlinedTextField(
            value = weight,
            onValueChange = { weight = it },
            label = { Text("Peso (kg)") }
        )
        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Estatura (m)") }
        )
        Button(onClick = { viewModel.calculateIMC(weight, height) }) {
            Text("Calcular")
        }

        result?.let {
            Text("IMC: %.2f".format(it.imc))
            Text("Categoría: ${it.category}")
        }
    }
}
