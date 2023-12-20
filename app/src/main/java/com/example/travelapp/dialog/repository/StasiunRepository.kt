package com.example.travelapp.dialog.repository

import com.example.travelapp.api.stasiun.Stasiun
import com.example.travelapp.api.stasiun.StationApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StasiunRepository {

    private val stationApi: StationApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://booking.kai.id/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        stationApi = retrofit.create(StationApi::class.java)
    }

    fun getStations(callback: Callback<List<Stasiun>>) {
        stationApi.getStations().enqueue(callback)
    }
}
