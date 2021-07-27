package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AlertDialog
import androidx.core.view.marginLeft
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.activity_find_menu.*
import kotlinx.android.synthetic.main.activity_pinned_buttons.*
import kotlinx.android.synthetic.main.activity_regestration.*
import kotlinx.android.synthetic.main.restoraunt.*
import org.json.JSONArray
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private const val ENDPOINT ="http://10.0.2.2:3005/api"
private const val RESTORAUNT="/restaurant"
private const val PRODUCT="products"
private const val NAME="name"
private const val ADRESS="adress"
private const val TEG="teg"
private const val WEIGHT="weight"
private const val PRICE="price"
private const val OZENKA="ozenka"
private const val OTZIV="otziv"
private const val TEGREST="tegrest"

class Find_menu : Fragment(R.layout.activity_find_menu) {
    val restorantfind = mutableListOf<String>()
    val timerestorfind = mutableListOf<String>()
    var restaurantstegfind = mutableListOf<String>()
    var restozenkalfind = mutableMapOf<String,Double>()
    val restotzivfind = mutableListOf<String>()
    val restorantdishfind = mutableListOf<String>()
    val weightdishfind = mutableListOf<String>()
    val pricedishfind = mutableListOf<String>()
    val tegrestfind = mutableListOf<String>()
    val elemnt: MutableList<FrameLayout> = ArrayList()
    val elemnt2: MutableList<FrameLayout> = ArrayList()
    val dishimg= arrayOf(
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish1,
        R.drawable.dish2
    )
    val restimg = arrayOf(
        R.drawable.ic_dark_tort,
        R.drawable.kfc_logo,
        R.drawable.ic_dark_tort,
        R.drawable.lovely_sushi_here
    )
    val restnamf = arrayOf(
        "Ulitka",
        "Shaverma Like",
        "Pizza",
        "Sushi"
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       Thread{
            getrest()
        }.start()
        val view: View = inflater.inflate(R.layout.activity_find_menu, container, false)
        val buttons:Array<Button> = arrayOf(
            view.findViewById(R.id.Restaurant) as Button,
            view.findViewById(R.id.Kuhni) as Button
        )
        val find=view.findViewById(R.id.editTextText)as EditText
        for (i in 0..3) {
            restozenkalfind.put(restnamf[i], 0.0)
        }
        val elemnt: MutableList<FrameLayout> = ArrayList()
        val animations: Array<Animation?> = arrayOf(
            AnimationUtils.loadAnimation(requireContext(), R.anim.alpha),
            AnimationUtils.loadAnimation(requireContext(), R.anim.alpha2),
            AnimationUtils.loadAnimation(requireContext(), R.anim.combo2)
        )



        fun reset_button(my_iter:Int){
            for(i in 0..buttons.size-1){
                buttons[i].setBackgroundResource(R.drawable.shadow)
                buttons[i].setTextColor(getResources().getColor(R.color.textDark))
            }
            buttons[my_iter].setBackgroundResource(R.drawable.button_shadow)
            buttons[my_iter].setTextColor(getResources().getColor(R.color.colorWhite))
            buttons[my_iter].startAnimation(animations[my_iter])
        }




        buttons[0].setOnClickListener {
            val my_iter = 0
            reset_button(my_iter)

            show_restourant(view.findViewById(R.id.flex) , "")
        }
        buttons[1].setOnClickListener {
            val my_iter2 = 1
            reset_button(my_iter2)
            show_dish(view.findViewById(R.id.flex) , "")
        }

        find.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
              for(i in 0 until restorantfind.size){
                  val str=Regex(restorantfind[i]).find(find.text)
                  if(str!=null){
                      show_restourant(view.findViewById(R.id.flex),restorantfind[i])
                  }
              }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        return view

    }

    fun int_to_dp(x:Int): Int {
        val value = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            x.toFloat(),
            resources.displayMetrics
        ).toInt()
        return value
    }
    fun create_frame(img:Int,name:String, tag_name:String,time:String,ozenka:Double): FrameLayout {
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
            namerestor=name
            restortime=time
            imagerestor=img
            restoranzenka=ozenka.toString()
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

        val ozen = TextView(this.requireContext())//Оценка ресторана
        val paramsozen = LinearLayout.LayoutParams(0,0,1000f)
        ozen.text = ozenka.toString()
        paramsozen.width = wc
        paramsozen.height = wc
        paramsozen.setMargins(int_to_dp(24),int_to_dp(35),0,0)
        ozen.layoutParams = paramsozen
        ozen.textSize=12f
        ozen.setTypeface(Typeface.SANS_SERIF,Typeface.BOLD)
        ozen.setTextColor(getResources().getColor(R.color.textDark))

        val img=ImageView(this.requireContext())//Звезды ресторана
        val params4 = LinearLayout.LayoutParams(int_to_dp(12),int_to_dp(10))
        params4.setMargins(int_to_dp(44),int_to_dp(40),0,0)
        img.setImageResource(R.drawable.gratis_png_icono_de_estrella_ico_naranja_splat_s_thumbnail)
        img.layoutParams= params4


        /*  val heart=ImageButton(this.requireContext())//лайк ресторана
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
  */

        val fl3 = FrameLayout(this.requireContext())//контейнер для текста с временем
        val params6 = LinearLayout.LayoutParams(wc,wc)
        params6.setMargins(int_to_dp(64),int_to_dp(25),0,0)
        fl3.layoutParams= params6
        fl3.setPadding(100,25,0,0)


        val txt =TextView(this.requireContext()) //Время работы ресторана
        val params7 = LinearLayout.LayoutParams(wc,wc)
        txt.text = time
        txt.layoutParams = params7
        txt.textSize = 11f
        txt.setTypeface(Typeface.SANS_SERIF,Typeface.NORMAL)
        txt.setTextColor(getResources().getColor(R.color.textSilver))
        txt.setPadding(21, 9, 0, 0)


        fl3.addView(txt)
        fl2.addView(tv)
        fl2.addView(ozen)
        fl2.addView(img)
        fl2.addView(fl3)
        fl.addView(iv)
        fl.addView(fl2)
        f.addView(fl)
        return f
    }
    fun create_blydo(img: Int, name: String, weight: String, price: String, tag_name: String): FrameLayout {
        val mp = LinearLayout.LayoutParams.MATCH_PARENT
        val wc = LinearLayout.LayoutParams.WRAP_CONTENT

        val frame_layout = FrameLayout(this.requireContext())//Главный контейнер
        frame_layout.layoutParams = FrameLayout.LayoutParams(wc, wc)
        frame_layout.setPadding(0, int_to_dp(11), 0, 0)
        frame_layout.tag = tag_name

        val frame_layout1 = FrameLayout(this.requireContext())//Контейнер с картинкой
        frame_layout1.layoutParams = FrameLayout.LayoutParams(wc, wc)

        val image_view  = ImageView(this.requireContext())//Картинка
        var params3 = FrameLayout.LayoutParams(int_to_dp(136), int_to_dp(136))
        params3.setMargins(int_to_dp(-5), 0, 0, 0)
        image_view.setImageResource(img)
        image_view.layoutParams = params3

        val frame_layout2 = FrameLayout(this.requireContext())//Блюдо
        var params1 = FrameLayout.LayoutParams(int_to_dp(343), int_to_dp(104))
        params1.setMargins(int_to_dp(10), int_to_dp(33), 0, int_to_dp(10))
        frame_layout2.layoutParams = params1
        frame_layout2.setBackgroundResource(R.drawable.layout3)

        var text_view3 = TextView(this.requireContext())//Название
        var params5 = FrameLayout.LayoutParams(wc, wc)
        params5.setMargins(int_to_dp(120), int_to_dp(11), 0, 0)
        text_view3.layoutParams = params5
        text_view3.text = name
        text_view3.textSize = 18f
        text_view3.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
        text_view3.setTextColor(getResources().getColor(R.color.textDark))
        val text_view2 = TextView(this.requireContext())//Цена
        val params4 = FrameLayout.LayoutParams(wc, wc)
        params4.setMargins(int_to_dp(121), int_to_dp(50), 0, 0)
        text_view2.layoutParams = params4
        text_view2.text = price
        text_view2.textSize = 12f
        text_view2.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
        text_view2.setTextColor(getResources().getColor(R.color.textSilver))
        val text_view4 = TextView(this.requireContext())//Вес
        val paramsw = FrameLayout.LayoutParams(wc, wc)
        paramsw.setMargins(int_to_dp(121), int_to_dp(70), 0, 0)
        text_view4.layoutParams = paramsw
        text_view4.text = weight
        text_view4.textSize = 12f
        text_view4.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
        text_view4.setTextColor(getResources().getColor(R.color.textSilver))
        val frame_layout3 = FrameLayout(this.requireContext())//Кнопки
        val params2=FrameLayout.LayoutParams(int_to_dp(75), int_to_dp(42))
        params2.setMargins(int_to_dp(259), int_to_dp(30), int_to_dp(13), int_to_dp(20))
        frame_layout3.layoutParams=params2
        frame_layout3.setBackgroundResource(R.drawable.shadow)
        var i=1
        var text_view1 = TextView(this.requireContext())//Колво
        var params8 = FrameLayout.LayoutParams(wc, int_to_dp(27))
        params8.setMargins(int_to_dp(25), int_to_dp(10), int_to_dp(8), 0)
        text_view1.layoutParams = params8
        text_view1.text = ""+i
        text_view1.textSize = 12f
        text_view1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
        text_view1.setTextColor(getResources().getColor(R.color.textDark))
        val imgb1 = ImageButton(this.requireContext())//Минус
        val params6=FrameLayout.LayoutParams(int_to_dp(11), int_to_dp(10))
        params6.setMargins(int_to_dp(5), int_to_dp(13), int_to_dp(50), int_to_dp(15))

        imgb1.layoutParams=params6
        imgb1.setImageResource(R.drawable.minus)
        imgb1.setOnClickListener {
            if(i>0) {
                i--
                text_view1.text = "" + i
            }
            else text_view1.text=" "

        }
        val imgb2 = ImageButton(this.requireContext())//Плюс
        val params7=FrameLayout.LayoutParams(int_to_dp(12), int_to_dp(33))
        params7.setMargins(int_to_dp(45), int_to_dp(5), int_to_dp(8), int_to_dp(10))
        imgb2.layoutParams=params7
        imgb2.setImageResource(R.drawable.plus)
        imgb2.setOnClickListener {
            i++
            text_view1.text=""+i
        }
        val add=Button(this.requireContext())
        val paramadd=FrameLayout.LayoutParams(int_to_dp(100), int_to_dp(25))
        paramadd.setMargins(int_to_dp(239), int_to_dp(70), int_to_dp(13), int_to_dp(20))
        add.layoutParams=paramadd
        add.text="В корзину"
        add.setBackgroundResource(R.drawable.layout2)
        add.setOnClickListener {
            val kol:String=text_view1.text.toString()
            val price:String=text_view2.text.toString()
            summa=summa+(kol.toInt()*price.toInt())
            textView28.text=summa.toString()
            orderdishimg.add(img)
            orderdishlist.add(name)
            kolvodish.add(kol)
            zena.add(price)
        }
        frame_layout3.addView(imgb1)
        frame_layout3.addView(text_view1)
        frame_layout3.addView(imgb2)
        frame_layout2.addView(add)
        frame_layout2.addView(frame_layout3)
        frame_layout2.addView(text_view3)
        frame_layout2.addView(text_view4)
        frame_layout2.addView(text_view2)
        frame_layout1.addView(frame_layout2)
        frame_layout1.addView(image_view)


        frame_layout.addView(frame_layout1)
        return frame_layout

    }
    fun add_elements(img:Int,name:String, tag_name:String, time:String,ozenka: Double) {
        var f1:FrameLayout
            f1 = create_frame(img, name, tag_name, time,ozenka)
        elemnt.add(f1)
    }
    fun add_dish( img: Int, name: String, weight: String, price: String, tag_name: String) {
        var f1: FrameLayout
        f1 = create_blydo(img, name, weight, price, tag_name)
        elemnt2.add(f1)
    }
    fun show_restourant(flex: FlexboxLayout, filter_tag:String){
        flex.removeAllViewsInLayout()
        for(i in 0..elemnt.size-1){
            val str=Regex(elemnt[i].tag.toString()).find(filter_tag)
         //  if(elemnt[i].tag == filter_tag)
            if(str!=null)
                flex.addView(elemnt[i])
            else if (filter_tag == "")
                flex.addView(elemnt[i])
        }
    }
    fun show_dish(flex: FlexboxLayout, filter_tag:String){
        flex.removeAllViewsInLayout()
        for(i in 0..elemnt2.size-1){
            if(elemnt2[i].tag == filter_tag)
                flex.addView(elemnt2[i])
            else if (filter_tag == "")
                flex.addView(elemnt2[i])
        }
    }
    @WorkerThread
    fun getrest() {


        val httpURLConnection = URL(ENDPOINT + RESTORAUNT).openConnection() as HttpURLConnection
        httpURLConnection.apply {
            connectTimeout = 20000
            requestMethod = "GET"
            doInput = true
        }
        if (httpURLConnection.responseCode != HttpURLConnection.HTTP_OK) {
            return
        }
        val streamReader = InputStreamReader(httpURLConnection.inputStream)
        var text: String = ""
        streamReader.use {
            text = it.readText()
        }


        val json = JSONArray(text)
        for (i in 0 until json.length()) {
            val jsonrest=json.getJSONObject(i)
            val name = jsonrest.getString(NAME)
            val time = jsonrest.getString(ADRESS)
            val teg = jsonrest.getString(TEG)
            val otziv = jsonrest.getString(OTZIV)
            restorantfind.add(name)
            timerestorfind.add(time)
            restaurantstegfind.add(name)
            restotzivfind.add(otziv)
        }
        for(i in 0 until json.length()){
            for(j in 0 until json.length()) {
                val jsonrest = json.getJSONObject(i)
                val names = jsonrest.getJSONArray(PRODUCT)
                val name = names.getJSONObject(j)
                val title = name.getString(NAME)
                val weight = name.getString(WEIGHT)
                val price = name.getString(PRICE)
                val tegrests = name.getString(TEGREST)
                restorantdishfind.add(title)
                weightdishfind.add(weight)
                pricedishfind.add(price)
                tegrestfind.add(title)
            }
        }
        httpURLConnection.disconnect()
        Handler(Looper.getMainLooper()).post {
            for(i in 0 until restorant.size){
                add_elements(restimg[i], restorantfind[i], restaurantstegfind[i], timerestorfind[i],restozenkalfind.getValue(restorantfind[i]))
            }
            for(i in 0 until restorantdishfind.size){
                add_dish(dishimg[i], restorantdishfind[i], weightdishfind[i], pricedishfind[i],tegrestfind[i])
            }
        }
    }
}
