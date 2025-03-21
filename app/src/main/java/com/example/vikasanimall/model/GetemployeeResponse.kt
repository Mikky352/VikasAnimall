package com.example.vikasanimall.model

import com.google.gson.annotations.SerializedName

class GetemployeeResponse  {

    @SerializedName("employees")
    var employees: MutableList<Employee>? = null
}