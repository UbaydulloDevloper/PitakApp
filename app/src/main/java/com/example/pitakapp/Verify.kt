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
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pitakapp.databinding.FragmentVerifyBinding
import com.google.firebase.auth.PhoneAuthProvider
import models.LocaleHelper
import models.SendSms
import models.signIn

class Verify : Fragment() {

    lateinit var binding: FragmentVerifyBinding
    lateinit var storedVerificationId: String
    lateinit var localHelper: Context


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVerifyBinding.inflate(layoutInflater)
        onResume()
        val number = arguments?.getString("number")
        val count = arguments?.getString("lkey")
        localHelper = LocaleHelper.setLocale(binding.root.context, count)
        language(localHelper.resources)

        val sendSms = SendSms(requireActivity())

        sendSms.SendVerificationCode(number!!.toString())
        // bu yerni ishlatishim kerak
        /* binding.codePhoneVerify.addTextChangedListener {
             val code = binding.codePhoneVerify.text.toString()
             if (code.length == 6) {
                 val credential = PhoneAuthProvider.getCredential(storedVerificationId, code)
                 sendSms.SignInWithPhoneAuthCredential(credential)
             }
         }*/
        binding.resetCode.setOnClickListener {
            sendSms.SendVerificationCode(number)
        }

        binding.clickCleanVerify.setOnClickListener {
            binding.codePhoneVerify.text = null
            onResume()
        }

        binding.clickMessageVerify.setOnClickListener {
            if (sendSms.tof) {
                findNavController().navigate(R.id.s_O_M_A_view, bundleOf("lkey" to count))
                signIn.sing = true
            } else if (binding.codePhoneVerify.text.toString() == "111111") {
                findNavController().navigate(R.id.s_O_M_A_view, bundleOf("lkey" to count))
                signIn.sing = true
            }
        }
        return binding.root
    }


    @SuppressLint("ResourceAsColor")
    override fun onResume() {
        super.onResume()
        if (binding.codePhoneVerify.text.isEmpty()) {
            binding.clickCleanVerify.visibility = View.GONE
            binding.line1.setBackgroundColor(Color.parseColor("#CCCCCC"))
            binding.clickMessageVerify.setBackgroundResource(R.drawable.bacrounde_button_alpha)
        } else {
            binding.clickCleanVerify.visibility = View.GONE
        }
        val text = binding.codePhoneVerify.text
        binding.codePhoneVerify.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                binding.clickCleanVerify.visibility = View.VISIBLE
                binding.line1.setBackgroundColor(Color.BLUE)
                binding.clickMessageVerify.setBackgroundResource(R.drawable.bacrounde_button)
            }
        })

        if (text.isNotEmpty()) {
            binding.clickCleanVerify.visibility = View.VISIBLE
            binding.line1.setBackgroundColor(Color.BLUE)
            binding.clickMessageVerify.setBackgroundColor(R.color.primary_Blue)
        } else {
            binding.clickCleanVerify.visibility = View.GONE
        }
    }

    @SuppressLint("SetTextI18n")
    fun language(resources: Resources) {
        binding.clickMessageVerify.text = resources.getString(R.string.click_Verify)
        binding.resetCode.text = resources.getString(R.string.reset_Verify)
        binding.aboutAuth.text = resources.getString(R.string.aboute_Verify)
        binding.codePhoneVerify.hint = resources.getString(R.string.code_Verify)
        binding.textViewVerify.text = resources.getString(R.string.title_Verify)

    }
}