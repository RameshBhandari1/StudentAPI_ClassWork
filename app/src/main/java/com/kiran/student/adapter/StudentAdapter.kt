package com.kiran.student.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kiran.student.R
import com.kiran.student.entity.Student
import com.kiran.student.ui.ViewStudentActivity

class StudentAdapter(
    val listStudents : MutableList<Student>,
    val context: Context
    ):RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(){
        class StudentViewHolder(view: View):RecyclerView.ViewHolder(view){
            val tvname : TextView
            val tvage : TextView
            val tvaddress : TextView
            val tvgender : TextView
            val delete : ImageView
            val update : ImageView
            init {
                tvname= view.findViewById(R.id.tvname)
                tvage= view.findViewById(R.id.tvage)
                tvaddress= view.findViewById(R.id.tvaddress)
                tvgender= view.findViewById(R.id.tvgender)
                delete = view.findViewById(R.id.delete)
                update = view.findViewById(R.id.update)
            }

        }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentAdapter.StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.display_layout, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter.StudentViewHolder, position: Int) {
        val stdlst = listStudents[position]
        holder.tvname.text = stdlst.fullname
        holder.tvage.text = stdlst.age.toString()
        holder.tvaddress.text = stdlst.address
        holder.tvgender.text = stdlst.gender

        holder.delete.setOnClickListener{
            listStudents.removeAt(position)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return listStudents.size
    }

}