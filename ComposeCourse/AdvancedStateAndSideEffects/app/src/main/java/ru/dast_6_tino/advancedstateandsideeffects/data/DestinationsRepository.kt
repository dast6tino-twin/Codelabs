package ru.dast_6_tino.advancedstateandsideeffects.data

import javax.inject.Inject

class DestinationsRepository @Inject constructor(
    private val destinationsLocalDataSource: DestinationsLocalDataSource,
) {

    val destinations: List<ExploreModel> = destinationsLocalDataSource.craneDestinations
    val hotels: List<ExploreModel> = destinationsLocalDataSource.craneHotels
    val restaurants: List<ExploreModel> = destinationsLocalDataSource.craneRestaurants

    fun getDestination(cityName: String): ExploreModel? = destinationsLocalDataSource.craneDestinations
        .firstOrNull { model -> model.city.name == cityName }

}
