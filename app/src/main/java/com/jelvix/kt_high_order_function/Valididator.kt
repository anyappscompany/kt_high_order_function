package com.jelvix.kt_high_order_function

import androidx.lifecycle.MutableLiveData

typealias Predicate = (value: Any?) -> Boolean
class Validator(private val inputString: String) {
    private val validationRules = mutableListOf<Predicate>()
    private val errorMessages = mutableListOf<String>()


    var error = ArrayList<String>()

    //For checking if the liveData value matches the error condition set in the validation rule predicate
    //The livedata value is said to be valid when its value doesn't match an error condition set in the predicate
    fun isValid(): Boolean {
        for (i in 0 until validationRules.size) {
            if (validationRules[i](inputString)) {
                emitErrorMessage(errorMessages[i])
                return false
            }
        }

        //emitErrorMessage(null)
        return true
    }

    //For emitting error messages
    private fun emitErrorMessage(messageRes: String) {
        error.add(messageRes)
    }

    //For adding validation rules
    fun addRule(errorMsg: String, predicate: Predicate) {
        validationRules.add(predicate)
        errorMessages.add(errorMsg)
    }
}