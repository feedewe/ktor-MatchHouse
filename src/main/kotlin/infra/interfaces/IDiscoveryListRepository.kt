package com.example.infra.interfaces

interface IDiscoveryListRepository {
    fun addHouses(userId: String, houseIds: List<String>)
    fun removeHouse(userId: String, houseId: String)
    fun getDiscoveryQueue(userId: String): List<String>
    fun clearDiscoveryQueue(userId: String)

}
