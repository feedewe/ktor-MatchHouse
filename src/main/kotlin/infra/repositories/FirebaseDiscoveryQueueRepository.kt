package com.example.infra.repositories

import com.example.domain.useCases.ClearDiscoveryQueueUseCase
import com.example.infra.interfaces.IDiscoveryListRepository
import com.google.cloud.Timestamp
import com.google.cloud.firestore.Firestore
import com.google.firebase.cloud.FirestoreClient
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

class FirebaseDiscoveryQueueRepository : IDiscoveryListRepository {

    private val db: Firestore = FirestoreClient.getFirestore()
    private val usersCollectionName = "users"
    private val discoveryCollectionName = "discoveryQueue"
    private val expirationTime = 24L

    override fun addHouses(userId: String, houseIds: List<String>) {
        if (houseIds.isEmpty()) {
            println("No hay casas para añadir a la cola de descubrimiento.")
            return
        }

        val discoveryQueueCollection = db.collection(usersCollectionName)
            .document(userId)
            .collection(discoveryCollectionName)

        val expirationTime = Instant.now().plus(expirationTime, ChronoUnit.HOURS)
        val expireAtTimestamp = Timestamp.of(Date.from(expirationTime))

        val batch = db.batch()

        for (houseId in houseIds) {
            val houseDocument = discoveryQueueCollection.document(houseId)
            val data = mapOf(
                "addedAt" to Timestamp.now(),
                "expireAt" to expireAtTimestamp
            )
            batch.set(houseDocument, data)
        }

        batch.commit().get()

        println("${houseIds.size} casas añadidas a la cola de descubrimiento para el usuario $userId")
    }

    override fun removeHouse(userId: String, houseId: String) {
        val discoveryQueueCollection = db.collection(usersCollectionName)
            .document(userId)
            .collection(discoveryCollectionName)

        val houseDocument = discoveryQueueCollection.document(houseId)

        houseDocument.delete().get()

        println("Casa $houseId eliminada de la cola de descubrimiento del usuario $userId")
    }

    override fun getDiscoveryQueue(userId: String): List<String> {
        val discoveryQueueCollection = db.collection(usersCollectionName).document(userId)
            .collection(discoveryCollectionName)

        val documents = discoveryQueueCollection.get().get().documents
        return documents.map{it.id}
    }

    override fun clearDiscoveryQueue(userId: String){
        val discoveryQueueCollection = db.collection(usersCollectionName).document(userId)
            .collection(discoveryCollectionName)

        val documents = discoveryQueueCollection.get().get().documents
        val batch = db.batch()

        for(doc in documents){
            batch.delete(doc.reference)
        }
        batch.commit().get()
        println("Discovery queue for $userId cleared")
    }

}