package com.example.domain.useCases

import com.example.infra.interfaces.IDiscoveryListRepository

class RemoveHouseFromDiscoveryQueueUseCase(
    private val discoveryListRepository: IDiscoveryListRepository
) {
    fun execute(userId: String, houseId: String) {
        discoveryListRepository.removeHouse(userId, houseId)
    }
}
