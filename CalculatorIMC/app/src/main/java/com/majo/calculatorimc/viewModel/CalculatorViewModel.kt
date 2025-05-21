package com.majo.calculatorimc.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.majo.calculatorimc.model.ImcResult

class CalculatorViewModel : ViewModel() {

    private val _imcResult = MutableStateFlow<ImcResult?>(null)
    val imcResult: StateFlow<ImcResult?> = _imcResult

    fun calculateIMC(weight: String, height: String) {
        val w = weight.toDoubleOrNull()
        val h = height.toDoubleOrNull()

        if (w != null && h != null && h > 0) {
            val imc = w / (h * h)
            val category = when {
                imc < 18.5 -> "Bajo peso"
                imc < 24.9 -> "Normal"
                imc < 29.9 -> "Sobrepeso"
                else -> "Obesidad"
            }
            _imcResult.value = ImcResult(imc, category)
        } else {
            _imcResult.value = null
        }
    }
}
