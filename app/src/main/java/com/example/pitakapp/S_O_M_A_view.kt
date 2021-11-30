package com.example.pitakapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pitakapp.databinding.FragmentSOMAViewBinding

class S_O_M_A_view : Fragment() {
    lateinit var binding: FragmentSOMAViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSOMAViewBinding.inflate(layoutInflater)
        childFragmentManager.beginTransaction().replace(
            R.id.fragment_child, Search
                .newInstance()
        ).commit()

        binding.search.setOnClickListener {
            DefaultIcon()
            binding.search.setImageResource(R.drawable.search_blue)
            childFragmentManager.beginTransaction().replace(
                R.id.fragment_child, Search
                    .newInstance()
            ).commit()


        }
        binding.orders.setOnClickListener {
            DefaultIcon()
            binding.orders.setImageResource(R.drawable.orders_blue)
            childFragmentManager.beginTransaction().replace(
                R.id.fragment_child, Orders
                    .newInstance()
            ).commit()

        }
        binding.message.setOnClickListener {
            DefaultIcon()
            binding.message.setImageResource(R.drawable.message_blue)
            childFragmentManager.beginTransaction().replace(
                R.id.fragment_child, MessageList
                    .newInstance()
            ).commit()
        }
        binding.account.setOnClickListener {
            DefaultIcon()
            binding.account.setImageResource(R.drawable.account_blue)
            childFragmentManager.beginTransaction().replace(
                R.id.fragment_child, Account
                    .newInstance()
            ).commit()
        }


        return binding.root
    }


    fun DefaultIcon() {
        binding.search.setImageResource(R.drawable.search_gray)
        binding.orders.setImageResource(R.drawable.orders_gray)
        binding.message.setImageResource(R.drawable.message_gray)
        binding.account.setImageResource(R.drawable.account_gray)

    }
}