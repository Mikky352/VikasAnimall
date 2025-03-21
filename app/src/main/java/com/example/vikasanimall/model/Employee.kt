package com.example.vikasanimall.model

import com.google.gson.annotations.SerializedName

class Employee {
    @SerializedName("uuid")
    var id: String? = null
    @SerializedName("full_name")
    var name: String? = null
    @SerializedName("phone_number")
    var phoneNumber: String? = null
    @SerializedName( "email_address")
    var email: String? = null
    @SerializedName( "biography")
    var biography: String? = null
    @SerializedName(  "photo_url_small")
    var urlSmall: String? = null
    @SerializedName(  "photo_url_large")
    var urlLarge: String? = null
    @SerializedName( "team")
    var team: String? = null
    @SerializedName( "employee_type")
    var employeeType: String? = null
}