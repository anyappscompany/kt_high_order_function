package com.jelvix.kt_high_order_function

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class MainActivityViewModel: ViewModel() {
    var errorsFirstName = MutableStateFlow<List<String>>(listOf())

    fun validateFirstName(valid: Validator){
        viewModelScope.launch {
            errorsFirstName.emit(valid.error)
        }
    }

    var errorsSecondName = MutableStateFlow<List<String>>(listOf())

    fun validateFullName(valid: Validator){
        viewModelScope.launch {
            errorsSecondName.emit(valid.error)
        }
    }

    var validForm = MutableStateFlow<Boolean?>(null)

    fun getErrors() {
        viewModelScope.launch {
            if(errorsFirstName.value.size>0 || errorsSecondName.value.size>0){
                //Log.d("debapp", "1")
                validForm.emit(true)
            }else{
                validForm.emit(false)
                //Log.d("debapp", "0")
            }
        }
    }
}