package com.example.myapplication


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.activity_pinned_buttons.*
import org.json.JSONArray
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

private const val ENDPOINT2 ="http://10.0.2.2:3006/"
private const val OZENKA="Ozenka"
private const val ENDPOINT ="http://10.0.2.2:3005/api"
private const val RESTORAUNT="/restaurant"
private const val NAME="name"
private const val ADRESS="adress"
private const val TEG="teg"
private const val OTZIV="otziv"
val restorant = mutableListOf<String>()
val timerestor = mutableListOf<String>()
var restaurantsteg = mutableListOf<String>()
var restozenkal = mutableMapOf<String, Array<String>>()
val restotziv = mutableListOf<String>()
val spisok = mutableListOf<String>()
val spisok2 = mutableListOf<String>()
var idx=0
class PinnedButtons : Fragment(R.layout.activity_pinned_buttons) {
    val elemnt: MutableList<FrameLayout> = ArrayList()
    val restimg = arrayOf(
        R.drawable.ic_dark_tort,
        R.drawable.kfc_logo,
        R.drawable.ic_dark_tort,
        R.drawable.lovely_sushi_here
    )
    val restnam = arrayOf(
        "Ulitka",
        "Shaverma Like",
        "Pizza"
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Thread{
            restorant.clear()
            timerestor.clear()
            restaurantsteg.clear()
            restotziv.clear()
            spisok.clear()
            restozenkal.clear()
            getozenky()
            getrest()

        }.start()

        val view= inflater.inflate(R.layout.activity_pinned_buttons, container,false)
       /* if(idx==0) {
            for (i in 0..2) {
                restozenkal.put(restnam[i], arrayOf("0","0"))
            }
        }*/
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerView.adapter = AdapterR("", restorant.size)
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
       // val flexer = view.findViewById(R.id.flex) as FlexboxLayout





    //  show_restourant(view.findViewById(R.id.flex),"")
        //ПЕРВОЕ ЗАПОЛНЕНИЕ//


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
        fun reset_button(myIter: Int){
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
            recyclerView.adapter = AdapterR("", restorant.size)
            val myIter = 0 as Int;
            texts[myIter].text = "Популярное"
            reset_button(myIter)
            texts[myIter].text = "Популярное"
            buttons[myIter].startAnimation(animations[myIter])
            texts[myIter].startAnimation(animations[3])
            buttons[myIter].layoutParams.width = int_to_dp(170)
            recyclerView.adapter = AdapterR("", restorant.size)

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
            recyclerView.adapter = AdapterR("Новинки",2)

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


            recyclerView.adapter = AdapterR("Акция",2)
             }

        //  fetchJson()
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


    @WorkerThread
    private fun getrest() {
        val httpURLConnection= URL(ENDPOINT + RESTORAUNT).openConnection() as HttpURLConnection
        httpURLConnection.apply{
            connectTimeout=10000
            requestMethod="GET"
            doInput=true
        }

        if(httpURLConnection.responseCode!=HttpURLConnection.HTTP_OK){
            return
        }
        val streamReader=InputStreamReader(httpURLConnection.inputStream)
        var text=""
        streamReader.use {
            text=it.readText()
        }
        val json=JSONArray(text)

        for(i in 0 until json.length()){
            val jsonrest=json.getJSONObject(i)
            val name = jsonrest.getString(NAME)
            val time = jsonrest.getString(ADRESS)
            val teg = jsonrest.getString(TEG)
            val otziv = jsonrest.getString(OTZIV)

            restorant.add(name)
            timerestor.add(time)
            restaurantsteg.add(teg)
            restotziv.add(otziv)

        }
        httpURLConnection.disconnect()
        Handler(Looper.getMainLooper()).post {
            for(i in 0..3) {

            }
        }
    }

    @WorkerThread
    private fun getozenky() {
        val httpURLConnection= URL(ENDPOINT2 + OZENKA).openConnection() as HttpURLConnection
        httpURLConnection.apply{
            connectTimeout=10000
            requestMethod="GET"
            doInput=true
        }

        if(httpURLConnection.responseCode!=HttpURLConnection.HTTP_OK){
            return
        }
        val streamReader=InputStreamReader(httpURLConnection.inputStream)
        var text=""
        streamReader.use {
            text=it.readText()
        }
        val json=JSONArray(text)
        for(i in 0 until json.length()){
            val jsonrest=json.getJSONObject(i)
            val name = jsonrest.getString("name")
            val ozenkares = jsonrest.getString("ozenka")
            val kolvoozenok = jsonrest.getString("kolvo")
         spisok.add(ozenkares)
            spisok2.add(kolvoozenok)
         restozenkal.put(name, arrayOf(ozenkares, kolvoozenok))

        }
        httpURLConnection.disconnect()
        Handler(Looper.getMainLooper()).post {

        }
    }



}