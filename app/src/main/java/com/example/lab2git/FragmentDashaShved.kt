package com.example.lab2git

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.text.SimpleDateFormat
import java.util.*

class FragmentDashaShved : Fragment() {

    private lateinit var resultText: TextView
    private lateinit var datePicker: DatePicker
    private lateinit var calcButton: Button
    private lateinit var btnGoToDaria: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_dasha_shved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultText = view.findViewById(R.id.resultText)
        datePicker = view.findViewById(R.id.datePicker)
        calcButton = view.findViewById(R.id.pickYearButton)
        btnGoToDaria = view.findViewById(R.id.btnGoToDaria)

        // Скрыть день и месяц (оставить только год)
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
            resultText.text = "Выбран год: $year\nВ этом году $sundays воскресений"
        }

        btnGoToDaria.setOnClickListener {
            findNavController().navigate(R.id.action_dasha_to_daria)
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