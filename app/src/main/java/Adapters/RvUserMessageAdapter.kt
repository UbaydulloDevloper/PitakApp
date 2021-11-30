package Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pitakapp.R
import com.example.pitakapp.databinding.ItemUsersListBinding
import com.squareup.picasso.Picasso
import models.Users

class RvUserMessageAdapter(val list: List<Users>, val click: Click) :
    RecyclerView.Adapter<RvUserMessageAdapter.Vh>() {


    inner class Vh(var itemRv: ItemUsersListBinding) : RecyclerView.ViewHolder(itemRv.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(user: Users) {
            Picasso.get().load(user.imageUrl).into(itemRv.circleImage)
            itemRv.email.text = user.email
            itemRv.name.text = user.name
            if (user.newMessage != ""){
                itemRv.circleNewMessage.visibility = View.VISIBLE
            }else{
                itemRv.circleNewMessage.visibility = View.INVISIBLE
            }

            if (user.online == true){
                itemRv.circleOnline.setImageResource(R.color.Green_color)
            }else{
                itemRv.circleOnline.setImageResource(R.color.Gray)
            }

            itemRv.root.setOnClickListener {
                    click.itemClick(user)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemUsersListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    interface Click {
        fun itemClick(user: Users)
    }

    override fun getItemCount(): Int = list.size
}
