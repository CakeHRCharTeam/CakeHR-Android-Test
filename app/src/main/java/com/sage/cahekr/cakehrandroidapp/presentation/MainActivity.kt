package com.sage.cahekr.cakehrandroidapp.presentation

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.sage.cahekr.cakehrandroidapp.R
import com.sage.cahekr.cakehrandroidapp.presentation.extension.observe
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    private val adapter: ArrayAdapter<String> by lazy {
        ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1
        )
    }

    private val stateObserver = Observer<MainViewModel.ViewState> { it ->
        adapter.clear()
        adapter.addAll(it.films.map { filmDomainModel -> filmDomainModel.title })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        listOf_films.adapter = adapter

        observe(mainViewModel.stateLiveData, stateObserver)
        mainViewModel.loadData()
    }

}
