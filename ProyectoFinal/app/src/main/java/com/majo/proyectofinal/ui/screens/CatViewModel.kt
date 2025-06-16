package com.majo.proyectofinal.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.majo.proyectofinal.model.Breed
import com.majo.proyectofinal.model.CatImage
import com.majo.proyectofinal.model.Category
import com.majo.proyectofinal.network.ApiClient
import kotlinx.coroutines.launch

class CatViewModel : ViewModel() {

    var images by mutableStateOf<List<CatImage>>(emptyList())
    var breeds by mutableStateOf<List<Breed>>(emptyList())
    var categories by mutableStateOf<List<Category>>(emptyList())
    var selectedCategory by mutableStateOf<Category?>(null)
    var randomImage by mutableStateOf<CatImage?>(null)

    init {
        getImages()
        getBreeds()
        getCategories()
        getRandomImage()
    }

    fun getImages(categoryId: Int? = null) {
        viewModelScope.launch {
            try {
                val response = ApiClient.retrofit.getImages(categoryId = categoryId)
                println("✅ Imágenes cargadas: \${response.size}")
                images = response
            } catch (e: Exception) {
                println("❌ ERROR EN getImages: \${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun getBreeds() {
        viewModelScope.launch {
            try {
                val response = ApiClient.retrofit.getBreeds()
                println("✅ Razas cargadas: \${response.size}")
                breeds = response
            } catch (e: Exception) {
                println("❌ ERROR EN getBreeds: \${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                val response = ApiClient.retrofit.getCategories()
                println("✅ Categorías cargadas: \${response.size}")
                categories = response
            } catch (e: Exception) {
                println("❌ ERROR EN getCategories: \${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun getRandomImage() {
        viewModelScope.launch {
            try {
                val response = ApiClient.retrofit.getRandomImage()
                if (response.isNotEmpty()) {
                    randomImage = response.first()
                    println("🎲 Imagen aleatoria cargada: \${randomImage?.url}")
                }
            } catch (e: Exception) {
                println("❌ ERROR EN getRandomImage: \${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun onCategorySelected(categoryId: Int?) {
        selectedCategory = categories.find { it.id == categoryId }
        getImages(categoryId)
    }
}
