package com.example.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_showprofile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class activity_login : AppCompatActivity() {
    var userlist = arrayListOf<User>()
    val createClient = UserAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View){
        val api : UserAPI = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:3000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserAPI::class.java)
    api.login(
    profile_user.text.toString(),
    profile_password.text.toString()).enqueue(object : Callback<User>{
        override fun onResponse(call: Call<User>, response: Response<User>) {
            if (response.isSuccessful()){
                val intent = Intent(applicationContext,Showprofile::class.java)
                intent.putExtra("name",response.body()?.profile_name.toString())
                intent.putExtra("username",response.body()?.profile_user.toString())
                intent.putExtra("password",response.body()?.profile_password.toString())
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
            }
        }
        override fun onFailure(call: Call<User>, t: Throwable){
            Toast.makeText(applicationContext,"Incorrect Username or Password !",Toast.LENGTH_LONG).show()
        }
    })
}
    fun cancel(view: View){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}
