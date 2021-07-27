package com.example.myapplication

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.format.DateFormat
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.order.*
import kotlinx.android.synthetic.main.waiting.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.fixedRateTimer

var numberzakaza=1
class Waiting : AppCompatActivity() {
    val elemnt: MutableList<FrameLayout> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.waiting)
        for(i in 0 until orderdishlist.size) {
            add_elements(orderdishimg[i], orderdishlist[i], "Кухни", zena[i])
        }

        val flex = findViewById<FlexboxLayout>(R.id.flexwait)
        show_restourant(flex, elemnt.size - 1)
        val zak="Заказ№"
        val zakord=zak+numberzakaza.toString()
       textViewnumb.text= zakord
        sumazakazawait.text= itogsumm.toString()
        numberzakaza++
        z1.isVisible=false
        t1.isVisible=false
        t2.isVisible=false
        val sdf = SimpleDateFormat("HH", Locale.GERMANY).format(Date())
        val sdf2 = SimpleDateFormat("mm", Locale.GERMANY).format(Date())
        val s=sdf2.toInt()
        val s1=sdf.toInt()
        val q=":"
        val time=s1
        val strokavremeni=time.toString()+q+s.toString()
        val rand = (1..2).random()
        val strokavremeni2=time.toString()+q+(s+rand).toString()
        timecook.text= strokavremeni
        fixedRateTimer("timer", false, 0L, 60 * 1000) {
            this@Waiting.runOnUiThread {
                val times = SimpleDateFormat("mm", Locale.GERMANY).format(Date())
                val t=times.toInt()
                if((s+rand)==t){
                    z1.isVisible=true
                    t2.isVisible=true
                    gotov.text = "Заказ собран"
                    edet.text = "Заказ в пути"
                    timerun.text=strokavremeni2
                    timecook.setTextColor(getResources().getColor(R.color.textSilver))
                    gotov.setTextColor(getResources().getColor(R.color.textSilver))
                    edet.setTextColor(getResources().getColor(R.color.slider))
                    timerun.setTextColor(getResources().getColor(R.color.colorApplication))
                    car1.setImageResource(R.drawable.truck_1)
                    car2.setImageResource(R.drawable.ic_third_stage_red)
                    cont1.setBackgroundResource(R.drawable.layout5)
                    cont2.setBackgroundResource(R.drawable.layout6)
                }
            }
        }
    }


    fun int_to_dp(x: Int): Int {
        val value = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            x.toFloat(),
            resources.displayMetrics
        ).toInt()
        return value
    }
    fun create_blydo(img: Int, name: String, tag_name: String, price: String): FrameLayout {
        val mp = LinearLayout.LayoutParams.MATCH_PARENT
        val wc = LinearLayout.LayoutParams.WRAP_CONTENT

        val frame_layout = FrameLayout(baseContext)//Главный контейнер
        frame_layout.layoutParams = FrameLayout.LayoutParams(wc, wc)
        frame_layout.setPadding(0, int_to_dp(11), 0, 0)
        frame_layout.tag = tag_name

        val frame_layout1 = FrameLayout(baseContext)//Контейнер с картинкой
        frame_layout1.layoutParams = FrameLayout.LayoutParams(wc, wc)


        val image_view  = ImageView(baseContext)//Картинка
        var params3 = FrameLayout.LayoutParams(int_to_dp(136), int_to_dp(136))
        params3.setMargins(int_to_dp(-5), 0, 0, 0)
        image_view.setImageResource(img)
        image_view.layoutParams = params3


        val frame_layout2 = FrameLayout(baseContext)//Блюдо
        var params1 = FrameLayout.LayoutParams(int_to_dp(343), int_to_dp(104))
        params1.setMargins(int_to_dp(10), int_to_dp(43), 0, int_to_dp(10))
        frame_layout2.layoutParams = params1
        frame_layout2.setBackgroundResource(R.drawable.layout3)

        var text_view3 = TextView(baseContext)//Название
        var params5 = FrameLayout.LayoutParams(wc, wc)
        params5.setMargins(int_to_dp(120), int_to_dp(11), 0, 0)
        text_view3.layoutParams = params5
        text_view3.text = name
        text_view3.textSize = 18f
        text_view3.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
        text_view3.setTextColor(getResources().getColor(R.color.textDark))
        val text_view2 = TextView(baseContext)//Цена
        val params4 = FrameLayout.LayoutParams(wc, wc)
        params4.setMargins(int_to_dp(121), int_to_dp(50), 0, 0)
        text_view2.layoutParams = params4
        text_view2.text = price+"грн"
        text_view2.textSize = 12f
        text_view2.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
        text_view2.setTextColor(getResources().getColor(R.color.textSilver))
        frame_layout2.addView(text_view3)
        frame_layout2.addView(text_view2)
        frame_layout1.addView(frame_layout2)
        frame_layout1.addView(image_view)


        frame_layout.addView(frame_layout1)
        return frame_layout
    }
    fun add_elements(img: Int, name: String, tag_name: String, price: String) {
        var f1: FrameLayout
        f1 = create_blydo(img, name, tag_name, price)
        elemnt.add(f1)
    }
    fun show_restourant(flex: FlexboxLayout, length: Int){
        if(length<=elemnt.size)
            for(i in 0..length){
                flex.addView(elemnt[i])
            }
    }





    fun back(view: View){
        val intent= Intent(this, Order::class.java)
        startActivity(intent)
        finish()
    }
}