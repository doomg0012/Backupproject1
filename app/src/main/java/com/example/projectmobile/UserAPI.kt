package com.example.projectmobile

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UserAPI {

    companion object{
        fun create():UserAPI{
            val userClient : UserAPI = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserAPI::class.java)
            return userClient
        }
    }

    @GET("allprofile")
    fun retrieveUser(): Call<List<User>>

    @FormUrlEncoded
    @POST("profile")
    fun insertUser(
        @Field("profile_name") profile_name:String,
        @Field("profile_user") profile_user:String,
        @Field("profile_password") profile_password:String):Call<User>
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("profile_user") profile_user: String,
        @Field("profile_password") profile_password: String):Call<User>

    @PUT("profile/{profile}")
    fun updateUser(
        @Path("profile_id") profile_id: String,
        @Field("profile_name") profile_name: String,
        @Field("profile_user") profile_user:String,
        @Field("profile_password") profile_password:String):Call<User>

}