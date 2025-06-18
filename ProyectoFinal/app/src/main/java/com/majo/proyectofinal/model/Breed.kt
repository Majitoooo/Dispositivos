package com.majo.proyectofinal.model

data class Breed(
    val id: String,
    val name: String,
    val origin: String?,
    val temperament: String?,
    val description: String?,
    val weight: Weight?
)

data class Weight(
    val imperial: String?,
    val metric: String?
)
