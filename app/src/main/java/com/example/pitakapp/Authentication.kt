package com.example.pitakapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pitakapp.databinding.FragmentAuthenticationBinding
import models.LocaleHelper

class Authentication : Fragment() {
    lateinit var binding: FragmentAuthenticationBinding
    lateinit var localHelper: Context

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthenticationBinding.inflate(layoutInflater)
        val count = arguments?.getString("lkey")
        localHelper = LocaleHelper.setLocale(binding.root.context, count)
        language(localHelper.resources)
        onResume()

        binding.clickMessage.setOnClickListener {
            val number = "+998${binding.numberPhone.text}"
            findNavController().navigate(R.id.verify, bundleOf("number" to number, "lkey" to count))
        }
        binding.clickClean.setOnClickListener {
            binding.numberPhone.text = null
            onResume()
        }

        return binding.root
    }

    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        if (binding.numberPhone.text.isEmpty()) {
            binding.clickClean.visibility = View.GONE
            binding.line1.setBackgroundColor(Color.parseColor("#CCCCCC"))
            binding.clickMessage.setBackgroundResource(R.drawable.bacrounde_button_alpha)
        } else {
            binding.clickClean.visibility = View.GONE
        }
        val text = binding.numberPhone.text
        binding.numberPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                binding.clickClean.visibility = View.VISIBLE
                binding.line1.setBackgroundColor(Color.BLUE)
                binding.clickMessage.setBackgroundResource(R.drawable.bacrounde_button)
            }
        })

        if (text.isNotEmpty()) {
            binding.clickClean.visibility = View.VISIBLE
            binding.line1.setBackgroundColor(Color.BLUE)
            binding.clickMessage.setBackgroundColor(R.color.primary_Blue)
        } else {
            binding.clickClean.visibility = View.GONE
        }
    }
    fun language(resources: Resources) {
        binding.clickMessage.text = resources.getString(R.string.clck_Authentication)
        binding.aboutAuth.text = resources.getString(R.string.about_Authentication)
        binding.numberPhone.hint = resources.getString(R.string.number_Authentication)
        binding.textView.text = resources.getString(R.string.title_Authentication)
    }


}

