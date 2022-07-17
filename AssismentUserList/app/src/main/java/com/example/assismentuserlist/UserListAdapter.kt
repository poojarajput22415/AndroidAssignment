package com.example.assismentuserlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assismentuserlist.handler.OnItemClickListener
import com.example.assismentuserlist.modle.UserDetails
import com.example.assismentuserlist.utils.DataOperation

class UserListAdapter(private val userDetailsList: List<UserDetails>,private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val userName:TextView = itemView.findViewById(R.id.tvUserName)
        private val email:TextView = itemView.findViewById(R.id.tvEmail)
        private val country:TextView = itemView.findViewById(R.id.tvCountry)
        private val registerDate:TextView = itemView.findViewById(R.id.tvRegistrationDate)

        fun bind(userDetails: UserDetails,onItemClickListener: OnItemClickListener){

            userName.text = DataOperation.getFullName(userDetails.name)
            email.text = userDetails.email
            country.text = userDetails.location.country
            registerDate.text = userDetails.registered.date
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(userDetails)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.row_gridview,parent,false)

        return ViewHolder(layoutInflater)


    }

    override fun getItemCount(): Int {

        return userDetailsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bind(userDetailsList[position],onItemClickListener)

    }

}