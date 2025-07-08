package com.example.domain.useCases

import com.example.doubles.DiscoveryListRepositoryDouble
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class RemoveHouseFromDiscoveryQueueUseCaseTest {

    @Test
    fun `removes house from discovery queue`() {
        val userId = "user1"
        val houseId = "house1"
        val discoveryListRepository = DiscoveryListRepositoryDouble()

        discoveryListRepository.houseList = mutableListOf(houseId, "house2")

        val useCase = RemoveHouseFromDiscoveryQueueUseCase(discoveryListRepository)

        useCase.execute(userId, houseId)

        discoveryListRepository.houseList.shouldContainExactly("house2")
        discoveryListRepository.userIdStored.shouldBe(userId)
    }

    @Test
    fun `does nothing if house is not in discovery queue`() {
        val userId = "user1"
        val houseId = "houseX"
        val discoveryListRepository = DiscoveryListRepositoryDouble()

        discoveryListRepository.houseList = mutableListOf("house1", "house2")

        val useCase = RemoveHouseFromDiscoveryQueueUseCase(discoveryListRepository)

        useCase.execute(userId, houseId)

        discoveryListRepository.houseList.shouldContainExactly("house1", "house2")
        discoveryListRepository.userIdStored.shouldBe(userId)
    }
}
