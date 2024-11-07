package com.example.metricconverter

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var inputValue: EditText
    private lateinit var unitFromSpinner: Spinner
    private lateinit var unitToSpinner: Spinner
    private lateinit var outputResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        inputValue = findViewById(R.id.inputValue)
        unitFromSpinner = findViewById(R.id.unitFromSpinner)
        unitToSpinner = findViewById(R.id.unitToSpinner)
        outputResult = findViewById(R.id.outputResult)
        val convertButton: Button = findViewById(R.id.convertButton)

        // Define units
        val units = arrayOf("Millimeter", "Centimeter", "Meter", "Kilometer")

        // Set up Spinners
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitFromSpinner.adapter = adapter
        unitToSpinner.adapter = adapter

        // Set onClickListener for convert button
        convertButton.setOnClickListener {
            convertUnits()
        }
    }

    private fun convertUnits() {
        val input = inputValue.text.toString()
        if (input.isEmpty()) {
            outputResult.text = "Please enter a value."
            return
        }

        val value = input.toDouble()
        val unitFrom = unitFromSpinner.selectedItem.toString()
        val unitTo = unitToSpinner.selectedItem.toString()

        val meterValue = toMeters(value, unitFrom)
        val result = fromMeters(meterValue, unitTo)

        outputResult.text = String.format("%.3f %s", result, unitTo)
    }

    private fun toMeters(value: Double, unit: String): Double {
        return when (unit) {
            "Millimeter" -> value / 1000
            "Centimeter" -> value / 100
            "Meter" -> value
            "Kilometer" -> value * 1000
            else -> 0.0
        }
    }

    private fun fromMeters(value: Double, unit: String): Double {
        return when (unit) {
            "Millimeter" -> value * 1000
            "Centimeter" -> value * 100
            "Meter" -> value
            "Kilometer" -> value / 1000
            else -> 0.0
        }
    }
}
