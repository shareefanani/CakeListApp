package com.waracle.cakelistapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.waracle.cakelistapp.model.Cake
import com.waracle.cakelistapp.retrofit.CakeRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CakeViewModel : ViewModel() {
    val cakesLiveData: MutableLiveData<List<Cake>> = MutableLiveData()
    private val repository = CakeRepository()

    fun fetchCakes() {
        repository.getCakes().enqueue(object : Callback<List<Cake>> {
            override fun onResponse(call: Call<List<Cake>>, response: Response<List<Cake>>) {
                if (response.isSuccessful) {
                    response.body()?.let { cakes ->
                        /* Remove duplicates from response **/
                        val distinctCakes = cakes.distinctBy { it.title }
                        /* Sort response by name **/
                        val sortedCakes = distinctCakes.sortedBy { it.title }
                        /* Convert to List to ensure compatibility **/
                        cakesLiveData.value = sortedCakes.toList()
                    }
                }
            }

            override fun onFailure(call: Call<List<Cake>>, t: Throwable) {
                // Handle failure
            }
        })
    }
    fun observeCakeLiveData() : LiveData<List<Cake>> {
        return cakesLiveData
    }
}
