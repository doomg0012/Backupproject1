package com.example.projectmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_showprofile.*

class Showprofile : AppCompatActivity() {
    var userlist = arrayListOf<User>()
    val createClient = UserAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showprofile)

        val profilename = intent.getStringExtra("name")
        val profileuser = intent.getStringExtra("username")
        val profilepassword = intent.getStringExtra("password")

        name.setText(profilename)
        username.setText(profileuser)
        password.setText(profilepassword)
    }
}
