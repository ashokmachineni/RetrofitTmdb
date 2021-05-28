package com.applaunch.retrofittmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val popularMovies = mutableListOf<Movie>()
        val  adapter = MoviesAdapter(this,popularMovies)
        val recyclerView = findViewById<RecyclerView>(R.id.recView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val movieService = retrofit.create(ApiService::class.java)
        movieService.getPopularMovies("7f945f914277633f019081c00561f647").enqueue(object :Callback<MovieList>{
            override fun onResponse(call: Call<MovieList>, response: Response<MovieList>) {
                Log.i("main","resp : $response")
                val body = response.body()
                if(body == null){
                    Log.d("oops","something went wrong")
                }else{
                    popularMovies.addAll(body.movies)
                    adapter.notifyDataSetChanged()

                }
            }

            override fun onFailure(call: Call<MovieList>, t: Throwable) {
                Log.i("err","err: $t")
            }

        })

    }
}