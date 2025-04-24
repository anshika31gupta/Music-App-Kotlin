package com.example.musicapp

import MyAdapter
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {

    private lateinit var myRecyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter
    private var mediaPlayer: MediaPlayer? = null  // ✅ Safe MediaPlayer instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recyclerView)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(AppInterface::class.java)
        val retrofitData = api.getData("eminem")

        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                val body = response.body()
                val dataList = body?.data

                if (response.isSuccessful && dataList != null && dataList.isNotEmpty()) {
                    myAdapter = MyAdapter(
                        this@MainActivity,
                        dataList,
                        onPlay = { url -> playAudio(url) },
                        onPause = { stopAudio() }
                    )
                    myRecyclerView.adapter = myAdapter
                    myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                    Log.d("TAG", "Data loaded successfully.")
                } else {
                    Log.e("TAG", "No data found or response not successful.")
                }
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.e("TAG", "Network request failed: ${t.message}")
            }
        })
    }

    // ✅ Safe play method
    private fun playAudio(previewUrl: String) {
        try {
            stopAudio() // Stop previous before starting new
            mediaPlayer = MediaPlayer.create(this, previewUrl.toUri())
            mediaPlayer?.start()
        } catch (e: Exception) {
            Log.e("MediaPlayer", "Error while playing audio: ${e.message}")
        }
    }

    // ✅ Safe stop and release
    private fun stopAudio() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
            }
            it.release()
            mediaPlayer = null
        }
    }

    // ✅ Release on destroy
    override fun onDestroy() {
        super.onDestroy()
        stopAudio()
    }
}
