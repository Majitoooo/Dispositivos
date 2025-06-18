package com.majo.proyectofinal.network

import com.majo.proyectofinal.model.Breed
import com.majo.proyectofinal.model.CatImage
import com.majo.proyectofinal.model.Category
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService {

    @GET("images/search")
    suspend fun getImages(
        @Query("category_ids") categoryId: Int? = null,
        @Query("breed_id") breedId: String? = null,
        @Query("limit") limit: Int = 10
    ): List<CatImage>

    @GET("breeds")
    suspend fun getBreeds(): List<Breed>

    @GET("categories")
    suspend fun getCategories(): List<Category>

    @GET("images/search")
    suspend fun getRandomImage(): List<CatImage>

    @GET("breeds/search")
    suspend fun searchBreedByName(@Query("q") breedName: String): List<Breed>

}

