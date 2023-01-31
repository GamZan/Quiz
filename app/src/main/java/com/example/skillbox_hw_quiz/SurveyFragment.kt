package com.example.skillbox_hw_quiz

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.databinding.FragmentSurveyBinding
import com.example.skillbox_hw_quiz.quiz.Quiz
import com.example.skillbox_hw_quiz.quiz.QuizStorage
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SurveyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SurveyFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentSurveyBinding? = null
    private val binding get() = _binding!!

    private var language = Locale.getDefault().language.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSurveyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindRadioButton(changeLanguage())
        setupClickListener()
        binding.radioGroup.animate().apply {
            duration = 1100
            alpha(1f)
        }
        binding.secondRadioGroup.animate().apply {
            duration = 1500
            alpha(1f)
        }
        binding.thirdRadioGroup.animate().apply {
            duration = 1900
            alpha(1f)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun bindRadioButton(q: Quiz) {

        binding.question.text = q.questions[0].question

        binding.optionOne.text = q.questions[0].answers[0]
        binding.optionTwo.text = q.questions[0].answers[1]
        binding.optionThree.text = q.questions[0].answers[2]
        binding.optionFour.text = q.questions[0].answers[3]

        binding.secondQuestion.text = q.questions[1].question

        binding.optionFive.text = q.questions[1].answers[0]
        binding.optionSix.text = q.questions[1].answers[1]
        binding.optionSeven.text = q.questions[1].answers[2]
        binding.optionEight.text = q.questions[1].answers[3]

        binding.thirdQuestion.text = q.questions[2].question

        binding.optionNine.text = q.questions[2].answers[0]
        binding.optionTen.text = q.questions[2].answers[1]
        binding.optionEleven.text = q.questions[2].answers[2]
        binding.optionTwelve.text = q.questions[2].answers[3]
    }

    private fun setupClickListener() {
        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_surveyFragment2_to_mainFragment)
        }
        binding.buttonSubmit.setOnClickListener {
            feedBack(changeLanguage())
        }
    }

    private fun feedBack(q: Quiz) {
        val bundle = Bundle().apply {
            when {
                binding.optionOne.isChecked -> putString("result1", q.questions[0].feedback[0])
                binding.optionTwo.isChecked -> putString("result1", q.questions[0].feedback[1])
                binding.optionThree.isChecked -> putString("result1", q.questions[0].feedback[2])
                binding.optionFour.isChecked -> putString("result1", q.questions[0].feedback[3])
            }
            when {
                binding.optionFive.isChecked -> putString("result2", q.questions[1].feedback[0])
                binding.optionSix.isChecked -> putString("result2", q.questions[1].feedback[1])
                binding.optionSeven.isChecked -> putString("result2", q.questions[1].feedback[2])
                binding.optionEight.isChecked -> putString("result2", q.questions[1].feedback[3])
            }
            when {
                binding.optionNine.isChecked -> putString("result3", q.questions[2].feedback[0])
                binding.optionTen.isChecked -> putString("result3", q.questions[2].feedback[1])
                binding.optionEleven.isChecked -> putString("result3", q.questions[2].feedback[2])
                binding.optionTwelve.isChecked -> putString("result3", q.questions[2].feedback[3])
            }
        }
        findNavController().navigate(R.id.action_surveyFragment2_to_resultFragment, bundle)
    }

    private fun changeLanguage(): Quiz {
        var q = QuizStorage.getQuiz(QuizStorage.Locale.En)
        if (language == "ru") {
            q = QuizStorage.getQuiz(QuizStorage.Locale.Ru)
        }
        return q
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SurveyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SurveyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}