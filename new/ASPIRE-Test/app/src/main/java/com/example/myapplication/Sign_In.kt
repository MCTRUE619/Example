package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Sign_In : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign__in)
        val password = findViewById(R.id.Password) as EditText
        password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD;
    }
    fun BackSign(view: View){
        val intent= Intent(this,Glavnaya::class.java)
        startActivity(intent)
        finish()
    }
    fun openRegestration(view: View) {
        val Intent = Intent(this, Regestration::class.java)
        startActivity(Intent)
    }

    fun openMain(view: View) {
        val randomIntent = Intent(this, Glavnaya1::class.java)
        val login = findViewById(R.id.Login) as EditText
        val password = findViewById(R.id.Password) as EditText
        if(login.text.toString() == "pikimell")
            if(password.text.toString() == "admin")
                startActivity(randomIntent)
            else{
                Toast.makeText(this, "Неверный пароль!", Toast.LENGTH_SHORT).show()
            }
        else{
            Toast.makeText(this, "Не верный логин!", Toast.LENGTH_SHORT).show()
        }

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

    fun showPassword(view:View){
        val password = findViewById(R.id.Password) as EditText
        if(password.tag == "hide"){
            password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD;
            password.tag = "show"
        }else{
            password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
            password.tag = "hide"
        }

    }
}