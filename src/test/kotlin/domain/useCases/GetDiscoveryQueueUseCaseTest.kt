package com.example.domain.useCases

import com.example.doubles.DiscoveryListRepositoryDouble
import io.kotest.matchers.collections.shouldContainExactly
import kotlin.test.Test

class GetDiscoveryQueueUseCaseTest {

    @Test
    fun `returns discovery queue for a user`(){

        val userId = "user1"
        val discoveryListRepository = DiscoveryListRepositoryDouble()
        discoveryListRepository.houseList= mutableListOf("house1","house2")

        val useCase = GetDiscoveryQueueUseCase(discoveryListRepository)

        val result = useCase.execute(userId)
        result.shouldContainExactly("house1","house2")
    }

    @Test
    fun `returns empty list if no houses in discovery queue`(){

        val userId = "user1"
        val discoveryListRepository = DiscoveryListRepositoryDouble()

        val useCase = GetDiscoveryQueueUseCase(discoveryListRepository)

        val result = useCase.execute(userId)

        result.shouldContainExactly()
    }

}