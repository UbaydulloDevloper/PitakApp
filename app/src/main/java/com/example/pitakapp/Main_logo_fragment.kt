package com.example.pitakapp

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.pitakapp.databinding.FragmentMainLogoFragmentBinding
import kotlinx.android.synthetic.main.fragment_main_logo_fragment.view.*
import kotlinx.coroutines.flow.combine

class Main_logo_fragment : Fragment() {
    lateinit var binding: FragmentMainLogoFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainLogoFragmentBinding.inflate(layoutInflater)

        val hendler = Handler()
        hendler.postDelayed(Runnable { //Do something after delay
            findNavController().navigate(R.id.language)
        }, 3000)

   /*     binding.imageLogo.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(
                R.id
                    .action_main_logo_fragment_to_language
            )
        }*/
        return binding.root
    }
}