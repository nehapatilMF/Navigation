package com.example.navigation.fragments

import com.example.navigation.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.navigation.MyWorkObject.MyWorkObject
import com.example.navigation.viewmodel.LogoutViewModel

class Home() : Fragment() {
    private lateinit var viewModel: LogoutViewModel<Any?>
    private lateinit var logoutViewModel: LogoutViewModel<Any?>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LogoutViewModel::class.java]
        logoutViewModel = ViewModelProvider(this)[LogoutViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val logoutButton = rootView.findViewById<Button>(R.id.logout_button)
        val oneTime = rootView.findViewById<Button>(R.id.btnOneTime)
        val periodic = rootView.findViewById<Button>(R.id.btnPeriodic)
        logoutButton.setOnClickListener {
            viewModel.logout()
        }
        oneTime.setOnClickListener {
            context?.let { it1 -> MyWorkObject.myOneTimeWork(it1) }
        }
        periodic.setOnClickListener {
            context?.let { it1 -> MyWorkObject.myPeriodicWork(it1) }
        }
        viewModel.logoutSuccessLiveData.observe(viewLifecycleOwner) { logoutSuccessful ->
            if (logoutSuccessful) {
                findNavController().navigate(R.id.navigate_to_login)
            } else {
                Log.e("Error", "Login Failed")
            }
        }
        return rootView
    }
}


