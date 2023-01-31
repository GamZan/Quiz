package com.example.skillbox_hw_quiz

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.databinding.FragmentResultBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ResultFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.result1.text = arguments?.getString("result1")
        binding.result2.text = arguments?.getString("result2")
        binding.result3.text = arguments?.getString("result3")
        retry()

        val anim1 = ObjectAnimator.ofFloat(binding.result1, "alpha", 1f)
        val anim2 = ObjectAnimator.ofFloat(binding.result2, "alpha", 1f)
        val anim3 = ObjectAnimator.ofFloat(binding.result3, "alpha", 1f)
        val anim4 = ObjectAnimator.ofFloat(binding.retryButton, "y", 1640f)
        val anim5 = ObjectAnimator.ofFloat(binding.retryButton, "z", 120f)
        val anim6 = ObjectAnimator.ofFloat(binding.complete, "alpha", 1f)
        AnimatorSet().apply {
            duration = 850
            playTogether(anim4, anim5)
            play(anim1)
            play(anim2).after(anim1)
            play(anim3).after(anim2)
            play(anim6).after(anim3)
            start()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun retry() {
        binding.retryButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_surveyFragment2)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResultFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}