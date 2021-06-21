package com.jelvix.kt_high_order_function

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {
    /*val usernameValidator = Validator("anytext").apply {
        //Whenever the condition of the predicate is true, the error message should be emitted
        addRule("Text is short") { it.length<10 }
        addRule("Text is short") { it.length<9 }
        isValid()
    }*/

    private val mainActivityViewModel: MainActivityViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*usernameValidator.error.observe(this, Observer {
            Log.d("debapp", "ERR: ${it}")
        })*/
        https://oozou.com/blog/modern-android-form-validations-with-data-binding-147
        https://metanit.com/kotlin/tutorial/3.7.php
        mainActivityViewModel.validate(Validator("ddd ddd").apply {
            //Whenever the condition of the predicate is true, the error message should be emitted
            addRule("Text is short") { selectAction("") }
            addRule("Text is short") { it.length<2 }
            addRule("Text no dupes") { it.split(" ").size == it.split(" ").toTypedArray().distinct().count() }
            isValid()
        })
        mainActivityViewModel.error.observe(this, Observer {
            Log.d("debapp", "E: ${it}")
        })
    }

    fun selectAction(key: String): Boolean{
        // определение возвращаемого результата
        return false
    }
}