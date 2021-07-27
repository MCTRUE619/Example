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
import kotlinx.android.synthetic.main.activity_person_menu.*
import kotlinx.android.synthetic.main.activity_regestration.*
import kotlinx.android.synthetic.main.order.*
import kotlinx.android.synthetic.main.sign__in.*
import org.json.JSONArray
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private const val ENDPOINT ="http://10.0.2.2:3000"
private const val USER="/Users"
private const val NAME="name"
private const val PHONE="phone"
private const val USERNAME="username"
private const val PASSWORD="password"
var usersid= mutableMapOf<String,String>()
var usersid2= mutableMapOf<String,String>()
class Sign_In : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign__in)
        val password = findViewById(R.id.Password) as EditText
        password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        Thread{
            getrest()
        }.start()

       /* mapuser.put("Vadim","centrsystem619")
        mapuser.put("Nastya","beschauen")*/

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
    /* @WorkerThread
   fun getUserAndShowIt() {
         val httpUrlConnection = URL(ENDPOINT + USER).openConnection() as HttpURLConnection
         httpUrlConnection.apply {
             connectTimeout = 10000 // 10 seconds
             requestMethod = "GET"
             doInput = true
         }
         if (httpUrlConnection.responseCode != HttpURLConnection.HTTP_OK) {
             // show error toast
             return
         }
         val streamReader = InputStreamReader(httpUrlConnection.inputStream)
         var text: String = ""
         streamReader.use {
             text = it.readText()
         }

         val users = mutableMapOf<String,String>()
         val json = JSONArray(text)
         for (i in 0 until json.length()) {
             val jsonuser = json.getJSONObject(i)
             val title = jsonuser.getString(NAME)
             val password=jsonuser.getString(PASSWORD)
             users.put(title,password)
         }

         httpUrlConnection.disconnect()
         val alert = AlertDialog.Builder(this)
         Handler(Looper.getMainLooper()).post {
             val getuser = users.get(title)
             alert.setTitle("Пользователи")
             alert.setMessage(getuser)
             alert.show()
         }
     }
     */

    fun openMain(view: View) {
        val randomIntent = Intent(this, Glavnaya1::class.java)
        var c=0
        for((key, value) in mapuser){

if(Login.text.toString()==key&&Password.text.toString()==value){
    c++
    nameuserz=mapuserdata.getValue(key)[0]
    phoneuserz= mapuserdata.getValue(key)[1]
    //buttonphone.text=mapuserdata.getValue(key)[1]
}
        }
        if(c<1) {
            Toast.makeText(this, "Такого користувача немає", Toast.LENGTH_SHORT).show()
        }
else{
    startActivity(randomIntent)
        }

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
    @WorkerThread
    private fun getrest() {
        val httpURLConnection= URL(ENDPOINT+USER).openConnection() as HttpURLConnection
        httpURLConnection.apply{
            connectTimeout=10000
            requestMethod="GET"
            doInput=true
        }

        if(httpURLConnection.responseCode!=HttpURLConnection.HTTP_OK){
            return
        }
        val streamReader=InputStreamReader(httpURLConnection.inputStream)
        var text=""
        streamReader.use {
            text=it.readText()
        }
        val json=JSONArray(text)
        var id=1
        for(i in 0 until json.length()){
            val jsonrest=json.getJSONObject(i)
            val name = jsonrest.getString(NAME)
            val username= jsonrest.getString(USERNAME)
            val phone = jsonrest.getString(PHONE)
            val password = jsonrest.getString(PASSWORD)
            mapuser.put(name,password)
            usersid.put(username,id.toString())
            usersid2.put(phone,id.toString())
            mapuserdata.put(name, arrayOf(username,phone))
            id++
        }
        httpURLConnection.disconnect()
        Handler(Looper.getMainLooper()).post {
        }
    }
}