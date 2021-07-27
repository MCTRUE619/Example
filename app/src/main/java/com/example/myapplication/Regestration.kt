package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_regestration.*
import kotlinx.android.synthetic.main.order.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL


private const val ENDPOINT ="http://10.0.2.2:3000"
private const val RESTORAUNT="/Users"
private const val NAME="name"
private const val PHONE="phone"
private const val USERNAME="username"
private const val PASSWORD="password"
var mapuser = mutableMapOf<String, String>()
var mapuserdata = mutableMapOf<String, Array<String>>()

class Regestration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regestration)

      /*  mapuser.put("centrsystem","centrsystem619")
        mapuser.put("beschauen","beschauen")*/

    }

    fun BackReg(view: View){
        val intent= Intent(this, Glavnaya::class.java)
        startActivity(intent)
        finish()
    }
    fun openMain(view: View){

        val randomIntent = Intent(this, Glavnaya1::class.java)
        if(name.text.isNotEmpty()&&phone.text.length==10&&Passwordvvod.text.length>8&&Passwordvvod.text.toString()==Passwordvvod2.text.toString()){
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Введите значение")
        val rand = (0..35).random()
        alert.setMessage(rand.toString() + "+" + (rand - 2))
        val key=rand+(rand-2)
        val input = EditText(this)
        alert.setView(input)
        alert.setPositiveButton("Ok") { dialog, whichButton ->
            val value: String = input.text.toString()
            Thread{
                addUser(name.text.toString(), Passwordvvod.text.toString(), phone.text.toString())
            }.start()
           mapuser.put(name.text.toString(),Passwordvvod.text.toString())
            mapuserdata.put(name.text.toString(), arrayOf(name.text.toString(),phone.text.toString()))
            nameuserz=name.text.toString()
            phoneuserz=phone.text.toString()
            buttonphone.text=phone.text.toString()
            when(value.toInt()){
                key -> startActivity(randomIntent)

            }
        }

        alert.setNegativeButton(
            "Cancel"
        ) { dialog, whichButton ->
            // Canceled.
        }

        alert.show()
        }
       else{
            val alert=AlertDialog.Builder(this)
            alert.setTitle("Регистрация")
            if(name.text.isEmpty()) {
                alert.setMessage("Введите логин")
                alert.show()
            }else if(phone.text.length<10){
                alert.setMessage("Введите правильный телефон")
                alert.show()
            }
            else{
                alert.setMessage("Введите пароль от 8 символов")
                alert.show()
            }
        }
    }
    fun showPassword(view: View){
        val password = findViewById(R.id.Passwordvvod) as EditText
        if(password.tag == "hide"){
            password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD;
            password.tag = "show"
        }else{
            password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
            password.tag = "hide"
        }
    }
    fun showPassword2(view: View){
        val password = findViewById(R.id.Passwordvvod2) as EditText
        if(password.tag == "hide"){
            password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD;
            password.tag = "show"
        }else{
            password.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
            password.tag = "hide"
        }
    }
    @WorkerThread
    fun addUser(name: String, password: String, phone: String) {
        val httpUrlConnection= URL(ENDPOINT + RESTORAUNT).openConnection() as HttpURLConnection
        val body = JSONObject().apply {
            put(NAME, name)
            put(PHONE, phone)
            put(USERNAME, name)
            put(PASSWORD, password)

        }
        httpUrlConnection.apply {
            connectTimeout = 10000 // 10 seconds
            requestMethod = "POST"
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
        }
        OutputStreamWriter(httpUrlConnection.outputStream).use {
            it.write(body.toString())
        }
        httpUrlConnection.responseCode
        httpUrlConnection.disconnect()
    }

}

