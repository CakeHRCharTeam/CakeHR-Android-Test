package com.sage.cahekr.cakehrandroidapp.ui

import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.sage.cahekr.cakehrandroidapp.model.FilmResults
import com.sage.cahekr.cakehrandroidapp.R
import com.sage.cahekr.cakehrandroidapp.model.Film
import com.sage.cahekr.cakehrandroidapp.network.FilmService
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    val service: FilmService by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        /*findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/

        val films = service.films
        GetFilms(this).execute(films)
        Log.d("MainActivity", "onCreate")

    }

    class GetFilms(val context: Activity) : AsyncTask<Call<FilmResults>, Void, Response<FilmResults>>() {
        override fun doInBackground(vararg p0: Call<FilmResults>?): Response<FilmResults>? {
            try {
                val res = p0[0]?.execute()
                return res
            } catch (e: Exception) {
                return null
            }
        }

        override fun onPostExecute(result: Response<FilmResults>?) {
            Log.d("FILMS", "films retrieved")
            if (result != null) {
                val adapter = ArrayAdapter<String>(
                    context,
                    android.R.layout.simple_list_item_1,
                    getItems(result.body()!!.results!!)
                )
                val view = context.findViewById<ListView>(R.id.listOf_films)
                view.adapter = adapter
            }
        }

        fun getItems(items: List<Film>): List<String> = items.map { it ->
            it.title
        }

    }

}