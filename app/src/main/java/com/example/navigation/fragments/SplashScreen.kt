package com.example.navigation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.Navigation
import com.example.navigation.R

@SuppressLint("CustomSplashScreen")
class SplashScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.logo_move_animation)

        val logoImageView = rootView.findViewById<ImageView>(R.id.splashScreen)
        logoImageView.startAnimation(animation)

        logoImageView.postDelayed({
            view?.let {

                Navigation.findNavController(it).navigate(R.id.navigate_to_login)
            }
        },
            animation.duration)
        return rootView
    }
}

