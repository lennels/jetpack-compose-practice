package com.example.marsphotos.data

import com.example.marsphotos.model.MarsPhoto
import com.example.marsphotos.ui.network.MarsApi

interface MarsPhotoRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository() : MarsPhotoRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return MarsApi.retrofitService.getPhotos()
    }
}