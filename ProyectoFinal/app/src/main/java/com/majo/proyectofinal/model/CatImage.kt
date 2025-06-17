package com.majo.proyectofinal.model

data class CatImage(
    val id: String,
    val url: String,
    val breeds: List<Breed>?,
    val categories: List<Category>?
)

