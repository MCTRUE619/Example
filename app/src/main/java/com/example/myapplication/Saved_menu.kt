package com.example.myapplication

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.activity_saved_menu.*
import kotlinx.android.synthetic.main.restoraunt.*
import org.json.JSONArray
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private const val ENDPOINT ="http://10.0.2.2:3005/api"
private const val RESTORAUNT="/restaurant"
private const val ENDPOINT2 ="http://10.0.2.2:3003"
private const val DISHLIKED="/Liked"

private const val PRODUCT="products"
private const val NAME="name"
private const val WEIGHT="weight"
private const val PRICE="price"
private const val OZENKA="ozenka"
private const val TEGREST="tegrest"
class Saved_menu : Fragment(R.layout.activity_saved_menu) {
    val likedmap= mutableMapOf<String, String>()
    val restorant = mutableListOf<String>()
    val elemnt: MutableList<FrameLayout> = ArrayList()
    val weightdish = mutableListOf<String>()
    val pricedish = mutableListOf<String>()
    val tegrest = mutableListOf<String>()
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
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Thread{
            getlikedrest()
        }.start()
        Thread{
            getrest()
        }.start()
        val view: View = inflater.inflate(R.layout.activity_saved_menu, container, false)
        val textstart:View = view.findViewById(R.id.textViewliked) as Button
        val flexm = view.findViewById(R.id.flexsaved) as FlexboxLayout
        textstart.setOnClickListener {
            show_restourant(flexm, elemnt.size - 1)
        }

        return view

    }


    fun int_to_dp(x: Int): Int {
        val value = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            x.toFloat(),
            resources.displayMetrics
        ).toInt()
        return value
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
    fun add_elements(
        img: Int,
        name: String,
        weight: String,
        price: String,
        tag_name: String,
        like: String
    ) {
        var f1: FrameLayout
        f1 = create_blydo(img, name, weight, price, tag_name)
        elemnt.add(f1)
    }
    fun show_restourant(flex: FlexboxLayout, length: Int){
        flex.removeAllViews()
        if(length<=elemnt.size)
            for(i in 0..length){
                    flex.addView(elemnt[i])
            }
    }


    @WorkerThread
    private fun getlikedrest() {
        val httpURLConnection= URL(ENDPOINT2 + DISHLIKED).openConnection() as HttpURLConnection
        httpURLConnection.apply{
            connectTimeout=10000
            requestMethod="GET"
            doInput=true
        }

        if(httpURLConnection.responseCode!= HttpURLConnection.HTTP_OK){
            return
        }
        val streamReader= InputStreamReader(httpURLConnection.inputStream)
        var text=""
        streamReader.use {
            text=it.readText()
        }
        val json= JSONArray(text)
        for(i in 0 until json.length()) {
            val jsonrest = json.getJSONObject(i)
            val name = jsonrest.getString("name")
            val liked = jsonrest.getString("liked")
            likedmap.put(name, liked)
        }
        httpURLConnection.disconnect()
        Handler(Looper.getMainLooper()).post {

        }
    }


    @WorkerThread
    private fun getrest() {
        var idxlek=1
        val httpURLConnection= URL(ENDPOINT + RESTORAUNT).openConnection() as HttpURLConnection
        httpURLConnection.apply{
            connectTimeout=10000
            requestMethod="GET"
            doInput=true
        }

        if(httpURLConnection.responseCode!= HttpURLConnection.HTTP_OK){
            return
        }
        val streamReader= InputStreamReader(httpURLConnection.inputStream)
        var text=""
        streamReader.use {
            text=it.readText()
        }
        val json= JSONArray(text)
        for(i in 0 until json.length()){
            for(j in 0 until json.length()) {
                val jsonrest = json.getJSONObject(i)
                val names = jsonrest.getJSONArray(PRODUCT)
                val name = names.getJSONObject(j)
                val title = name.getString(NAME)
                val weight = name.getString(WEIGHT)
                val price = name.getString(PRICE)
                val tegrests = name.getString(TEGREST)
                restorant.add(title)
                weightdish.add(weight)
                pricedish.add(price)
                tegrest.add(tegrests)
            }
        }
        httpURLConnection.disconnect()
        Handler(Looper.getMainLooper()).post {
            for((key, value)in likedmap){
                    if (value == "true") {
                        add_elements(
                            dishimg[idxlek],
                            restorant[idxlek],
                            weightdish[idxlek],
                            pricedish[idxlek],
                            "Закуски",
                            "false"
                        )
                    }
                idxlek++
                }


            }
        }
    }

