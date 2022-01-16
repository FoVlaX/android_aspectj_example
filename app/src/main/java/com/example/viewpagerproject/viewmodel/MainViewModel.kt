package com.example.viewpagerproject.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

class MainViewModel : ViewModel() {


    private val _onRemoveButtonClickLiveData: MutableLiveData<Int> = MutableLiveData()

    val onRemoveButtonClickLiveData
        get() = _onRemoveButtonClickLiveData as LiveData<Int>

    fun clickOnRemoveButton(
        position: Int
    ) {
        _onRemoveButtonClickLiveData.value = position
    }

    fun networkRequest() {
        viewModelScope.launch(Dispatchers.IO) {
            val okHttpClient = OkHttpClient
                .Builder().build()

            val request = Request.Builder().url("https://oreluniver.ru").build()
            val response =  try {
                okHttpClient.newCall(request).execute()
            } catch (th: Throwable) {

                th.javaClass
            }
            Log.d("MAINVIEWMODEL", response.toString())
        }

    }

}