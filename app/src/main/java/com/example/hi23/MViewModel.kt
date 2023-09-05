package com.example.hi23

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hi23.adapters.WeatherModel

class MViewModel : ViewModel() {
    val liveDataCurrent = MutableLiveData<WeatherModel>()

    val liveDataList = MutableLiveData<List<WeatherModel>>()

}