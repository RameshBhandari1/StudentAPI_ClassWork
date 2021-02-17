package com.kiran.student.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kiran.student.R
import com.kiran.student.api.ServiceBuilder
import com.kiran.student.entity.Student
import com.kiran.student.repository.StudentRepository
import com.kiran.student.ui.UpdateStudentActivity
import com.kiran.student.ui.ViewStudentActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentAdapter(
    val listStudents : MutableList<Student>,
    val context: Context
    ):RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(){
        class StudentViewHolder(view: View):RecyclerView.ViewHolder(view){
            val tvname : TextView
            val tvage : TextView
            val tvaddress : TextView
            val tvgender : TextView
            val image : ImageView
            val delete : ImageView
            val update : ImageView
            init {
                tvname= view.findViewById(R.id.tvname)
                tvage= view.findViewById(R.id.tvage)
                tvaddress= view.findViewById(R.id.tvaddress)
                tvgender= view.findViewById(R.id.tvgender)
                image = view.findViewById(R.id.stdimg)
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
        val imagePath = ServiceBuilder.loadImagePath() + stdlst.photo
        if (!stdlst.photo.equals("no-photo.jpg")) {
            Glide.with(context)
                .load(imagePath)
                .fitCenter()
                .into(holder.image)
        }
        holder.update.setOnClickListener{
            val intent = Intent(context, UpdateStudentActivity::class.java)
            intent.putExtra("student", stdlst)
            context.startActivity(intent)
        }
        holder.delete.setOnClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete student")
            builder.setMessage("Are you sure you want to delete ${stdlst.fullname} ??")
            builder.setIcon(android.R.drawable.ic_delete)

            builder.setPositiveButton("Yes") { _, _ ->
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val studentRepository = StudentRepository()
                        val response = studentRepository.deleteStudent(stdlst._id!!)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    context,
                                    "Student Deleted",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            withContext(Main) {
                                listStudents.remove(stdlst)
                                notifyDataSetChanged()
                            }
                        }
                    } catch (ex: Exception) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(
                                context,
                                ex.toString(),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
            builder.setNegativeButton("No") { _, _ ->
            }
            val alertDialog: AlertDialog = builder.create()
            alertDialog.setCancelable(false)
            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return listStudents.size
    }

}