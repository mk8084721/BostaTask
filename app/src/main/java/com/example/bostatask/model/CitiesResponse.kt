package com.example.bostatask.model

data class CitiesResponse(
    val success: Boolean,
    val message: String,
    val data: List<City>
)

data class City(
    val cityId: String,
    val cityName: String,
    val cityOtherName: String,
    val cityCode: String,
    val districts: List<District>,
    var isExpanded: Boolean = false
)

data class District(
    val districtId: String,
    val districtName: String,
    val districtOtherName: String,
    val pickupAvailability: Boolean,
    val dropOffAvailability: Boolean,
    val coverage: String
)

