package com.jelvix.kt_high_order_function

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {
    /*val usernameValidator = Validator("anytext").apply {
        //Whenever the condition of the predicate is true, the error message should be emitted
        addRule("Text is short") { it.length<10 }
        addRule("Text is short") { it.length<9 }
        isValid()
    }*/

    private val mainActivityViewModel: MainActivityViewModel by viewModels()



    lateinit var btnStart: Button
    lateinit var etFirstName: EditText
    lateinit var etSecondName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.btnStart)
        etFirstName = findViewById(R.id.etFirstName)
        etSecondName = findViewById(R.id.etSecondName)
        /*usernameValidator.error.observe(this, Observer {
            Log.d("debapp", "ERR: ${it}")
        })*/



        btnStart.setOnClickListener(View.OnClickListener {
            mainActivityViewModel.validateFirstName(Validator(etFirstName.text.toString()).apply {
                //Whenever the condition of the predicate is true, the error message should be emitted
                //addRule("Text is short") { selectAction("") }
                addRule("First name is short") { (it as String).length<3 }
                //addRule("Error values differend") { (it as String).split(" ").size == it.split(" ").toTypedArray().distinct().count() }
                isValid()
            })
            /*mainActivityViewModel.errorsFirstName.observe(this, Observer {
                Log.d("debapp", "Efirst: ${it}")
            })*/

            mainActivityViewModel.validateFullName(Validator(etSecondName.text.toString()).apply {
                //Whenever the condition of the predicate is true, the error message should be emitted
                //addRule("Text is short") { selectAction("") }
                addRule("Second name is short") { (it as String).length<11 }
                //addRule("Error values differend") { (it as String).split(" ").size == it.split(" ").toTypedArray().distinct().count() }
                isValid()
            })

            mainActivityViewModel.getErrors()
        })
        /*mainActivityViewModel.errorsFirstName.observe(this, Observer {
            Log.d("debapp", "Esecond: ${it}")
        })*/
        lifecycleScope.launchWhenStarted {
            mainActivityViewModel.validForm?.collect {
                if(it == true){
                    Log.d("debapp", "Есть ошибки")
                }else if(it==false){
                    Log.d("debapp", "Ошибок нет")
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            mainActivityViewModel.errorsFirstName.collect {
                it.forEach {
                    Log.d("debapp", "Ошибка имени: ${it}")
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            mainActivityViewModel.errorsSecondName.collect {
                it.forEach {
                    Log.d("debapp", "Ошибка Фамилии: ${it}")
                }
            }
        }
    }
}