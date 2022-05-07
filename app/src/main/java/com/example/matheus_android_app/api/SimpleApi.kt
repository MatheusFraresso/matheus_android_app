package com.example.matheus_android_app.api

import com.example.matheus_android_app.model.Activity
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {
    @GET("https://www.boredapi.com/api/activity/")
    suspend fun getActivity(): Activity

    @GET("https://www.boredapi.com/api/activity")
    suspend fun getActivityByType(
        @Query("type") type : String
    ): Activity


}