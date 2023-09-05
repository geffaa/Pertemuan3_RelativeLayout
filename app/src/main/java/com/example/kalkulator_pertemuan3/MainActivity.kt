package com.example.kalkulator_pertemuan3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var firstInput = ""
    var secondInput = ""
    var selectedOperator = ""
    var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun inputNumber(view: View) {
        val number = (view as Button).text.toString()
        if (selectedOperator.isEmpty()) {
            firstInput += number
            findViewById<TextView>(R.id.numberDisplay).text = firstInput
        }  else {
            secondInput += number
            findViewById<TextView>(R.id.numberDisplay).text = secondInput
        }
    }

    fun inputOperator(view: View) {
        if (selectedOperator.isNotEmpty() && firstInput.isNotEmpty() && secondInput.isNotEmpty()) {
            calculate(view)
        }
        val operator = (view as Button).text.toString()
        selectedOperator = operator
        findViewById<TextView>(R.id.operator).text = operator
    }

    fun calculate(view: View) {
        if (firstInput.isNotEmpty() && secondInput.isNotEmpty() && selectedOperator.isNotEmpty()) {
            val firstNum = firstInput.toDouble()
            val secondNum = secondInput.toDouble()

            result = when (selectedOperator) {
                "+" -> firstNum + secondNum
                "-" -> firstNum - secondNum
                "*" -> firstNum * secondNum
                "÷" -> firstNum / secondNum
                else -> 0.0
            }

            findViewById<TextView>(R.id.numberDisplay).text = result.toString()
            firstInput = result.toString()
            secondInput = ""
            selectedOperator = ""

            if (view.id == R.id.equalButton) {
                findViewById<TextView>(R.id.operator).text = ""
            } else {
                findViewById<TextView>(R.id.operator).text = (view as Button).text
            }
        }
    }

    fun reset(view: View) {
        firstInput = ""
        secondInput = ""
        selectedOperator = ""
        result = 0.0
        findViewById<TextView>(R.id.numberDisplay).text = ""
        findViewById<TextView>(R.id.operator).text = ""
    }
    fun inputDelete(view: View) {
        if (selectedOperator.isEmpty()) {
            // Hapus digit terakhir dari firstInput
            if (firstInput.isNotEmpty()) {
                firstInput = firstInput.substring(0, firstInput.length - 1)
                findViewById<TextView>(R.id.numberDisplay).text = firstInput
            }
        } else {
            if (secondInput.isNotEmpty()) {
                secondInput = secondInput.substring(0, secondInput.length - 1)
                findViewById<TextView>(R.id.numberDisplay).text = secondInput
            }
        }
    }

}

