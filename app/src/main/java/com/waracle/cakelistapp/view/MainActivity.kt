package com.waracle.cakelistapp.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.waracle.cakelistapp.adapter.CakeAdapter
import com.waracle.cakelistapp.databinding.ActivityMainBinding
import com.waracle.cakelistapp.model.Cake
import com.waracle.cakelistapp.viewmodel.CakeViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: CakeViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[CakeViewModel::class.java]
        viewModel.fetchCakes()

        val adapter = CakeAdapter { cake -> showDescriptionDialog(cake) }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.observeCakeLiveData().observe(this, Observer { cakeList ->
            adapter.setCakeList(cakeList)
            binding.swipeRefreshLayout.isRefreshing = false
        })

        viewModel.observeErrorLiveData().observe(this, Observer { errorMessage ->
            binding.swipeRefreshLayout.isRefreshing = false
            Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT).show()
        })

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchCakes()
        }
    }
    private fun showDescriptionDialog(cake: Cake) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(cake.title)
            .setMessage(cake.desc)
            .setPositiveButton("OK", null)
            .create()
        dialog.show()
    }
}