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
import kotlinx.android.synthetic.main.activity_saved_menu.*

class Saved_menu : Fragment(R.layout.activity_saved_menu) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.activity_saved_menu, container, false)
        val buttons = arrayOf(
            view.findViewById(R.id.Restaurantsaved) as Button,
            view.findViewById(R.id.Bluda) as Button
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

        fun create_frame(img:Int,name:String, tag_name:String, like:String): FrameLayout {
            var params1 = FrameLayout.LayoutParams(int_to_dp(343), int_to_dp(190))
            val mp = LinearLayout.LayoutParams.MATCH_PARENT
            val wc = LinearLayout.LayoutParams.WRAP_CONTENT



            val f = FrameLayout(this.requireContext())//Главный контейнер
            f.layoutParams = FrameLayout.LayoutParams(wc,wc)
            f.tag = tag_name

            val fl = FrameLayout(this.requireContext())//Главный контейнер
            params1.setMargins(0,int_to_dp(16),0,int_to_dp(0))
            fl.layoutParams = params1
            fl.setBackgroundResource(R.drawable.shadow)
            fl.setPadding(10,0,10,20)


            val iv = ImageView(this.requireContext())//Картинка ресторана
            iv.setImageResource(img)
            iv.layoutParams = LinearLayout.LayoutParams(mp,wc)
            iv.scaleType = ImageView.ScaleType.CENTER_CROP
            iv.setOnClickListener {
                activity?.let {
                    val intent = Intent(it, Restaurant_menu::class.java)
                    it.startActivity(intent)
                }
            }


            val fl2= FrameLayout(this.requireContext())//нижний контейнер
            val params2 = LinearLayout.LayoutParams(0,0)
            params2.width = mp
            params2.height = int_to_dp(60)
            params2.setMargins(0,int_to_dp(130),0,0)
            fl2.layoutParams = params2
            fl2.setBackgroundColor(Color.WHITE)
            fl2.setBackgroundResource(R.drawable.bottom_block)


            val tv = TextView(this.requireContext())//Название ресторана
            val params3 = LinearLayout.LayoutParams(0,0,1000f)
            tv.text = name
            params3.width = wc
            params3.height = wc
            params3.setMargins(int_to_dp(21),int_to_dp(8),0,0)
            tv.layoutParams = params3
            tv.textSize = 20f
            tv.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
            tv.setTextColor(getResources().getColor(R.color.textDark))
            tv.setPadding(21, 9, 0, 0)


            val img= ImageView(this.requireContext())//Звезды ресторана
            val params4 = LinearLayout.LayoutParams(int_to_dp(58),int_to_dp(9))
            params4.setMargins(int_to_dp(24),int_to_dp(40),0,0)
            img.setImageResource(R.drawable.stars)
            img.layoutParams= params4


            val heart=ImageButton(this.requireContext())//лайк ресторана
            val params5 = LinearLayout.LayoutParams(wc,wc)
            params5.setMargins(int_to_dp(290),int_to_dp(7),0,0)
            heart.setImageResource(R.drawable.ic_heart_black)
            heart.layoutParams= params5
            heart.setBackgroundColor(Color.TRANSPARENT)
            heart.tag = like
            if (heart.tag == "false") {
                heart.setImageResource(R.drawable.ic_heart_black)
                heart.tag = "true"
            } else {
                heart.setImageResource(R.drawable.ic_heart_like_red)
                heart.tag = "false"
            }
            heart.setOnClickListener {
                if (heart.tag == "false") {
                    heart.setImageResource(R.drawable.ic_heart_black)
                    heart.tag = "true"
                } else {
                    heart.setImageResource(R.drawable.ic_heart_like_red)
                    heart.tag = "false"
                }
            }


            val fl3 = FrameLayout(this.requireContext())//контейнер для текста с временем
            val params6 = LinearLayout.LayoutParams(wc,wc)
            params6.setMargins(int_to_dp(40),int_to_dp(25),0,0)
            fl3.layoutParams= params6
            fl3.setPadding(100,25,0,0)


            val txt = TextView(this.requireContext()) //Время работы ресторана
            val params7 = LinearLayout.LayoutParams(wc,wc)
            txt.text = "08:00 - 22:00"
            txt.layoutParams = params7
            txt.textSize = 11f
            txt.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
            txt.setTextColor(getResources().getColor(R.color.textSilver))
            txt.setPadding(21, 9, 0, 0)


            fl3.addView(txt)
            fl2.addView(tv)
            fl2.addView(img)
            fl2.addView(fl3)
            fl2.addView(heart)
            fl.addView(iv)
            fl.addView(fl2)
            f.addView(fl)
            return f
        }

        fun add_elements(img:Int,name:String, tag_name:String, like:String) {
            val f1 = create_frame(img, name, tag_name, like)
            elemnt.add(f1)
        }

        fun show_restourant(flex: FlexboxLayout, filter_tag:String){
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


        val txt=view.findViewById<TextView>(R.id.myText)
        txt.setText("Всего"+elemnt.size)


        buttons[0].setOnClickListener {
            var my_iter = 0
            val idx = 0
            reset_button(idx)
            show_restourant(view.findViewById(R.id.flexsaved) , "Рестораны")

            val txt=view.findViewById<TextView>(R.id.myText)
            for(i in 0..elemnt.size-1) {
                if (elemnt[i].tag == "Рестораны")
                    my_iter += 1
            }
            txt.setText("Ресторанов "+my_iter)
        }
        buttons[1].setOnClickListener {
            var my_iter = 0
            val idx = 1
            reset_button(idx)
            show_restourant(view.findViewById(R.id.flexsaved) , "Блюда")
            val txt=view.findViewById<TextView>(R.id.myText)
            for(i in 0..elemnt.size-1) {
                if (elemnt[i].tag == "Блюда")
                    my_iter += 1
            }
            txt.setText("Блюд "+my_iter)
        }

        fun but(view: View) {

        }


        for(i in 0..5){
            add_elements(R.drawable.ic_dark_tort,"Bartolomeo","Рестораны","false")
            add_elements(R.drawable.kfc_logo,"Борщ","Блюда","false")
            add_elements(R.drawable.chel,"Сосиска","Рестораны","false")
        }
        show_restourant(view.findViewById(R.id.flexsaved),"")


        return view

    }
}