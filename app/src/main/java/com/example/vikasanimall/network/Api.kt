package com.example.vikasanimall.network

import com.example.vikasanimall.model.GetemployeeResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("employees.json")
    suspend fun getEmployees(): Response<GetemployeeResponse>

    @GET("employees_empty.json")
    suspend fun getEmployeesEmpty(): Response<GetemployeeResponse>
}