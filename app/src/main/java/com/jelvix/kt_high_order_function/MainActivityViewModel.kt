package com.jelvix.kt_high_order_function

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {
    var error = MutableLiveData<String>()

    fun validate(valid: Validator){
        error.value = valid.error
    }
}