package com.example.musicapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface AppInterface {
    @Headers(
        "x-rapidapi-key: fa1e44d6b6msh4c269a7e4e3d775p135463jsn45dde894a147",
        "x-rapidapi-host: deezerdevs-deezer.p.rapidapi.com" // Fixed spacing here
    )
    @GET("search")
    fun getData(@Query("q") query: String): Call<MyData>
}
