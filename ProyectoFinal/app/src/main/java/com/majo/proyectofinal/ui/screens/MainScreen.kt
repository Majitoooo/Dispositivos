package com.majo.proyectofinal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.majo.proyectofinal.ui.components.CategoryDropdown
import com.majo.proyectofinal.ui.components.ImageCard

@Composable
fun MainScreen(viewModel: CatViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF0F5)) // fondo lavanda suave
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "🐱 Galería de Gatos",
                fontSize = 24.sp,
                color = Color(0xFF4A4A4A),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Categoría aleatoria (primera imagen del endpoint general)
            viewModel.randomImage?.let {
                ImageCard(
                    image = it,
                    title = "🎲 Imagen Aleatoria"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Dropdown para elegir categorías
            CategoryDropdown(
                categories = viewModel.categories,
                selectedCategoryId = viewModel.selectedCategory?.id,
                onCategorySelected = { categoryId ->
                    viewModel.selectedCategory = viewModel.categories.find { it.id == categoryId }
                    viewModel.getImages(categoryId)
                }
            )


            Spacer(modifier = Modifier.height(16.dp))

            // Lista de imágenes por categoría
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(viewModel.images) { image ->
                    ImageCard(image = image)
                }
            }
        }
    }
}
