package com.majo.calculatorimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.majo.calculatorimc.CalculatorScreen
import com.majo.calculatorimc.ui.theme.CalculatorIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorIMCTheme {
                CalculatorScreen()
            }
        }
    }
}
