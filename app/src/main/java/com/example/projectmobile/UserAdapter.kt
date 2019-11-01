package com.example.projectmobile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*

class UserAdapter (val items:List<User>,val context: Context) :
    RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return ViewHolder(view_item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvname?.text = "name :"+items[position].profile_name
        holder.tvuser?.text = "user :"+items[position].profile_user
        holder.tvpass?.text = "pass :"+items[position].profile_password
    }
}
class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val tvname = view.tv_name
    val tvuser = view.tv_user
    val tvpass = view.tv_pass
}