package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel


class Dish: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dish)
        val imageSlider = findViewById<ImageSlider>(R.id.viewPager2)

        val slideModels: MutableList<SlideModel> = ArrayList()
        slideModels.add(
            SlideModel("фцвф")
        )
        slideModels.add(
            SlideModel("фцв")
        )
        slideModels.add(
            SlideModel("цфв")
        )
        imageSlider.setImageList(slideModels, true)

    }
    fun return_Rest(view: View){
        val intent= Intent(this,Restaurant_menu::class.java)
        startActivity(intent)
        finish()
    }
    fun likeClick(view: View){
        val b = view as ImageButton

        if(b.tag == "false"){
            b.setImageResource(R.drawable.ic_heart_black)
            b.tag = "true"
        }else{
            b.setImageResource(R.drawable.ic_heart_like_red)
            b.tag = "false"
        }
    }

    fun plus(view: View){
        val tt = findViewById(R.id.iterator) as TextView;
        tt.text = (tt.text.toString().toInt() + 1).toString()
    }

    fun minus(view: View){
        val tt = findViewById(R.id.iterator) as TextView;

        if(tt.text.toString().toInt()>=1)
            tt.text = (tt.text.toString().toInt() - 1).toString()
    }
}