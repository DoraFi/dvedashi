package com.example.lab2git

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import java.time.DayOfWeek
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class FragmentDariaYoon : Fragment() {

    private lateinit var datePicker: DatePicker
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView
    private lateinit var btnGoToDasha: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_daria_yoon, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        datePicker = view.findViewById(R.id.datePicker)
        btnCalculate = view.findViewById(R.id.btnCalculate)
        tvResult = view.findViewById(R.id.tvResult)
        btnGoToDasha = view.findViewById(R.id.btnGoToDasha)

        val currentYear = LocalDate.now().year
        datePicker.updateDate(currentYear, 0, 1)

        btnCalculate.setOnClickListener { calculateMondays() }

        btnGoToDasha.setOnClickListener {
            findNavController().navigate(R.id.action_daria_to_dasha)
        }
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