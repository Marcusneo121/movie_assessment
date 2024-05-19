package com.example.movieassessment.fragment

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.example.movieassessment.R
import com.example.movieassessment.databinding.FragmentLoginBinding
import com.example.movieassessment.databinding.FragmentOnBoardBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private var loginBinding: FragmentLoginBinding? = null
    private val binding get() = loginBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener {
            it.findNavController().popBackStack(R.id.onBoardFragment, false)
        }
        emailFieldListener()
        passwordFieldListener()

        binding.btnLogin.setOnClickListener {
            val validUsername = binding.emailContainer.helperText == null
            val validPassword = binding.passwordContainer.helperText == null
            val usernameLength = binding.editTextTextEmailAddress.length() == 0
            val passwordLength = binding.editTextTextPassword.length() == 0

            Log.v("length pass", usernameLength.toString());
            Log.v("length username", passwordLength.toString());
            if(usernameLength || passwordLength){
                Log.v("came in", "CAME IN");
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("Unable to login")
                    setMessage("Please ensure username & password is provided.")
                    setPositiveButton("OK"){ _,_ ->
                    }
                }.create().show()
            } else {
                if(validUsername && validPassword){
                    //hardcoded Username and Password Check
                    if(binding.editTextTextEmailAddress.text.toString() == "VVVBB" && binding.editTextTextPassword.text.toString() == "@bcd1234"){
                        //navigate to home
                        it.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    } else {
                        AlertDialog.Builder(requireContext()).apply {
                            setTitle("Unable to login")
                            setMessage("Please ensure username & password is correct.")
                            setPositiveButton("OK"){ _,_ ->
                            }
                        }.create().show()
                    }
                } else {
                    invalidForm()
                }
            }
        }
    }

    private fun invalidForm() {
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Unable to login")
            setMessage("Please ensure username & password is fulfilled before login.")
            setPositiveButton("OK"){ _,_ ->
            }
        }.create().show()
    }

    private fun resetForm() {

    }

    private fun emailFieldListener(){
        binding.editTextTextEmailAddress.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.editTextTextEmailAddress.text.toString()
        if(emailText.length < 5){
            return "Minimum 5 Characters Username"
        }

        return null
    }

    private fun passwordFieldListener(){
        binding.editTextTextPassword.setOnFocusChangeListener { _, focused ->
            if(!focused){
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.editTextTextPassword.text.toString()
        if(passwordText.length < 8){
            return "Minimum 8 Characters Password"
        }
        return null
    }
}