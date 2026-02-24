package com.example.marsphotos.data

import com.example.marsphotos.ui.data.MarsPhoto
import com.example.marsphotos.ui.network.MarsApiService

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService
) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
    }
}