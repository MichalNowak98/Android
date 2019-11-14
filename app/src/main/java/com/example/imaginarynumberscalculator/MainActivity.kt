package com.example.imaginarynumberscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener{

    private var spinner: Spinner? = null
    private val stringBuilder = StringBuilder()
    private val calc = Calculator()
    private var aRe: CharSequence? = null
    private var aIm: CharSequence? = null
    private var bRe: CharSequence? = null
    private var bIm: CharSequence? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById<Spinner>(R.id.calculatorSpinner)

        // Spinner click listener
        spinner?.onItemSelectedListener = this
        val adapter = ArrayAdapter.createFromResource(
            this.applicationContext,
            R.array.math_operations, android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = adapter
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(position != 0)
        {
            aRe = findViewById<TextView>(R.id.editTextARe)?.text
            aIm = findViewById<TextView>(R.id.editTextAIm)?.text
            bRe = findViewById<TextView>(R.id.editTextBRe)?.text
            bIm = findViewById<TextView>(R.id.editTextBIm)?.text

            if (aRe.toString().isNotEmpty() && aIm.toString().isNotEmpty() &&
                bRe.toString().isNotEmpty() && bIm.toString().isNotEmpty())
            {
                val item = parent?.getItemAtPosition(position)?.toString()
                try {
                    if (item == "+")
                    {
                        calc.add(aRe.toString().toDouble(), aIm.toString().toDouble(), bRe.toString().toDouble(), bIm.toString().toDouble())
                        showResult()
                    }
                    else if (item == "-")
                    {
                        calc.sub(aRe.toString().toDouble(), aIm.toString().toDouble(), bRe.toString().toDouble(), bIm.toString().toDouble())
                        showResult()
                    }
                    else if (item == "*")
                    {
                        calc.mul(aRe.toString().toDouble(), aIm.toString().toDouble(), bRe.toString().toDouble(), bIm.toString().toDouble())
                        showResult()
                    }
                    else if (item == "/")
                    {
                        if(bRe.toString().toDouble() == 0.0 && bIm.toString().toDouble() == 0.0)
                        {
                            Toast.makeText(
                                applicationContext,
                                """Condition BRe^2 + BIm^2 > 0
                                    |must be fulfilled
                                """.trimMargin(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else
                        {
                            calc.div(aRe.toString().toDouble(), aIm.toString().toDouble(), bRe.toString().toDouble(), bIm.toString().toDouble())
                            showResult()
                        }
                    }
                }
                catch (ex: NumberFormatException)
                {
                    Toast.makeText(
                        applicationContext,
                        """Not all required parameters were correct
                            |Type correct decimal value
                        """.trimMargin(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else
            {
                Toast.makeText(
                    applicationContext,
                    """Not all required parameters were passed
                        |Pass all four parameters
                    """.trimMargin(),
                    Toast.LENGTH_SHORT
                ).show()
            }
            spinner?.setSelection(0)
        }
    }

    private fun showResult() :Unit
    {
        stringBuilder.append(calc.getRe().toString()).append(
            if (calc.getIm() >= 0) {
                "+"
            } else {
                ""
            }
        ).append(calc.getIm().toString()).append("i")
        Toast.makeText(
            applicationContext,
            stringBuilder.toString(),
            Toast.LENGTH_SHORT
        ).show()
        stringBuilder.clear()
    }
}
