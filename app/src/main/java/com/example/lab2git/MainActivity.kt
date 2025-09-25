package com.example.lab2git

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Build
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi

import java.time.DayOfWeek
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class MainActivity : AppCompatActivity() {

    private lateinit var datePicker: DatePicker
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datePicker = findViewById(R.id.datePicker)
        btnCalculate = findViewById(R.id.btnCalculate)
        tvResult = findViewById(R.id.tvResult)

        val currentYear = LocalDate.now().year
        datePicker.updateDate(currentYear, 0, 1)

        btnCalculate.setOnClickListener { calculateMondays() }
    }

    private fun calculateMondays() {
        val year = datePicker.year

        if (year < 1900 || year > 2100) {
            tvResult.text = "Пожалуйста, выберите год от 1900 до 2100."
            return
        }

        var count1 = 0
        var count11 = 0
        var count21 = 0

        for (month in 1..12) {
            if (LocalDate.of(year, month, 1).dayOfWeek == DayOfWeek.MONDAY) count1++
            if (LocalDate.of(year, month, 11).dayOfWeek == DayOfWeek.MONDAY) count11++
            if (LocalDate.of(year, month, 21).dayOfWeek == DayOfWeek.MONDAY) count21++
        }

        val result = """
            В $year году:
            Понедельников 1-го: $count1
            Понедельников 11-го: $count11
            Понедельников 21-го: $count21
        """.trimIndent()

        tvResult.text = result
    }
}