package com.example.domain.useCases

import com.example.infra.interfaces.IDiscoveryListRepository

class ClearDiscoveryQueueUseCase(private val discoveryListRepository: IDiscoveryListRepository) {
    fun execute(userId: String){
        discoveryListRepository.clearDiscoveryQueue(userId)
    }
}
