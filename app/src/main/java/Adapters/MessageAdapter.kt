package Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pitakapp.databinding.ItemMessageFromBinding
import com.example.pitakapp.databinding.ItemMessageToBinding
import models.Massageclass

class MessageAdapter(val list: List<Massageclass>, var id: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class FromVh(var itemRv: ItemMessageFromBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: Massageclass) {
            itemRv.dateTv.text = user.date
            itemRv.textMessage.text = user.massage

        }
    }

    inner class ToVh(var itemRv: ItemMessageToBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: Massageclass) {
            itemRv.dateTv.text = user.date
            itemRv.textMessage.text = user.massage

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 1) {
            return FromVh(
                ItemMessageFromBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            return ToVh(
                ItemMessageToBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == 1) {
            val fromVh = holder as FromVh
            fromVh.onBind(list[position])
        } else {
            val toVh = holder as ToVh
            toVh.onBind(list[position])
        }
    }

    override fun getItemCount(): Int = list.size
    override fun getItemViewType(position: Int): Int {
        if (list[position].myId == id) {
            return 1
        } else {
            return 2
        }
    }


}