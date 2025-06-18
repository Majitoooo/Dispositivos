package com.majo.proyectofinal.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.majo.proyectofinal.model.Breed

@Composable
fun BreedDropdown(
    breeds: List<Breed>,
    onBreedSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedName by remember { mutableStateOf("Selecciona una raza") }

    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text("Filtrar por raza:", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(4.dp))
        Box {
            Button(onClick = { expanded = true }) {
                Text(selectedName)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                breeds.forEach { breed ->
                    DropdownMenuItem(
                        text = { Text(breed.name) },
                        onClick = {
                            selectedName = breed.name
                            onBreedSelected(breed.id)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}
