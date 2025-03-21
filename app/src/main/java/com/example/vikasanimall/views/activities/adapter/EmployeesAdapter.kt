package com.example.vikasanimall.views.activities.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.vikasanimall.R
import com.example.vikasanimall.model.Employee

class EmployeesAdapter(val employeeList: MutableList<Employee>) : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>(){


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var employeeImage: ImageView = itemView.findViewById(R.id.product_image)
        var employeetName: AppCompatTextView = itemView.findViewById(R.id.appCompatTextView3)
        var employeetTeam: AppCompatTextView = itemView.findViewById(R.id.appCompatTextView5)
        var employeetEmail: AppCompatTextView = itemView.findViewById(R.id.appCompatTextView7)
        var employeetJob: AppCompatTextView = itemView.findViewById(R.id.appCompatTextView9)

        fun setData(imageUrl: String) {

            Glide.with(employeeImage.context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.drawable.placeholderimage)
                .into(employeeImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_employeeitem, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return employeeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.employeetName.setText(employeeList[position].name)
        holder.employeetJob.setText(employeeList.get(position).employeeType)
        holder.employeetTeam.setText(employeeList.get(position).team)
        holder.employeetEmail.setText(employeeList.get(position).email)

        employeeList.get(position).urlLarge?.let { holder.setData(it) }
    }
}