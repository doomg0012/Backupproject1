package com.example.projectmobile

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.activity_insert.view.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.profile_user
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.activity_login.view.btnlogin
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MainActivity : AppCompatActivity() {
    var userlist = arrayListOf<User>()
    val createClient = UserAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recycler_view.layoutManager= LinearLayoutManager(applicationContext)
//        recycler_view.itemAnimator= DefaultItemAnimator()
//        recycler_view.addItemDecoration(
//            DividerItemDecoration(recycler_view.getContext(),
//                DividerItemDecoration.VERTICAL))
    }
    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
    fun login(view: View){
        val intent = Intent(this,activity_login::class.java)
        startActivity(intent)
    }

    fun register(view: View){
        val intent = Intent(this,InsertActivity::class.java)
        startActivity(intent)
    }
    override fun onResume(){
        super.onResume()
        callUserdata()
    }
    fun callUserdata() {
        userlist.clear();
        val serv = createClient
        serv.retrieveUser()
            .enqueue(object : Callback<List<User>>{
                override fun onResponse(call: Call<List<User>>,response: Response<List<User>>) {
                    response.body()?.forEach {
                        userlist.add(User(it.profile_name, it.profile_user, it.profile_password))
                    }
                }
                    override fun onFailure(call:Call<List<User>>,t:Throwable)=t.printStackTrace()
            })
    }
}


interface OnItemClickListener{
    fun onItemClicked(position:Int,view:View)
}
fun  RecyclerView.addOnItemTouchListener(onClickListener: OnItemClickListener){
    this.addOnChildAttachStateChangeListener(object :RecyclerView.OnChildAttachStateChangeListener{
        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }
        override fun onChildViewAttachedToWindow(view: View) {
            view?.setOnClickListener{
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition,view)
            }
        }
    })
}