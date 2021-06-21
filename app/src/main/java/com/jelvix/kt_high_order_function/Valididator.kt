package com.jelvix.kt_high_order_function

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

typealias Predicate = (value: String) -> Boolean
class Validator(private val s: String) {

    private val validationRules = mutableListOf<Predicate>()
    private val errorMessages = mutableListOf<String>()

    var error = ""

    fun isValid(): Boolean {
        for (i in 0 .. validationRules.size-1) {
            if (validationRules[i](s)) {
                emitErrorMessage(errorMessages[i])
                return false
            }
        }

        return true
    }

    private fun emitErrorMessage(messageRes: String) {
        error = messageRes
    }

    fun addRule(errorMsg: String, predicate: Predicate) {
        validationRules.add(predicate)
        errorMessages.add(errorMsg)
    }

    here validators
    /*fun selectAction(key: String): Boolean{
        // определение возвращаемого результата
        return false
    }*/
}