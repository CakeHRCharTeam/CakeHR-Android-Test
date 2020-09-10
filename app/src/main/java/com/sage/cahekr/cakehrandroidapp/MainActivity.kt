package com.sage.cahekr.cakehrandroidapp

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        /*findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        val retrofit = Retrofit.Builder()
        val okHttpClient = OkHttpClient().newBuilder()
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS)
        val client = okHttpClient.build()
        retrofit.baseUrl("https://swapi.dev/api/").addConverterFactory(GsonConverterFactory.create())
        retrofit.client(client)
        val retrofitbuilder = retrofit.build()
        val service: SWService = retrofitbuilder.create(SWService::class.java)

        val films = service.films
        GetFilms(this).execute(films)


    }

    class GetFilms(val context: Activity) : AsyncTask<Call<Film>, Void, Response<Film>>() {
        override fun doInBackground(vararg p0: Call<Film>?): Response<Film>? {
            try {
                val res = p0[0]?.execute()
                return res
            } catch (e: Exception) {
                return null
            }
        }

        override fun onPostExecute(result: Response<Film>?) {
            Log.d("FILMS", "films retrieved")
            if (result != null) {
                val adapter = ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, getItems(result.body()!!.results!!))
                val view = context.findViewById<ListView>(R.id.listOf_films)
                view.adapter = adapter
            }
        }

        fun getItems(items: List<ResultsItem>): List<String> = items.map { it ->
            it.title
        }

    }

}