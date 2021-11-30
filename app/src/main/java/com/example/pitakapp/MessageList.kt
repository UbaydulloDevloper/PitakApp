package com.example.pitakapp

import Adapters.RvUserMessageAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pitakapp.databinding.FragmentMessagelistBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import models.Users

class MessageList : Fragment() {

    lateinit var binding: FragmentMessagelistBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var auth: FirebaseAuth
    lateinit var reference: DatabaseReference
    lateinit var arrayList: ArrayList<Users>
    lateinit var rvUserMessageAdapter: RvUserMessageAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMessagelistBinding.inflate(layoutInflater)
        firebaseDatabase = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()
        MessagePersonal()

        return binding.root
    }

    fun MessagePersonal() {
        val currentUsers = auth.currentUser
        reference = firebaseDatabase.getReference("MessageList")
        val email = currentUsers?.email
        val name = currentUsers?.displayName
        val image = currentUsers?.photoUrl.toString()
        val idtoken = currentUsers?.uid
        val users = Users(idtoken, name, image, email)

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList = ArrayList()
                val children = snapshot.children
                val filterList = arrayListOf<Users>()
                for (child in children) {
                    val value = child.getValue(Users::class.java)
                    if (value != null && idtoken != value.idToken) {
                        arrayList.add(value)
                    }
                    if (value != null && value.idToken == idtoken) {
                        filterList.add(value)
                    }
                }
                if (filterList.isEmpty()) {
                    reference.child(idtoken!!).setValue(users)
                }

                if (arrayList.isEmpty()) {
                    rvUserMessageAdapter =
                        RvUserMessageAdapter(arrayList, object : RvUserMessageAdapter.Click {
                            override fun itemClick(user: Users) {
                                findNavController().navigate(
                                    R.id.messagePersonal,
                                    bundleOf("user" to user)
                                )
                            }
                        })
                    binding.noMessage.visibility = View.GONE

                    binding.recycleViewUserMessage.adapter = rvUserMessageAdapter
                } else {
                    binding.noMessage.visibility = View.VISIBLE
                }

            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun MessageGroup() {

    }

    companion object {
        @JvmStatic
        fun newInstance() = MessageList()

    }
}