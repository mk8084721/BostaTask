package com.example.bostatask.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bostatask.model.CitiesResponse
import com.example.bostatask.repo.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(
    private val repository: CityRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _citiesState = MutableStateFlow<Result<CitiesResponse>?>(null)
    val citiesState: StateFlow<Result<CitiesResponse>?> get() = _citiesState
    val countryId = "60e4482c7cb7d4bc4849c4d5"

    fun fetchCities() {
        viewModelScope.launch {
            repository.getCities(countryId).collect { result ->
                _citiesState.value = result
            }
        }
    }
}
