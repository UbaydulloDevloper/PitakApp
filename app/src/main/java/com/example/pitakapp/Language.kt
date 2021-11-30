package com.example.pitakapp

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Color.BLACK
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pitakapp.databinding.FragmentLanguageBinding
import kotlinx.android.synthetic.main.fragment_language.*
import models.LocaleHelper
import java.util.*


class Language : Fragment() {
    lateinit var binding: FragmentLanguageBinding
    lateinit var localHelper: Context


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceAsColor", "ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLanguageBinding.inflate(layoutInflater)


        var count = ""

        binding.languageRussian.setOnClickListener {
            localHelper = LocaleHelper.setLocale(binding.root.context, "ru")
            language(localHelper.resources)
            binding.languageEnglishTitle.text = resources.getString(R.string.Английский)

            count ="ru"
            checkmark_russian.visibility = View.VISIBLE
            language_russian_title.setTextColor(Color.BLUE)
            language_russian.setBackgroundColor(Color.parseColor("#F2F2FF"))

            checkmark_uzb.visibility = View.INVISIBLE
            language_uzb_title.setTextColor(BLACK)
            language_Uzb.background = null

            checkmark_english.visibility = View.INVISIBLE
            language_english_title.setTextColor(BLACK)
            language_English.background = null

        }

        binding.languageUzb.setOnClickListener {
            localHelper = LocaleHelper.setLocale(binding.root.context, "uz")
            language(localHelper.resources)
            count ="uz"
            checkmark_russian.visibility = View.INVISIBLE
            language_russian_title.setTextColor(BLACK)
            language_russian.background = null

            checkmark_uzb.visibility = View.VISIBLE
            language_uzb_title.setTextColor(Color.BLUE)
            language_Uzb.setBackgroundColor(Color.parseColor("#F2F2FF"))

            checkmark_english.visibility = View.INVISIBLE
            language_english_title.setTextColor(BLACK)
            language_English.background = null

        }

        binding.languageEnglish.setOnClickListener {
            localHelper = LocaleHelper.setLocale(binding.root.context, "en")
            language(localHelper.resources)
            count ="en"
            checkmark_russian.visibility = View.INVISIBLE
            language_russian_title.setTextColor(BLACK)
            language_russian.background = null

            checkmark_uzb.visibility = View.INVISIBLE
            language_uzb_title.setTextColor(BLACK)
            language_Uzb.background = null

            checkmark_english.visibility = View.VISIBLE
            language_english_title.setTextColor(Color.BLUE)
            language_English.setBackgroundColor(Color.parseColor("#F2F2FF"))


        }

        binding.clickLanguage.setOnClickListener {
            findNavController().navigate(
                R.id.onBoarding_Fragment, bundleOf
                    ("lkey" to count)
            )
        }
        return binding.root
    }

    fun setLocale(activity: Activity, languageCode: String?) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val resources: Resources = activity.resources
        val config: Configuration = resources.getConfiguration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
    }

     private fun language(resources: Resources) {
        binding.titleLanguage.text = resources.getString(R.string.n)
        binding.languageRussianTitle.text = resources.getString(R.string.cc)
        binding.languageRussianAbout.text = resources.getString(R.string.Выбран)
        binding.languageUzbTitle.text = resources.getString(R.string.Узбекский)
        binding.languageUzbAbout.text = resources.getString(R.string.o_zbek_lotin)
        binding.languageEnglishTitle.text = resources.getString(R.string.Английский)
        binding.languageEnglishAbout.text = resources.getString(R.string.english)
        binding.clickLanguage.text = resources.getString(R.string.click_Выбрать)
    }

}