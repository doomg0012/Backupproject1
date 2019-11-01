package com.example.projectmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InsertActivity : AppCompatActivity() {
    var userlist = arrayListOf<User>()
    val createClient = UserAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
    }
    fun addProfile(view:View){
//        val edt_pass1 = edt_pass1.toString()
//        val edt_pass2 = edt_pass2.toString()
//        if(edt_pass1 == ""){
//            Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
//        }
//        if (edt_pass1 == edt_pass2) {
        val api : UserAPI = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserAPI::class.java)
            api.insertUser(
                edt_name.text.toString(),
                edt_username.text.toString(),
                edt_pass1.text.toString()).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: retrofit2.Response<User>) {
                    if(response.isSuccessful()){
                        Toast.makeText(applicationContext,"Register Successfully",Toast.LENGTH_SHORT).show()
                        finish()
                    }else{
                        Toast.makeText(applicationContext,"Error",Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(applicationContext,"Error onFailure"+t.message,Toast.LENGTH_LONG).show()
                }
            })
//        }
    }
    fun reset(view: View){
        edt_username.getText().clear()
        edt_pass1.getText().clear()
//        edt_pass2.getText().clear()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}