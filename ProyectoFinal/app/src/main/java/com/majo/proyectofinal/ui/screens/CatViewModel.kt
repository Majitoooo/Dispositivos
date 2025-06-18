package com.majo.proyectofinal.ui.screens

import androidx.compose.runtime.*
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
    var selectedBreed by mutableStateOf<Breed?>(null)

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
                println("✅ Imágenes cargadas: ${response.size}")
                images = response
            } catch (e: Exception) {
                println("❌ ERROR EN getImages: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun getBreeds() {
        viewModelScope.launch {
            try {
                val response = ApiClient.retrofit.getBreeds()
                println("✅ Razas cargadas: ${response.size}")
                breeds = response
            } catch (e: Exception) {
                println("❌ ERROR EN getBreeds: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                val response = ApiClient.retrofit.getCategories()
                println("✅ Categorías cargadas: ${response.size}")
                categories = response
            } catch (e: Exception) {
                println("❌ ERROR EN getCategories: ${e.message}")
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
                    println("🎲 Imagen aleatoria cargada: ${randomImage?.url}")
                }
            } catch (e: Exception) {
                println("❌ ERROR EN getRandomImage: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun getImagesByBreed(breedId: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.retrofit.getImages(breedId = breedId)
                images = response
                selectedBreed = breeds.find { it.id == breedId } // 👈 aquí se guarda la info
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun searchBreedDetails(name: String, onResult: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiClient.retrofit.getBreeds()
                val matched = response.find { it.name.equals(name, ignoreCase = true) }

                if (matched != null) {
                    val info = buildString {
                        append("🐾 Nombre: ${matched.name}\n")
                        append("📍 Origen: ${matched.origin}\n")
                        append("📏 Tamaño: ${matched.weight?.metric} kg\n")
                        append("❤️ Temperamento: ${matched.temperament}")
                    }
                    onResult(info)
                } else {
                    onResult("No se encontró información para la raza \"$name\".")
                }
            } catch (e: Exception) {
                println("❌ ERROR EN searchBreedDetails: ${e.message}")
                onResult("Ocurrió un error al buscar la raza.")
            }
        }
    }

    fun onCategorySelected(categoryId: Int?) {
        selectedCategory = categories.find { it.id == categoryId }
        getImages(categoryId)
    }
}
