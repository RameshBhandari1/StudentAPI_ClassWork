package com.kiran.student.repository

import com.kiran.student.api.MyApiRequest
import com.kiran.student.api.ServiceBuilder
import com.kiran.student.api.StudentAPI
import com.kiran.student.entity.Student
import com.kiran.student.response.AddStudentResponse
import com.kiran.student.response.DeleteStudentResponse
import com.kiran.student.response.GetAllStudentResponse
import com.kiran.student.response.ImageResponse
import com.kiran.student.ui.AddStudentActivity
import okhttp3.MultipartBody

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
    //Delete Student
    suspend fun deleteStudent(id :String): DeleteStudentResponse{
        return apiRequest {
            studentAPI.deleteStudent(
                ServiceBuilder.token!!,id
            )
        }
    }
    //Update Student
    suspend fun updateStudent(id :String,student : Student): DeleteStudentResponse{
        return apiRequest {
            studentAPI.updateStudent(
                ServiceBuilder.token!!, id, student
            )
        }
    }
    //upload image
    suspend fun uploadImage(id: String, body: MultipartBody.Part)
            : ImageResponse {
        return apiRequest {
            studentAPI.uploadImage(
                ServiceBuilder.token!!, id, body
            )
        }
    }

    }