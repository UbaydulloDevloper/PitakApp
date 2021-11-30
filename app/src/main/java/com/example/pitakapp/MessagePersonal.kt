package com.example.pitakapp

import Adapters.MessageAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.pitakapp.databinding.FragmentMessagePersonalBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import models.Massageclass
import models.Users
import java.text.SimpleDateFormat
import java.util.*

class MessagePersonal : Fragment() {
    lateinit var binding: FragmentMessagePersonalBinding
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var messageAdapter: MessageAdapter

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessagePersonalBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance()
        reference = firebaseDatabase.getReference("massage")
        val user = arguments?.getSerializable("user") as Users
        binding.nameUsers.text = user.name



        binding.text.addTextChangedListener {
            if (binding.text.text!=null){
                binding.sendBtn.setImageResource(R.drawable.send_blue)
            }
        }

        binding.sendBtn.setOnClickListener {
            val messagetext = binding.text.text.toString()
            if (messagetext != "") {
                val simpleDateFormat = SimpleDateFormat("HH:mm")
                val date = simpleDateFormat.format(Date())
                val massageclass = Massageclass(
                    firebaseAuth.currentUser?.uid, user.idToken, date,
                    messagetext
                )
                val key = reference.push().key
                reference.child("${firebaseAuth.currentUser?.uid}/messages/${user.idToken!!}/$key")
                    .setValue(massageclass)

                reference.child("${user.idToken}/messages/${firebaseAuth.currentUser?.uid}/$key")
                    .setValue(massageclass)

                binding.text.text = null
            }
        }

        reference.child("${firebaseAuth.currentUser?.uid}/messages/${user.idToken}")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = arrayListOf<Massageclass>()
                    val children = snapshot.children
                    for (child in children) {
                        val value = child.getValue(Massageclass::class.java)
                        if (value != null) {
                            list.add(value)
                        }
                    }
                    messageAdapter = MessageAdapter(list, firebaseAuth.currentUser?.uid!!)
                    binding.recycleView.adapter = messageAdapter
                    binding.recycleView.scrollToPosition(list.size - 1);

                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        return binding.root
    }

    companion object {
        fun newInstance() = MessagePersonal()

    }
}