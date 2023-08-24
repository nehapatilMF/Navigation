package com.example.navigation.fragments

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.navigation.R
import com.example.navigation.viewmodel.LoginViewModel

class Login : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var etEmailAddress: EditText
    private lateinit var etPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)
        etEmailAddress = rootView.findViewById<EditText>(R.id.etEmailAddress)
        etPassword = rootView.findViewById<EditText>(R.id.etPassword)
        val loginButton = rootView.findViewById<Button>(R.id.button)

        loginButton.setOnClickListener {
            val emailAddress = etEmailAddress.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if (validateInputs(emailAddress, password)) {
                viewModel.login(emailAddress, password)
            }
        }


        viewModel.loginSuccessLiveData.observe(viewLifecycleOwner) { loginSuccessful ->
            if (loginSuccessful) {
                findNavController().navigate(R.id.action_login_to_bottomFragment)
            } else {
                Log.e("Error", "Login Failed")
            }
        }
        viewModel.loginErrorLiveData.observe(viewLifecycleOwner) { error ->
            Log.e("LoginActivity", "Error: $error")
        }

        return rootView
    }

    private fun validateInputs(emailAddress: String, password: String): Boolean {

        if (emailAddress.isEmpty() || password.isEmpty()) {

                etEmailAddress.error = "Email or Password cannot be empty."

             return false

            }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                etEmailAddress.error ="Invalid email address"
                return false
            }
            return true
        }
    }





