package com.example.lab2git

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView
    private lateinit var datePicker: DatePicker
    private lateinit var calcButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`activity_main_dashashved.xml`)

        resultText = findViewById(R.id.resultText)
        datePicker = findViewById(R.id.datePicker)
        calcButton = findViewById(R.id.pickYearButton)

        try {
            val day = datePicker.findViewById<View>(
                resources.getIdentifier("day", "id", "android")
            )
            val month = datePicker.findViewById<View>(
                resources.getIdentifier("month", "id", "android")
            )
            day?.visibility = View.GONE
            month?.visibility = View.GONE
        } catch (e: Exception) {
            e.printStackTrace()
        }

        calcButton.setOnClickListener {
            val year = datePicker.year
            val sundays = countSundaysInYear(year)
            val cal = Calendar.getInstance()
            cal.set(year, Calendar.JANUARY, 1)
            val sdf = SimpleDateFormat("yyyy", Locale.getDefault())
            resultText.text = "Выбран год: ${sdf.format(cal.time)}\nВ этом году $sundays воскресений"
        }
    }

    private fun countSundaysInYear(year: Int): Int {
        val calendar = Calendar.getInstance()
        calendar.set(year, Calendar.JANUARY, 1)
        val daysInYear = calendar.getActualMaximum(Calendar.DAY_OF_YEAR)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        var sundays = daysInYear / 7
        if ((firstDayOfWeek - 1 + daysInYear % 7) >= 7) sundays++
        return sundays
    }
}
