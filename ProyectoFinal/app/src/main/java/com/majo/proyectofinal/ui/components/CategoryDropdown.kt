package com.majo.proyectofinal.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.majo.proyectofinal.model.Category

@Composable
fun CategoryDropdown(
    categories: List<Category>,
    selectedCategoryId: Int?,
    onCategorySelected: (Int?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedName = categories.find { it.id == selectedCategoryId }?.name ?: "Todas"

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(text = "Filtrar por categoría:", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Box {
            Button(onClick = { expanded = true }) {
                Text(text = selectedName)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(text = { Text("Todas") }, onClick = {
                    onCategorySelected(null)
                    expanded = false
                })
                categories.forEach { category ->
                    DropdownMenuItem(text = { Text(category.name) }, onClick = {
                        onCategorySelected(category.id)
                        expanded = false
                    })
                }
            }
        }
    }
}
