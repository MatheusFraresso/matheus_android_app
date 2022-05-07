package com.example.matheus_android_app.repository

import com.example.matheus_android_app.api.RetrofitInstance
import com.example.matheus_android_app.model.Activity

class Repository {

    suspend fun getActivity(): Activity {
        return RetrofitInstance.api.getActivity();
    }

    suspend fun getActivityByType(type: String): Activity {
        return RetrofitInstance.api.getActivityByType(type);
    }

}