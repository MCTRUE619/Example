package com.example.myapplication

import android.app.ActionBar
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.restoraunt.*


class Restaurant_menu : AppCompatActivity() {
    val elemnt: MutableList<FrameLayout> = ArrayList()
    val element2: MutableList<FrameLayout> = ArrayList()
    val element3: MutableList<FrameLayout> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restoraunt)

        val imageSlider = findViewById<ImageSlider>(R.id.viewPager3)

        val slideModels: MutableList<SlideModel> = ArrayList()
        slideModels.add(
            SlideModel("https://-tbn0.gstatic.com/images?q=tbn:ANd9GcTeY-x0-ozntxe-jwblHmlddIc1PqUrdDn6Nw&usqp=CAU")
        )
        slideModels.add(
            SlideModel("https://reston.ua/uploads/img/zavedeniya/original/54212b9.jpg")
        )
        slideModels.add(
            SlideModel("https://www.dniprohotel.ua/images/m/dnipro-m.jpg")
        )
        imageSlider.setImageList(slideModels, true)
        for(i in 0..5){
            add_elements(R.drawable.dish1,"Dish1","Закуски","false")
            add_elements(R.drawable.dish2,"Dish2","Напитки","false")
            add_elements(R.drawable.dish1,"Dish3","Горячее","false")
            add_elements(R.drawable.dish2,"Dish4","Закуски","false")
        }
        show_restourant(flexrestor,elemnt.size-1)
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
        frame_layout1.setOnClickListener {
            startActivity(Intent(this,Dish::class.java))
        }

        val image_view  = ImageView(baseContext)//Картинка
        var params3 = FrameLayout.LayoutParams(int_to_dp(136), int_to_dp(136))
        params3.setMargins(int_to_dp(-5),0,0,0)
        image_view.setImageResource(img)
        image_view.layoutParams = params3


        val frame_layout2 = FrameLayout(baseContext)//Блюдо
        var params1 = FrameLayout.LayoutParams(int_to_dp(343), int_to_dp(104))
        params1.setMargins(int_to_dp(10),int_to_dp(33),0,int_to_dp(10))
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
        text_view2.text = "Тунец с тыквой, под соусом табоджи поданый с кусочком лука (200гр)"
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
    fun plus(view: View){
        val tt = findViewById(R.id.iterator)as TextView;
        tt.text = (tt.text.toString().toInt() + 1).toString()
    }

    fun minus(view: View){
        val tt = findViewById(R.id.iterator)as TextView;
        if(tt.text.toString().toInt()>=1)
        tt.text = (tt.text.toString().toInt() - 1).toString()
    }

    fun openDish(view: View){
        val intent= Intent(this,Dish::class.java)
        startActivity(intent)
    }
    fun Ordered(view: View){
        val intent= Intent(this,Order::class.java)
        startActivity(intent)
    }

    fun openRestaurantMenu(view: View){
        val intent= Intent(this,Glavnaya1::class.java)
        startActivity(intent)
        finish()
    }

    /*fun Podrobnee(view: View){
        val ico = findViewById(R.id.strelka) as ImageView
        val container = findViewById(R.id.PlusOther) as ConstraintLayout
        val kuhni = findViewById(R.id.podrobneeLay) as ConstraintLayout

        if(but.text == "Подробнее") {
            container.layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
            but.text = "Скрыть"
            ico.setImageResource(R.drawable.ic_arrow_up)
            kuhni.layoutParams.height = ConstraintLayout.LayoutParams.WRAP_CONTENT
        }
        else {
            container.layoutParams.height = int_to_dp(40);
            but.text = "Подробнее"
            ico.setImageResource(R.drawable.ic_arrow_down)
        }
    }*/


    fun all_menu(view: View){
        val anim: Animation? = AnimationUtils.loadAnimation(this, R.anim.alpha);
        val animtext: Animation? = AnimationUtils.loadAnimation(this, R.anim.combo);
        val myIter = 0
        val size = 4-1
        val buttons = arrayOf(findViewById(R.id.relativeLay1) as RelativeLayout, findViewById(R.id.relativeLay2) as RelativeLayout, findViewById(R.id.relativeLay3) as RelativeLayout, findViewById(R.id.relativeLay4) as RelativeLayout)
        val images= arrayOf(findViewById(R.id.relativeImg1) as ImageView, findViewById(R.id.relativeImg2) as ImageView, findViewById(R.id.relativeImg3) as ImageView, findViewById(R.id.relativeImg4) as ImageView)
        val icons = arrayOf(R.drawable.ic_menu_red,R.drawable.ic_cold_red,R.drawable.ic_hot_red,R.drawable.ic_drink_red,
            R.drawable.ic_menu_black,R.drawable.ic_cold_black,R.drawable.ic_hot_black,R.drawable.ic_drink_black)
        val texts = arrayOf(findViewById(R.id.relativeBut1) as TextView, findViewById(R.id.relativeBut2) as TextView, findViewById(R.id.relativeBut3) as TextView, findViewById(R.id.relativeBut4) as TextView)
        val sizeInPX1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,64f,resources.displayMetrics).toInt()
        val sizeInPX2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150f,resources.displayMetrics).toInt()

        for(i in 0..size){
            if(i != myIter){
                buttons[i].setBackgroundResource(R.drawable.shadow)
                images[i].setImageResource(icons[i+4])
                texts[i].text = ""
                buttons[i].layoutParams.width=sizeInPX1
            }
        }

        val textViewanim = findViewById<TextView>(R.id.relativeBut1)
        buttons[myIter].setBackgroundResource(R.drawable.button_shadow)
        images[myIter].setImageResource(icons[myIter])
        texts[myIter].text = "Всё меню"
        buttons[myIter].startAnimation(anim)
        textViewanim.startAnimation(animtext)
        buttons[myIter].layoutParams.width=sizeInPX2
    }

    fun cold_platters(view: View){
        val anim: Animation? = AnimationUtils.loadAnimation(this, R.anim.alpha2);
        val animtext: Animation? = AnimationUtils.loadAnimation(this, R.anim.combo);
        val myIter = 1
        val size = 4-1
        val buttons = arrayOf(findViewById(R.id.relativeLay1) as RelativeLayout, findViewById(R.id.relativeLay2) as RelativeLayout, findViewById(R.id.relativeLay3) as RelativeLayout, findViewById(R.id.relativeLay4) as RelativeLayout)
        val images= arrayOf(findViewById(R.id.relativeImg1) as ImageView, findViewById(R.id.relativeImg2) as ImageView, findViewById(R.id.relativeImg3) as ImageView, findViewById(R.id.relativeImg4) as ImageView)
        val icons = arrayOf(R.drawable.ic_menu_red,R.drawable.ic_cold_red,R.drawable.ic_hot_red,R.drawable.ic_drink_red,
            R.drawable.ic_menu_black,R.drawable.ic_cold_black,R.drawable.ic_hot_black,R.drawable.ic_drink_black)
        val texts = arrayOf(findViewById(R.id.relativeBut1) as TextView, findViewById(R.id.relativeBut2) as TextView, findViewById(R.id.relativeBut3) as TextView, findViewById(R.id.relativeBut4) as TextView)
        val sizeInPX1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,64f,resources.displayMetrics).toInt()
        val sizeInPX2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150f,resources.displayMetrics).toInt()

        for(i in 0..size){
            if(i != myIter){
                buttons[i].setBackgroundResource(R.drawable.shadow)
                images[i].setImageResource(icons[i+4])
                texts[i].text = ""
                buttons[i].layoutParams.width=sizeInPX1
            }
        }
        val textViewanim = findViewById<TextView>(R.id.relativeBut2)
        buttons[myIter].setBackgroundResource(R.drawable.button_shadow)
        images[myIter].setImageResource(icons[myIter])
        texts[myIter].text = "Закуски"
        buttons[myIter].startAnimation(anim)
        textViewanim.startAnimation(animtext)
        buttons[myIter].layoutParams.width=sizeInPX2
    }

    fun soups(view: View){
        val anim: Animation? = AnimationUtils.loadAnimation(this, R.anim.alpha2);
        val animtext: Animation? = AnimationUtils.loadAnimation(this, R.anim.combo);
        val myIter = 2
        val size = 4-1
        val buttons = arrayOf(findViewById(R.id.relativeLay1) as RelativeLayout, findViewById(R.id.relativeLay2) as RelativeLayout, findViewById(R.id.relativeLay3) as RelativeLayout, findViewById(R.id.relativeLay4) as RelativeLayout)
        val images= arrayOf(findViewById(R.id.relativeImg1) as ImageView, findViewById(R.id.relativeImg2) as ImageView, findViewById(R.id.relativeImg3) as ImageView, findViewById(R.id.relativeImg4) as ImageView)
        val icons = arrayOf(R.drawable.ic_menu_red,R.drawable.ic_cold_red,R.drawable.ic_hot_red,R.drawable.ic_drink_red,
            R.drawable.ic_menu_black,R.drawable.ic_cold_black,R.drawable.ic_hot_black,R.drawable.ic_drink_black)
        val texts = arrayOf(findViewById(R.id.relativeBut1) as TextView, findViewById(R.id.relativeBut2) as TextView, findViewById(R.id.relativeBut3) as TextView, findViewById(R.id.relativeBut4) as TextView)
        val sizeInPX1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,64f,resources.displayMetrics).toInt()
        val sizeInPX2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150f,resources.displayMetrics).toInt()

        for(i in 0..size){
            if(i != myIter){
                buttons[i].setBackgroundResource(R.drawable.shadow)
                images[i].setImageResource(icons[i+4])
                texts[i].text = ""
                buttons[i].layoutParams.width=sizeInPX1
            }
        }
        val textViewanim = findViewById<TextView>(R.id.relativeBut3)
        buttons[myIter].setBackgroundResource(R.drawable.button_shadow)
        images[myIter].setImageResource(icons[myIter])
        texts[myIter].text = "Горячее"
        buttons[myIter].startAnimation(anim)
        textViewanim.startAnimation(animtext)
        buttons[myIter].layoutParams.width=sizeInPX2
    }

    fun beverages(view: View){
        val anim: Animation? = AnimationUtils.loadAnimation(this, R.anim.combo2);
        val animtext: Animation? = AnimationUtils.loadAnimation(this, R.anim.combo);
        val myIter = 3
        val size = 4-1
        val buttons = arrayOf(findViewById(R.id.relativeLay1) as RelativeLayout, findViewById(R.id.relativeLay2) as RelativeLayout, findViewById(R.id.relativeLay3) as RelativeLayout, findViewById(R.id.relativeLay4) as RelativeLayout)
        val images= arrayOf(findViewById(R.id.relativeImg1) as ImageView, findViewById(R.id.relativeImg2) as ImageView, findViewById(R.id.relativeImg3) as ImageView, findViewById(R.id.relativeImg4) as ImageView)
        val icons = arrayOf(R.drawable.ic_menu_red,R.drawable.ic_cold_red,R.drawable.ic_hot_red,R.drawable.ic_drink_red,
            R.drawable.ic_menu_black,R.drawable.ic_cold_black,R.drawable.ic_hot_black,R.drawable.ic_drink_black)
        val texts = arrayOf(findViewById(R.id.relativeBut1) as TextView, findViewById(R.id.relativeBut2) as TextView, findViewById(R.id.relativeBut3) as TextView, findViewById(R.id.relativeBut4) as TextView)
        val sizeInPX1 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,64f,resources.displayMetrics).toInt()
        val sizeInPX2 = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,150f,resources.displayMetrics).toInt()

        for(i in 0..size){
            if(i != myIter){
                buttons[i].setBackgroundResource(R.drawable.shadow)
                images[i].setImageResource(icons[i+4])
                texts[i].text = ""
                buttons[i].layoutParams.width=sizeInPX1
            }
        }
        val textViewanim = findViewById<TextView>(R.id.relativeBut4)
        buttons[myIter].setBackgroundResource(R.drawable.button_shadow)
        images[myIter].setImageResource(icons[myIter])
        texts[myIter].text = "Напитки"
        buttons[myIter].startAnimation(anim)
        textViewanim.startAnimation(animtext)
        buttons[myIter].layoutParams.width=sizeInPX2
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

}