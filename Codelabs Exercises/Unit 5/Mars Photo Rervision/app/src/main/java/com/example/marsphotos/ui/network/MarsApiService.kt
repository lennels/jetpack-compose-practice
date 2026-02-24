package com.example.marsphotos.ui.network

import com.example.marsphotos.ui.data.MarsPhoto
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import kotlin.jvm.java

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


// POSTMAN GET {base_url}/photos
interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}

