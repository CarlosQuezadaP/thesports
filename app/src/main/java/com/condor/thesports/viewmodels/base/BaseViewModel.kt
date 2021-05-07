package com.condor.thesports.viewmodels.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//Todo agregar un dispatcher en el constructor
abstract class BaseViewModel : ViewModel() {


    protected val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    fun clearViewModel() {
        onCleared()
    }
}