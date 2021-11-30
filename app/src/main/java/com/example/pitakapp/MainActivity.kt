package com.example.pitakapp

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.example.pitakapp.databinding.ActivityMainBinding
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.pitakapp.databinding.ActivityMainBinding.inflate
import models.SendSms
import models.signIn


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

  /*  override fun onStart() {
        super.onStart()
        if (SendSms(binding.root.context).signIn) {
            findNavController(R.id.fragment_empty).navigate(R.id.s_O_M_A_view)
        }
        if (signIn.sing) {
            findNavController(R.id.fragment_empty).navigate(R.id.s_O_M_A_view)
        }
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            (supportFragmentManager.findFragmentById(R.id.fragment_empty) as NavHostFragment)
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.pitakhome)
        if (SendSms(binding.root.context).signIn) {
            //graph.addArgument("argument", NavArgument)
            graph.startDestination = R.id.s_O_M_A_view
            //or
        } else {
            graph.startDestination = R.id.main_logo_fragment
        }
        navHostFragment.navController.graph = graph
    }


    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.fragment_empty).navigateUp()
    }

}
