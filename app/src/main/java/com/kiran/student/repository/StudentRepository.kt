package com.kiran.student.repository

import com.kiran.student.api.MyApiRequest
import com.kiran.student.api.ServiceBuilder
import com.kiran.student.api.StudentAPI
import com.kiran.student.entity.Student
import com.kiran.student.response.AddStudentResponse
import com.kiran.student.response.GetAllStudentResponse
import com.kiran.student.ui.AddStudentActivity

class StudentRepository:
    MyApiRequest() {
    private  val studentAPI =
        ServiceBuilder.buildService(StudentAPI::class.java)
    //Add student
    suspend fun addStudent(student : Student): AddStudentResponse{
        return apiRequest {
            studentAPI.addStudent(
                ServiceBuilder.token!!,student
            )
        }
    }
    //View Students
    suspend fun getAllStudents(): GetAllStudentResponse{
        return apiRequest {
            studentAPI.getAllStudents(
                ServiceBuilder.token!!
            )
        }
    }
    }