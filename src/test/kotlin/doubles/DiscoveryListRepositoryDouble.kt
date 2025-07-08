package com.example.doubles

import com.example.infra.interfaces.IDiscoveryListRepository

class DiscoveryListRepositoryDouble : IDiscoveryListRepository {

    var houseList: MutableList<String> = mutableListOf()
    var userIdStored: String = ""

    override fun addHouses(userId: String, houseIds: List<String>) {
        houseList.addAll(houseIds)
        userIdStored = userId
    }

    override fun removeHouse(userId: String, houseId: String) {
        houseList.remove(houseId)
        userIdStored = userId
    }

    override fun getDiscoveryQueue(userId: String): List<String> {
        userIdStored = userId
        return houseList
    }

    override fun clearDiscoveryQueue(userId: String) {
        userIdStored = userId
        houseList.clear()
    }


}
