package com.example.movieassessment.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.movieassessment.R
import com.example.movieassessment.databinding.FragmentOnBoardBinding

class OnBoardFragment : Fragment(R.layout.fragment_on_board) {

    private var onboardBinding: FragmentOnBoardBinding? = null
    private val binding get() = onboardBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        onboardBinding = FragmentOnBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogIn.setOnClickListener{
            it.findNavController().navigate(R.id.action_onBoardFragment_to_loginFragment)
        }
    }
}