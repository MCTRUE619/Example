package com.example.myapplication


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.flexbox.FlexboxLayout
import com.google.android.gms.vision.Frame
import java.time.format.TextStyle


class PinnedButtons : Fragment(R.layout.activity_pinned_buttons) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.activity_pinned_buttons, container, false)
        val elemnt: MutableList<FrameLayout> = ArrayList()
        val relativeLay1 = view.findViewById(R.id.relativeLay1) as RelativeLayout
        val relativeLay2 = view.findViewById(R.id.relativeLay2) as RelativeLayout
        val relativeLay3 = view.findViewById(R.id.relativeLay3) as RelativeLayout
        val buttons: Array<RelativeLayout> = arrayOf(
            view.findViewById(R.id.relativeLay1) as RelativeLayout,
            view.findViewById(R.id.relativeLay2) as RelativeLayout,
            view.findViewById(R.id.relativeLay3) as RelativeLayout
        )
        val images: Array<ImageView> = arrayOf(
            view.findViewById(R.id.relativeImg1) as ImageView,
            view.findViewById(R.id.relativeImg2) as ImageView,
            view.findViewById(R.id.relativeImg3) as ImageView
        )
        var icons = arrayOf(
            R.drawable.ic_hand_like_white,
            R.drawable.ic_new_white,
            R.drawable.ic_sale_white,
            R.drawable.ic_hand_like_black,
            R.drawable.ic_new_black,
            R.drawable.ic_sale_black
        )
        val texts: Array<TextView> = arrayOf(
            view.findViewById(R.id.relativeBut1) as TextView,
            view.findViewById(R.id.relativeBut2) as TextView,
            view.findViewById(R.id.relativeBut3) as TextView
        )
        val animations: Array<Animation?> = arrayOf(
            AnimationUtils.loadAnimation(requireContext(), R.anim.alpha),
            AnimationUtils.loadAnimation(requireContext(), R.anim.alpha2),
            AnimationUtils.loadAnimation(requireContext(), R.anim.combo2),
            AnimationUtils.loadAnimation(requireContext(), R.anim.combo)
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


            val fl2=FrameLayout(this.requireContext())//нижний контейнер
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
            tv.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD)
            tv.setTextColor(getResources().getColor(R.color.textDark))
            tv.setPadding(21, 9, 0, 0)


            val img=ImageView(this.requireContext())//Звезды ресторана
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


            val txt =TextView(this.requireContext()) //Время работы ресторана
            val params7 = LinearLayout.LayoutParams(wc,wc)
            txt.text = "08:00 - 22:00"
            txt.layoutParams = params7
            txt.textSize = 11f
            txt.setTypeface(Typeface.SANS_SERIF,Typeface.NORMAL)
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

        fun show_restourant(flex:FlexboxLayout, filter_tag:String){
            flex.removeAllViewsInLayout()
            for(i in 0..elemnt.size-1){
                if(elemnt[i].tag == filter_tag)
                    flex.addView(elemnt[i])
                else if (filter_tag == "")
                    flex.addView(elemnt[i])
            }
        }

        fun reset_button(myIter:Int){
            for (i in 0..2) {
                if (i != myIter) {
                    buttons[i].setBackgroundResource(R.drawable.shadow)
                    images[i].setImageResource(icons[i + 3])
                    texts[i].text = ""
                    buttons[i].layoutParams.width = int_to_dp(84)
                }
            }
            buttons[myIter].setBackgroundResource(R.drawable.button_shadow)
            images[myIter].setImageResource(icons[myIter])
            buttons[myIter].startAnimation(animations[myIter])
            texts[myIter].startAnimation(animations[3])
            buttons[myIter].layoutParams.width = int_to_dp(150)
        }





        relativeLay1.setOnClickListener {
            val myIter = 0 as Int;
            texts[myIter].text = "Популярное"
            reset_button(myIter)
            texts[myIter].text = "Популярное"
            buttons[myIter].startAnimation(animations[myIter])
            texts[myIter].startAnimation(animations[3])
            buttons[myIter].layoutParams.width = int_to_dp(170)
            show_restourant(view.findViewById(R.id.flex),"")
            //Toast.makeText(this, "Its toast!", Toast.LENGTH_SHORT).show()
        }


        relativeLay2.setOnClickListener {
            val myIter = 1 as Int;
            texts[myIter].text = "Новинки"
            reset_button(myIter)
            for (i in 0..2) {
                if (i != myIter) {
                    buttons[i].setBackgroundResource(R.drawable.shadow)
                    images[i].setImageResource(icons[i + 3])
                    texts[i].text = ""
                    buttons[i].layoutParams.width = int_to_dp(84)
                }
            }

            val textViewanim = view.findViewById<TextView>(R.id.relativeBut2)
            buttons[myIter].setBackgroundResource(R.drawable.button_shadow)
            images[myIter].setImageResource(icons[myIter])
            texts[myIter].text = "Новинки"
            buttons[myIter].startAnimation(animations[myIter])
            texts[myIter].startAnimation(animations[3])
            buttons[myIter].layoutParams.width = int_to_dp(170)
            show_restourant(view.findViewById(R.id.flex),"Новинки")
            //Toast.makeText(view.context, "${(R.drawable.ic_dark_tort)::class.simpleName}", Toast.LENGTH_SHORT).show()
        }



        relativeLay3.setOnClickListener {
            val myIter = 2 as Int;
            texts[myIter].text = "Акция"
            reset_button(myIter)

            for (i in 0..2) {
                if (i != myIter) {
                    buttons[i].setBackgroundResource(R.drawable.shadow)
                    images[i].setImageResource(icons[i + 3])
                    texts[i].text = ""
                    buttons[i].layoutParams.width = int_to_dp(84)
                }
            }

            val textViewanim = view.findViewById<TextView>(R.id.relativeBut3)
            buttons[myIter].setBackgroundResource(R.drawable.button_shadow)
            images[myIter].setImageResource(icons[myIter])
            texts[myIter].text = "Акция"
            buttons[myIter].startAnimation(animations[myIter])
            texts[myIter].startAnimation(animations[3])
            buttons[myIter].layoutParams.width = int_to_dp(170)
            show_restourant(view.findViewById(R.id.flex),"Акция")
            //Toast.makeText(view.context, "${(R.drawable.ic_dark_tort)::class.qualifiedName}", Toast.LENGTH_SHORT).show()
        }





        //ПЕРВОЕ ЗАПОЛНЕНИЕ//
        for(i in 0..5){
            add_elements(R.drawable.ic_dark_tort,"Bartolomeo","Популярное","false")
            add_elements(R.drawable.kfc_logo,"KFC","Акция","false")
            add_elements(R.drawable.chel,"Chelentano","Новинки","false")
        }
        show_restourant(view.findViewById(R.id.flex),"")
        val imageSlider = view.findViewById<ImageSlider>(R.id.imageView8)

        val slideModels: MutableList<SlideModel> = ArrayList()
        slideModels.add(
            SlideModel("error")
        )
        slideModels.add(
            SlideModel("https://smachno.ua/wp-content/uploads/old_uploads/img/pg/72/11/1.jpg")
        )
        slideModels.add(
            SlideModel("https://i.obozrevatel.com/food/recipemain/2019/1/9/126.webp?size=600x400")
        )
        imageSlider.setImageList(slideModels, true)


        return view
    }
}