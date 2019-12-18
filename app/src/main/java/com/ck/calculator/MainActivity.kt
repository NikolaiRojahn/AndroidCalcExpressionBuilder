package com.ck.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setClickListeners()
    }

    fun appendOnExpression(string: String, canClear: Boolean){

        if(result.text.isNotEmpty()){
            expression.text = ""
        }

        if(canClear){
            result.text = ""
            expression.append(string)
        } else {
            expression.append(result.text)
            expression.append(string)
            result.text = ""
        }
    }

    private fun setClickListeners() {
        listOf(
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            button0
        ).forEach { button ->
            button.setOnClickListener { appendOnExpression( button.text.toString(), true ) }


        }
        listOf(buttonADD, buttonDIV, buttonSUB, buttonMUL).forEach { button ->
            button.setOnClickListener { appendOnExpression( button.text.toString(), false) }
        }
        buttonC.setOnClickListener {
            result.text = ""
            expression.text = ""
        }

        buttonEQ.setOnClickListener {
            try {
                val myExpression = ExpressionBuilder(expression.text.toString()).build()
                val res = myExpression.evaluate()
                result.text = res.toString()

            } catch(exception: Exception) {
                Log.d("Exception", " " + exception.message)
            }
        }

    }
}


