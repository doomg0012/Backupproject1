package com.example.projectmobile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User (
    @Expose
    @SerializedName("profile_name")val profile_name:String,
    @Expose
    @SerializedName("profile_user")val profile_user:String,
    @Expose
    @SerializedName("profile_password")val profile_password:String){}