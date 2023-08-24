package com.example.navigation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.navigation.MyWorkObject.MyWorkObject
import com.example.navigation.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class BottomFragment : Fragment() {

    private lateinit var bottomNav : BottomNavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_bottom, container, false)
        bottomNav = rootView.findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.oneTimeWork ->
                    context?.let(MyWorkObject::myOneTimeWork)
                R.id.periodicWork ->
                    context?.let(MyWorkObject::myPeriodicWork)
                R.id.logout ->
                    findNavController().navigate(R.id.action_bottomFragment_to_login)
            }
            true
        }
            return rootView
    }
    }

