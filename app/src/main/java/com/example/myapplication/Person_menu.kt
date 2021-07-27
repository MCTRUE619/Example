package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_person_menu.*
import kotlinx.android.synthetic.main.activity_regestration.*
import kotlinx.android.synthetic.main.sign__in.*
import org.json.JSONObject
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL

private const val ENDPOINT ="http://10.0.2.2:3000"
private const val RESTORAUNT="/Users/"
private const val NAME="name"
private const val PHONE="phone"
private const val USERNAME="username"
private const val PASSWORD="password"
var nameuserz:String="f"
var phoneuserz:String="q"
class Person_menu : Fragment(R.layout.activity_person_menu) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.activity_person_menu, container, false)
        val imageView=view.findViewById(R.id.imageView16) as ImageView
        val notification_sound = view.findViewById(R.id.notification_sound) as ImageView



        val edit1=view.findViewById(R.id.editname) as ImageView
        val edit2=view.findViewById(R.id.editphone) as ImageView
        val nuser=view.findViewById(R.id.textView51) as TextView
        val puser=view.findViewById(R.id.textView52) as TextView
        val rel1 = view.findViewById(R.id.rel1) as RelativeLayout
        val rel2 = view.findViewById(R.id.rel2) as RelativeLayout
        val rel3 = view.findViewById(R.id.rel3) as RelativeLayout
        val rel4 = view.findViewById(R.id.rel4) as RelativeLayout
        val rel5 = view.findViewById(R.id.rel5) as RelativeLayout

        nuser.text=nameuserz
        puser.text=phoneuserz

        edit2.setOnClickListener{
            var c=0
            val prov=textView51.text
            val prov2=textView52.text
            val alert=AlertDialog.Builder(this.requireContext())
            alert.setTitle("Смена телефона")
            alert.setMessage("Введите новый телефон")
            val input = EditText(this.requireContext())
            alert.setView(input)
            alert.setPositiveButton("Ok") { dialog, whichButton ->
                val value: String = input.text.toString()
                textView52.text=value
                /*  nameuserz=value
                  mapuser.put(value,phoneuserz)*/
                for((key, value)  in usersid2){
                    if(prov2.toString()==key){
                        Thread{
                            addUser(prov.toString(),input.text.toString(),value.toInt())
                        }.start()
                    }
                }
            }


            alert.setNegativeButton(
                "Cancel"
            ) { dialog, whichButton ->
                // Canceled.
            }
            alert.show()

        }
        edit1.setOnClickListener{
            var c=0
            val prov=textView51.text
            val prov2=textView52.text
            val alert=AlertDialog.Builder(this.requireContext())
            alert.setTitle("Смена имени")
            alert.setMessage("Введите новое имя")
            val input = EditText(this.requireContext())
            alert.setView(input)
            alert.setPositiveButton("Ok") { dialog, whichButton ->
                val value: String = input.text.toString()
                textView51.text=value
              /*  nameuserz=value
                mapuser.put(value,phoneuserz)*/
                for((key, value)  in usersid){
                    if(prov.toString()==key){
                        Thread{
                            addUser(input.text.toString(),prov2.toString(),value.toInt())
                        }.start()
                    }
                }
                }


            alert.setNegativeButton(
                "Cancel"
            ) { dialog, whichButton ->
                // Canceled.
            }
            alert.show()

        }
        notification_sound.setOnClickListener {
       /*     Toast.makeText(requireContext(), "Уведомления выключены!", Toast.LENGTH_SHORT).show()
            val b = it as ImageView
            if (b.tag == "false") {
                b.setImageResource(R.drawable.bell_1)
                Toast.makeText(requireContext(), "Уведомления включены!", Toast.LENGTH_SHORT).show()
                b.tag = "true"
            } else {
                b.setImageResource(R.drawable.bell_1)
                Toast.makeText(requireContext(), "Уведомления выключены!", Toast.LENGTH_SHORT).show()
                b.tag = "false"
            }*/
        }


        imageView.setOnClickListener {
    (activity as Glavnaya1).selectImageInAlbum()
}


        rel1.setOnClickListener {

            val imageView: ImageView = view.findViewById(R.id.imageView18)
            imageView.setImageResource(R.drawable.gps)
            val imageView2: ImageView = view.findViewById(R.id.imageView20)
            imageView2.setImageResource(R.drawable.ic_sale)
            val imageView3: ImageView = view.findViewById(R.id.imageView22)
            imageView3.setImageResource(R.drawable.settings)
            val imageView4: ImageView = view.findViewById(R.id.imageView24)
            imageView4.setImageResource(R.drawable.email_b)
            val imageView5: ImageView = view.findViewById(R.id.imageView26)
            imageView5.setImageResource(R.drawable.faq)

        }

        rel2.setOnClickListener {
            val imageView: ImageView = view.findViewById(R.id.imageView18)
            imageView.setImageResource(R.drawable.gps_b)
            val imageView2: ImageView = view.findViewById(R.id.imageView20)
            imageView2.setImageResource(R.drawable.sales_r)
            val imageView3: ImageView = view.findViewById(R.id.imageView22)
            imageView3.setImageResource(R.drawable.settings)
            val imageView4: ImageView = view.findViewById(R.id.imageView24)
            imageView4.setImageResource(R.drawable.email_b)
            val imageView5: ImageView = view.findViewById(R.id.imageView26)
            imageView5.setImageResource(R.drawable.faq)

        }

        rel3.setOnClickListener {
            val imageView: ImageView = view.findViewById(R.id.imageView18)
            imageView.setImageResource(R.drawable.gps_b)
            val imageView2: ImageView = view.findViewById(R.id.imageView20)
            imageView2.setImageResource(R.drawable.ic_sale)
            val imageView3: ImageView = view.findViewById(R.id.imageView22)
            imageView3.setImageResource(R.drawable.settings_r)
            val imageView4: ImageView = view.findViewById(R.id.imageView24)
            imageView4.setImageResource(R.drawable.email_b)
            val imageView5: ImageView = view.findViewById(R.id.imageView26)
            imageView5.setImageResource(R.drawable.faq)
        }

        rel4.setOnClickListener {
            val imageView: ImageView = view.findViewById(R.id.imageView18)
            imageView.setImageResource(R.drawable.gps_b)
            val imageView2: ImageView = view.findViewById(R.id.imageView20)
            imageView2.setImageResource(R.drawable.ic_sale)
            val imageView3: ImageView = view.findViewById(R.id.imageView22)
            imageView3.setImageResource(R.drawable.settings)
            val imageView4: ImageView = view.findViewById(R.id.imageView24)
            imageView4.setImageResource(R.drawable.email_r)
            val imageView5: ImageView = view.findViewById(R.id.imageView26)
            imageView5.setImageResource(R.drawable.faq)
        }

        rel5.setOnClickListener {
            val imageView: ImageView = view.findViewById(R.id.imageView18)
            imageView.setImageResource(R.drawable.gps_b)
            val imageView2: ImageView = view.findViewById(R.id.imageView20)
            imageView2.setImageResource(R.drawable.ic_sale)
            val imageView3: ImageView = view.findViewById(R.id.imageView22)
            imageView3.setImageResource(R.drawable.settings)
            val imageView4: ImageView = view.findViewById(R.id.imageView24)
            imageView4.setImageResource(R.drawable.email_b)
            val imageView5: ImageView = view.findViewById(R.id.imageView26)
            imageView5.setImageResource(R.drawable.faq_r)

        }

        fun but(view: View) {

        }

        return view

    }
    @WorkerThread
    fun addUser(name: String, phone: String,idus:Int) {
        val httpUrlConnection= URL(ENDPOINT + RESTORAUNT+"$idus").openConnection() as HttpURLConnection
        val body = JSONObject().apply {
            put(USERNAME, name)
            put(PHONE, phone)

        }
        httpUrlConnection.apply {
            connectTimeout = 10000 // 10 seconds
            requestMethod = "PATCH"
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
