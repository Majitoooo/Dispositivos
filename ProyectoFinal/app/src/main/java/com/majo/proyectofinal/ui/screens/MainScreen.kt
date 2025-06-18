package com.majo.proyectofinal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.majo.proyectofinal.ui.components.CategoryDropdown
import com.majo.proyectofinal.ui.components.ImageCard
import com.majo.proyectofinal.ui.components.BreedDropdown

@Composable
fun MainScreen(viewModel: CatViewModel = viewModel()) {
    var showRandom by remember { mutableStateOf(false) }
    var mostrarSoloGatos by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF0F5))
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {

            if (mostrarSoloGatos) {
                Button(
                    onClick = {
                        mostrarSoloGatos = false
                        viewModel.images = emptyList()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD48FA1))
                ) {
                    Text("← Volver a filtros", color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(viewModel.images) { image ->
                        ImageCard(image = image)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

            } else {

                Text(
                    text = "🐱 Galería de Gatos",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A4A4A),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        showRandom = !showRandom
                        if (showRandom) {
                            viewModel.getRandomImage()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEDB2C0)),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        if (showRandom) "Ocultar imagen aleatoria" else "Mostrar imagen aleatoria",
                        color = Color.White
                    )
                }

                if (showRandom) {
                    Spacer(modifier = Modifier.height(12.dp))
                    viewModel.randomImage?.let {
                        ImageCard(image = it, title = "🎲 Imagen Aleatoria")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Divider(thickness = 1.dp, color = Color(0xFFE0C4CD))
                Spacer(modifier = Modifier.height(16.dp))

                Text("📂 Filtrar por categoría", fontWeight = FontWeight.Medium)
                CategoryDropdown(
                    categories = viewModel.categories,
                    selectedCategoryId = viewModel.selectedCategory?.id,
                    onCategorySelected = { categoryId ->
                        viewModel.selectedCategory = viewModel.categories.find { it.id == categoryId }
                        viewModel.getImages(categoryId)
                        mostrarSoloGatos = true
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text("🐾 Filtrar por raza", fontWeight = FontWeight.Medium)
                BreedDropdown(
                    breeds = viewModel.breeds,
                    onBreedSelected = { breedId ->
                        viewModel.getImagesByBreed(breedId)
                        mostrarSoloGatos = true
                    }
                )

                viewModel.selectedBreed?.let {
                    Spacer(modifier = Modifier.height(12.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBF0))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text("📄 Descripción: ${it.description ?: "No disponible"}", fontSize = 14.sp)
                            Text("🌍 Origen: ${it.origin ?: "No disponible"}", fontSize = 14.sp)
                            Text("😺 Temperamento: ${it.temperament ?: "No disponible"}", fontSize = 14.sp)
                            Text("⚖️ Peso: ${it.weight?.metric ?: "No disponible"} kg", fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

