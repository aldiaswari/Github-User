package com.aldi.dicoding.viewholder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aldi.dicoding.Interface.ItemClickListener
import com.aldi.dicoding.R

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    var user_name: TextView
    var user_image: ImageView
    var company: TextView
    var loc: TextView

    private var itemClickListener: ItemClickListener? = null

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    init {
        user_name = itemView.findViewById(R.id.tv_username)
        user_image = itemView.findViewById(R.id.img_user)
        company = itemView.findViewById(R.id.tv_company)
        loc = itemView.findViewById(R.id.tv_location)
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        itemClickListener!!.onClick(v, adapterPosition, false)
    }
}