package com.example.domain.useCases

import com.example.infra.interfaces.IDiscoveryListRepository

class GetDiscoveryQueueUseCase(private val discoveryListRepository: IDiscoveryListRepository) {
    fun execute(userId : String ) : List<String>{
        return discoveryListRepository.getDiscoveryQueue(userId)
    }
}
