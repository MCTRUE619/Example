package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Glavnaya : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glavnaya)
    }

    fun onClick2(view: View){
        val intent= Intent(this,Sign_In::class.java)
        startActivity(intent)
    }

    fun secondClick(view: View){
        val intent= Intent(this,Regestration::class.java)
        startActivity(intent)
    }
}