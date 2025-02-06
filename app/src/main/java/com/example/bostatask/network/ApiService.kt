package com.example.bostatask.network

import com.example.bostatask.model.CitiesResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("cities/getAllDistricts?countryId=60e4482c7cb7d4bc4849c4d5")
    suspend fun getCities(): CitiesResponse
}
