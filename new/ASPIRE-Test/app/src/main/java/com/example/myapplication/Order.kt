package com.example.myapplication

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.*
import com.google.android.flexbox.FlexboxLayout

class Order : AppCompatActivity() {
    val elemnt: MutableList<FrameLayout> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order)
        for(i in 0..5){
            add_elements(R.drawable.dish1,"Блюдо"+i,"Кухни","false")
        }

        val flex = findViewById<FlexboxLayout>(R.id.flexorder)
        show_restourant(flex, elemnt.size-1)
    }

    fun int_to_dp(x:Int): Int {
        val value = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            x.toFloat(),
            resources.displayMetrics
        ).toInt()
        return value
    }
    fun create_blydo(img:Int,name:String, tag_name:String): FrameLayout {
        val mp = LinearLayout.LayoutParams.MATCH_PARENT
        val wc = LinearLayout.LayoutParams.WRAP_CONTENT

        val frame_layout = FrameLayout(baseContext)//Главный контейнер
        frame_layout.layoutParams = FrameLayout.LayoutParams(wc,wc)
        frame_layout.setPadding(0,int_to_dp(11),0,0)
        frame_layout.tag = tag_name

        val frame_layout1 = FrameLayout(baseContext)//Контейнер с картинкой
        frame_layout1.layoutParams = FrameLayout.LayoutParams(wc,wc)


        val image_view  = ImageView(baseContext)//Картинка
        var params3 = FrameLayout.LayoutParams(int_to_dp(136), int_to_dp(136))
        params3.setMargins(int_to_dp(-5),0,0,0)
        image_view.setImageResource(img)
        image_view.layoutParams = params3


        val frame_layout2 = FrameLayout(baseContext)//Блюдо
        var params1 = FrameLayout.LayoutParams(int_to_dp(343), int_to_dp(104))
        params1.setMargins(int_to_dp(10),int_to_dp(43),0,int_to_dp(10))
        frame_layout2.layoutParams = params1
        frame_layout2.setBackgroundResource(R.drawable.layout3)

        var text_view3 = TextView(baseContext)//Название
        var params5 = FrameLayout.LayoutParams(wc,wc)
        params5.setMargins(int_to_dp(120),int_to_dp(11),0,0)
        text_view3.layoutParams = params5
        text_view3.text = name
        text_view3.textSize = 18f
        text_view3.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
        text_view3.setTextColor(getResources().getColor(R.color.textDark))
        val text_view2 = TextView(baseContext)//Цена
        val params4 = FrameLayout.LayoutParams(wc,wc)
        params4.setMargins(int_to_dp(121),int_to_dp(50),0,0)
        text_view2.layoutParams = params4
        text_view2.text = "350грн"
        text_view2.textSize = 12f
        text_view2.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
        text_view2.setTextColor(getResources().getColor(R.color.textSilver))
        val frame_layout3 = FrameLayout(baseContext)//Кнопки
        val params2=FrameLayout.LayoutParams(int_to_dp(75),int_to_dp(42))
        params2.setMargins(int_to_dp(259),int_to_dp(30),int_to_dp(13),int_to_dp(20))
        frame_layout3.layoutParams=params2
        frame_layout3.setBackgroundResource(R.drawable.shadow)
        var i=1
        var text_view1 = TextView(baseContext)//Колво
        var params8 = FrameLayout.LayoutParams(wc,int_to_dp(27))
        params8.setMargins(int_to_dp(25),int_to_dp(10),int_to_dp(8),0)
        text_view1.layoutParams = params8
        text_view1.text = ""+i
        text_view1.textSize = 12f
        text_view1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
        text_view1.setTextColor(getResources().getColor(R.color.textDark))
        val imgb1 = ImageButton(baseContext)//Минус
        val params6=FrameLayout.LayoutParams(int_to_dp(11),int_to_dp(10))
        params6.setMargins(int_to_dp(5),int_to_dp(13),int_to_dp(50),int_to_dp(15))

        imgb1.layoutParams=params6
        imgb1.setImageResource(R.drawable.minus)
        imgb1.setOnClickListener {
            if(i>0) {
                i--
                text_view1.text = "" + i
            }
            else text_view1.text=" "

        }
        val imgb2 = ImageButton(baseContext)//Плюс
        val params7=FrameLayout.LayoutParams(int_to_dp(12),int_to_dp(33))
        params7.setMargins(int_to_dp(45),int_to_dp(5),int_to_dp(8),int_to_dp(10))
        imgb2.layoutParams=params7
        imgb2.setImageResource(R.drawable.plus)
        imgb2.setOnClickListener {
            i++
            text_view1.text=""+i
        }
        frame_layout3.addView(imgb1)
        frame_layout3.addView(text_view1)
        frame_layout3.addView(imgb2)
        frame_layout2.addView(frame_layout3)
        frame_layout2.addView(text_view3)
        frame_layout2.addView(text_view2)
        frame_layout1.addView(frame_layout2)
        frame_layout1.addView(image_view)


        frame_layout.addView(frame_layout1)
        return frame_layout
    }
    fun add_elements(img:Int,name:String, tag_name:String, like:String) {
        var f1: FrameLayout
        f1 = create_blydo(img, name, tag_name)
        elemnt.add(f1)
    }
    fun show_restourant(flex: FlexboxLayout, length:Int){
        if(length<=elemnt.size)
            for(i in 0..length){
                flex.addView(elemnt[i])
            }
    }




    fun back(view: View){
        val intent= Intent(this,Restaurant_menu::class.java)
        startActivity(intent)
        finish()
    }
    fun confirm(view: View){
        val intent= Intent(this,Waiting::class.java)
        startActivity(intent)
    }

    fun showMore(view: View){
        val More = view.findViewById(R.id.More) as RelativeLayout
        val flex = view.findViewById(R.id.flexorder) as FlexboxLayout
        var length = 2
        if(More.tag == "true"){
            length = elemnt.size-1
            More.tag = "false"
        }else{
            More.tag = "tag"
        }
        show_restourant(flex, length)
    }
}