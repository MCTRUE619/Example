package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class Person_menu : Fragment(R.layout.activity_person_menu) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.activity_person_menu, container, false)

        val notification_sound = view.findViewById(R.id.notification_sound) as ImageView

        val rel1 = view.findViewById(R.id.rel1) as RelativeLayout
        val rel2 = view.findViewById(R.id.rel2) as RelativeLayout
        val rel3 = view.findViewById(R.id.rel3) as RelativeLayout
        val rel4 = view.findViewById(R.id.rel4) as RelativeLayout
        val rel5 = view.findViewById(R.id.rel5) as RelativeLayout

        notification_sound.setOnClickListener {
            Toast.makeText(requireContext(), "Уведомления выключены!", Toast.LENGTH_SHORT).show()
            val b = it as ImageView
            if (b.tag == "false") {
                b.setImageResource(R.drawable.bell_1)
                Toast.makeText(requireContext(), "Уведомления включены!", Toast.LENGTH_SHORT).show()
                b.tag = "true"
            } else {
                b.setImageResource(R.drawable.bell_1)
                Toast.makeText(requireContext(), "Уведомления выключены!", Toast.LENGTH_SHORT).show()
                b.tag = "false"
            }
        }

        fun outLogin(view: View) {
            activity?.let {
                val intent = Intent(it, Sign_In::class.java)
                it.startActivity(intent)
            }
        }


        fun smena(it:View){
            
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
}