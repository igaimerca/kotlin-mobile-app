package com.ampersand.ampersandapp.network

import com.ampersand.ampersandapp.model.DataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("objects")
    fun getObjects(): Call<List<DataModel>>

    @GET("objects/{id}")
    fun getObjectDetails(@Path("id") id: Int): Call<DataModel>
}
