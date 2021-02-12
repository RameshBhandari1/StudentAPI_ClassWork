package com.kiran.student.api

import androidx.room.Update
import com.kiran.student.entity.Student
import com.kiran.student.response.AddStudentResponse
import com.kiran.student.response.DeleteStudentResponse
import com.kiran.student.response.GetAllStudentResponse
import retrofit2.Response
import retrofit2.http.*

interface StudentAPI {
    //Add student
    @POST("student/")
    suspend fun addStudent(
        @Header("Authorization") token : String,
        @Body student : Student
    ): Response<AddStudentResponse>

    //view student
    @GET("student/")
    suspend fun getAllStudents(
        @Header("Authorization") token : String,
    ):Response<GetAllStudentResponse>

    //delete student
    @DELETE("student/{id}")
    suspend fun deleteStudent(
        @Header("Authorization") token : String,
        @Path("id") id : String
    ):Response<DeleteStudentResponse>

    //update student detail
    @PUT("student/{id}")
    suspend fun updateStudent(
        @Header("Authorization") token : String,
        @Path("id") id : String,
        @Body student : Student
    ):Response<DeleteStudentResponse>
}