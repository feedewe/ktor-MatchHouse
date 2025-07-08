package com.example.domain.useCases

import com.example.doubles.DiscoveryListRepositoryDouble
import io.kotest.matchers.collections.shouldBeEmpty
import kotlin.test.Test

class ClearDiscoveryQueueUseCaseTest {

    @Test
    fun `clears discovery queue for a user`(){
        val userId = "user"
        val discoveryListRepository = DiscoveryListRepositoryDouble()
        discoveryListRepository.houseList = mutableListOf("house1","house2","house3")

        val useCase = ClearDiscoveryQueueUseCase(discoveryListRepository)
        useCase.execute(userId)

        discoveryListRepository.houseList.shouldBeEmpty()
    }

    @Test
    fun `does nothing if discovery queue is empty`(){
        val userId = "user1"
        val discoveryListRepository = DiscoveryListRepositoryDouble()
        val useCase = ClearDiscoveryQueueUseCase(discoveryListRepository)
        useCase.execute(userId)
        discoveryListRepository.houseList.shouldBeEmpty()
    }

}