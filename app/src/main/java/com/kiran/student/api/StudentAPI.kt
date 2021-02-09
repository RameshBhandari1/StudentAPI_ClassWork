package com.kiran.student.api

import com.kiran.student.entity.Student
import com.kiran.student.response.AddStudentResponse
import com.kiran.student.response.GetAllStudentResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface StudentAPI {
    //Add student
    @POST("student/")
    suspend fun addStudent(
        @Header("Authorization") token : String,
        @Body student : Student
    ): Response<AddStudentResponse>

    @GET("student/")
    suspend fun getAllStudents(
        @Header("Authorization") token : String,
    ):Response<GetAllStudentResponse>
}