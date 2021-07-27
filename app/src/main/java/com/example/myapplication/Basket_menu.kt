package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.flexbox.FlexboxLayout

class Basket_menu : Fragment(R.layout.activity_basket_menu) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.activity_basket_menu, container, false)

        val buttons = arrayOf(
            view.findViewById(R.id.Restaurant_bask) as Button,
            view.findViewById(R.id.Bluda_bask) as Button
        )
        val elemnt: MutableList<FrameLayout> = ArrayList()
        val animations: Array<Animation?> = arrayOf(
            AnimationUtils.loadAnimation(requireContext(), R.anim.alpha2)
        )


        fun int_to_dp(x:Int): Int {
            val value = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                x.toFloat(),
                resources.displayMetrics
            ).toInt()
            return value
        }

        fun create_frame(number:String,name:String, tag_name:String): FrameLayout {
            val mp = LinearLayout.LayoutParams.MATCH_PARENT
            val wc = LinearLayout.LayoutParams.WRAP_CONTENT
            var params1 = FrameLayout.LayoutParams(mp, int_to_dp(71))



            val f = FrameLayout(this.requireContext())//Главный контейнер
            f.layoutParams = params1
            f.tag = tag_name


            val tv = TextView(this.requireContext())//Номер заказа
            val params2 = LinearLayout.LayoutParams(0,0,1000f)
            tv.text = number
            params2.width = wc
            params2.height = int_to_dp(13)
            params2.setMargins(int_to_dp(26),int_to_dp(12),0,0)
            tv.layoutParams = params2
            tv.textSize = 12f
            tv.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
            tv.setTextColor(getResources().getColor(R.color.textDark))


            val tv2 = TextView(this.requireContext())//Название
            val params3 = LinearLayout.LayoutParams(0,0,1000f)
            tv2.text = name
            params3.width = wc
            params3.height = int_to_dp(23)
            params3.setMargins(int_to_dp(26),int_to_dp(25),0,0)
            tv2.layoutParams = params3
            tv2.textSize = 20f
            tv2.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
            tv2.setTextColor(getResources().getColor(R.color.textDark))


            val img= ImageView(this.requireContext())//Тарелка
            val params4 = LinearLayout.LayoutParams(int_to_dp(30),int_to_dp(30))
            params4.setMargins(int_to_dp(172),int_to_dp(15),0,0)
            img.setImageResource(R.drawable.ic_restaurants_black)
            img.layoutParams= params4
            val arrow= ImageView(this.requireContext())//Стрелка
            val params5 = LinearLayout.LayoutParams(int_to_dp(34),int_to_dp(20))
            params5.setMargins(int_to_dp(320),int_to_dp(22),0,0)
            arrow.setImageResource(R.drawable.ic_arrow_forward_black)
            arrow.layoutParams= params5



            f.addView(tv)
            f.addView(tv2)
            f.addView(img)
            f.addView(arrow)
            return f
        }

        fun add_elements(number:String,name:String, tag_name:String) {
            val f1 = create_frame(number, name, tag_name)
            elemnt.add(f1)
        }

        fun show_restourant(flex: LinearLayout, filter_tag:String){
            flex.removeAllViewsInLayout()
            for(i in 0..elemnt.size-1){
                if(elemnt[i].tag == filter_tag)
                    flex.addView(elemnt[i])
                else if (filter_tag == "")
                    flex.addView(elemnt[i])
            }
        }

        fun reset_button(my_iter:Int){
            for(i in 0..buttons.size-1){
                buttons[i].setBackgroundResource(R.drawable.shadow)
                buttons[i].setTextColor(getResources().getColor(R.color.textDark))
            }
            buttons[my_iter].setBackgroundResource(R.drawable.button_shadow)
            buttons[my_iter].setTextColor(getResources().getColor(R.color.colorWhite))
            buttons[my_iter].startAnimation(animations[0])
        }




        buttons[0].setOnClickListener {
            val my_iter = 0
            reset_button(my_iter)
            show_restourant(view.findViewById(R.id.basket_lin) , "Ожидаемые")
        }
        buttons[1].setOnClickListener{
            val my_iter = 1
            reset_button(my_iter)
            show_restourant(view.findViewById(R.id.basket_lin) , "Завершённые")
        }

        fun but(view: View) {

        }


        for(i in 0..5){
            add_elements("1234","Bartolomeo","Ожидаемые")
            add_elements("3214","MC","Завершённые")
            add_elements("2314","Chelentano","Ожидаемые")
        }
        show_restourant(view.findViewById(R.id.basket_lin),"")

        return view

    }
}