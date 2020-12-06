package com.aldi.dicoding.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aldi.dicoding.R
import com.aldi.dicoding.model.User
import com.aldi.dicoding.ui.DetailActivity
import com.aldi.dicoding.viewholder.UserViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.valdesekamdem.library.mdtoast.MDToast


class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {

    var listUser = ArrayList<User>()
    lateinit var ctx: Context

    fun UserAdapter(ctx: Context,listUser: ArrayList<User>) {
        this.ctx = ctx
        this.listUser = listUser
    }

    fun setData(items: ArrayList<User>) {
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val (avatar, username, location,company) = listUser[position]
        holder.user_name.text = username
        holder.loc.text = location
        holder.company.text = company

        Glide.with(holder.itemView.context)
            .load(avatar!!)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh_black_24dp))
            .error(R.drawable.ic_broken_image_black_24dp)
            .placeholder(R.drawable.user)
            .into(holder.user_image)

        holder.itemView.setOnClickListener {
            val i = Intent(it.context, DetailActivity::class.java)
            i.putExtra(DetailActivity.EXTRA_USER, listUser[position])
            it.context.startActivity(i)
            MDToast.makeText(it.context, username, MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS).show()
        }

    }
    override fun getItemCount(): Int {
        return listUser.size
    }

}
