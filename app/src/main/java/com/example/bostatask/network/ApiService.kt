package com.example.bostatask.network

import com.example.bostatask.model.CitiesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("cities/getAllDistricts")
    suspend fun getCities(@Query("countryId") countryId: String): CitiesResponse
}
