package com.example.skillbox_hw_quiz.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.MainFragmentBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar

class MainFragment : Fragment() {

//    private lateinit var viewModel: MainViewModel

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val calendar = Calendar.getInstance()
    private val dateFormat = SimpleDateFormat("dd.MM.yy")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNextPage.setOnClickListener {
            openSurveyFragment()
        }
        binding.setBornDay.setOnClickListener {
            date()
        }

    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openSurveyFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_surveyFragment2)
    }

    private fun date() {
        val constraint = CalendarConstraints.Builder()
            .setOpenAt(calendar.timeInMillis)
            .build()

        val dateDialog = MaterialDatePicker.Builder.datePicker()
            .setCalendarConstraints(constraint)
            .setTitleText(resources.getString(R.string.born_day))
            .build()
        dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
            calendar.timeInMillis = timeInMillis
            Snackbar.make(
                binding.setBornDay,
                dateFormat.format(calendar.time),
                Snackbar.LENGTH_SHORT
            ).show()

        }
        dateDialog.show((activity as FragmentActivity).supportFragmentManager, "DatePicker")
    }


    companion object {
//        fun newInstance() = MainFragment()
    }

}