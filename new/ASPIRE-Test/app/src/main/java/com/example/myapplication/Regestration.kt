package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class Regestration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regestration)
    }

    fun BackReg(view: View){
        val intent= Intent(this,Glavnaya::class.java)
        startActivity(intent)
        finish()
    }
    fun openMain(view: View){
        val randomIntent = Intent(this, Glavnaya1::class.java)
        startActivity(randomIntent)
    }
    fun myFun(view:View){
        Toast.makeText(this, "А я причем?))))", Toast.LENGTH_SHORT).show()
    }
    fun myFun1(view:View){
        Toast.makeText(this, "Телеграм не работет!", Toast.LENGTH_SHORT).show()
    }
    fun myFun2(view:View){
        Toast.makeText(this, "Вайбер тоже!)", Toast.LENGTH_SHORT).show()
    }
    fun myFun3(view:View){
        Toast.makeText(this, "Не ростраюйся)))", Toast.LENGTH_SHORT).show()
    }
}