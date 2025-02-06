package com.example.bostatask.repo

import com.example.bostatask.model.CitiesResponse
import com.example.bostatask.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val apiService: ApiService
) {
    fun getCities(countryId : String): Flow<Result<CitiesResponse>> = flow {
        try {
            val response = apiService.getCities(countryId)
            emit(Result.success(response))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}
