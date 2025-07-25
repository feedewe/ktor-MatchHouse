package com.example.infra.interfaces

import domain.entities.House

interface IHouseRepository {
    fun getHouses(): List<House>
    fun houseExist(houseId: String): Boolean
    fun getHouseById(houseId: String): House
    fun getUserHouses(userId: String): List<House>
    fun findNearbyHouses(lat: Double, lon: Double, radiusInM: Double): List<House>

}
