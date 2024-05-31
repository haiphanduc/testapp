package com.photomyne.myapplication.model

import retrofit2.http.GET

interface Api {

    @GET("hello")
    suspend fun getMess(): Item
}