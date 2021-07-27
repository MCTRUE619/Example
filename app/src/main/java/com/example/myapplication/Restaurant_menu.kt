package com.example.myapplication

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.flexbox.FlexboxLayout
import kotlinx.android.synthetic.main.activity_regestration.*
import kotlinx.android.synthetic.main.restoraunt.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

private const val ENDPOINT4 ="http://10.0.2.2:3006"
private const val OZENOCHKA ="/Ozenka"

private const val ENDPOINT3 ="http://10.0.2.2:3004"
private const val RESTABOUT ="/About"

private const val ENDPOINT2 = "http://10.0.2.2:3003"
private const val DISHLIKED = "/Liked"

private const val ENDPOINT = "http://10.0.2.2:3005/api"
private const val RESTORAUNT = "/restaurant"
private const val PRODUCT="products"
private const val NAME="name"
private const val WEIGHT="weight"
private const val PRICE="price"
private const val OZENKA="ozenka"
private const val TEGREST="tegrest"
private const val TEGDISH="tegdish"
var namerestor:String="f"
var restortime:String="f"
var restoranzenka:String="f"
var imagerestor:Int=R.drawable.ic_dark_tort
var i=1
val orderdishlist = mutableListOf<String>()
val orderdishimg = mutableListOf<Int>()
val kolvodish= mutableListOf<String>()
val zena= mutableListOf<String>()
var summa=0
var sumozenok:Double=0.0
class Restaurant_menu : AppCompatActivity() {
    private val Rating = arrayOf("Ужасно(1)", "Плохо(2)", "Нормально(3)", "Хорошо(4)", "Отлично(5)")
    val restorant = mutableListOf<String>()
    val weightdish = mutableListOf<String>()
    val pricedish = mutableListOf<String>()
    val tegrest = mutableListOf<String>()
    val tegdish = mutableListOf<String>()
    val likedmap = mutableMapOf<String,String>()
    val aboutrest = mutableMapOf<String,String>()
    val valuelike = mutableMapOf<String,Int>()
    val elemnt: MutableList<FrameLayout> = ArrayList()
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
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish1,
        R.drawable.dish2,
        R.drawable.dish2
    )
   /* val buttons = arrayOf<RelativeLayout>(
        findViewById(R.id.relativeLay1R),
        findViewById(R.id.relativeLay2R),
        findViewById(R.id.relativeLay3R),
        findViewById(R.id.relativeLay4R)
    )

    val icons = arrayOf(
        R.drawable.ic_menu_red,
        R.drawable.ic_cold_red,
        R.drawable.ic_hot_red,
        R.drawable.ic_drink_red,
        R.drawable.ic_menu_black,
        R.drawable.ic_cold_black,
        R.drawable.ic_hot_black,
        R.drawable.ic_drink_black
    )
    val texts = arrayOf<TextView>(
        this.findViewById(R.id.relativeBut1),
        this.findViewById(R.id.relativeBut2),
        this.findViewById(R.id.relativeBut3),
        this.findViewById(R.id.relativeBut4)
    )
    val animations: Array<Animation?> = arrayOf(
        AnimationUtils.loadAnimation(baseContext, R.anim.alpha),
        AnimationUtils.loadAnimation(baseContext, R.anim.alpha2),
        AnimationUtils.loadAnimation(baseContext, R.anim.combo2),
        AnimationUtils.loadAnimation(baseContext, R.anim.combo)
    )*/
    override fun onCreate(savedInstanceState: Bundle?) {
       val policy: StrictMode.ThreadPolicy =
           StrictMode.ThreadPolicy.Builder().permitAll().build()
       StrictMode.setThreadPolicy(policy)
       Thread{
           getrest()
           getlikedrest()
           getaboutrest()
       }.start()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.restoraunt)
        restopen.text= namerestor
       timeopen.text= restortime
       viewPager3.setImageResource(imagerestor)
       restozenka.text=restoranzenka
       textView28.text=summa.toString()
        val container = findViewById(R.id.PlusOther) as ConstraintLayout


     //   show_restourant(flexrestor, elemnt.size - 1)
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
        params1.setMargins(int_to_dp(10), int_to_dp(33), 0, int_to_dp(10))
        frame_layout2.layoutParams = params1
        frame_layout2.setBackgroundResource(R.drawable.layout3)

        val likebtn = ImageButton(baseContext)
        var likeparam=FrameLayout.LayoutParams(int_to_dp(25), int_to_dp(25))
        likeparam.setMargins(900,60,0,0)
        likebtn.layoutParams=likeparam
        likebtn.setImageResource(R.drawable.ic_heart_like_red)
        likebtn.tag=likedmap.get(name)
        if(likebtn.tag=="true"){
            likebtn.setImageResource(R.drawable.ic_heart_like_red)
        }else if(likebtn.tag=="false"){
            likebtn.setImageResource(R.drawable.ic_heart_black)
        }
        likebtn.setOnClickListener {
            likeClick(likebtn,name)


        }
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
        text_view2.text = price
        text_view2.textSize = 12f
        text_view2.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
        text_view2.setTextColor(getResources().getColor(R.color.textSilver))
        val text_view4 = TextView(baseContext)//Вес
        val paramsw = FrameLayout.LayoutParams(wc, wc)
        paramsw.setMargins(int_to_dp(121), int_to_dp(70), 0, 0)
        text_view4.layoutParams = paramsw
        text_view4.text = weight
        text_view4.textSize = 12f
        text_view4.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL)
        text_view4.setTextColor(getResources().getColor(R.color.textSilver))
        val frame_layout3 = FrameLayout(baseContext)//Кнопки
        val params2=FrameLayout.LayoutParams(int_to_dp(75), int_to_dp(42))
        params2.setMargins(int_to_dp(259), int_to_dp(30), int_to_dp(13), int_to_dp(20))
        frame_layout3.layoutParams=params2
        frame_layout3.setBackgroundResource(R.drawable.shadow)
        var i=1
        var text_view1 = TextView(baseContext)//Колво
        var params8 = FrameLayout.LayoutParams(wc, int_to_dp(27))
        params8.setMargins(int_to_dp(25), int_to_dp(10), int_to_dp(8), 0)
        text_view1.layoutParams = params8
        text_view1.text = ""+i
        text_view1.textSize = 12f
        text_view1.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
        text_view1.setTextColor(getResources().getColor(R.color.textDark))
        val imgb1 = ImageButton(baseContext)//Минус
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
        val imgb2 = ImageButton(baseContext)//Плюс
        val params7=FrameLayout.LayoutParams(int_to_dp(12), int_to_dp(33))
        params7.setMargins(int_to_dp(45), int_to_dp(5), int_to_dp(8), int_to_dp(10))
        imgb2.layoutParams=params7
        imgb2.setImageResource(R.drawable.plus)
        imgb2.setOnClickListener {
            i++
            text_view1.text=""+i
        }
        val add=Button(baseContext)
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
        frame_layout1.addView(likebtn)
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
    fun show_restourant(flex: FlexboxLayout, length: Int,filter_tag:String){
        flex.removeAllViewsInLayout()
        if(length<=elemnt.size)
            for(i in 0..length){
                if(tegrest[i]==restopen.text.toString()&&elemnt[i].tag==filter_tag)
                flex.addView(elemnt[i])
                else if(tegrest[i]==restopen.text.toString()&&filter_tag=="")
                    flex.addView(elemnt[i])
            }
    }
    @WorkerThread
    private fun getrest() {
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
                val tegdishs = name.getString(TEGDISH)
                restorant.add(title)
               weightdish.add(weight)
                pricedish.add(price)
                tegrest.add(tegrests)
                tegdish.add(tegdishs)
            }
        }
        httpURLConnection.disconnect()
        Handler(Looper.getMainLooper()).post {
            for(i in 0 until restorant.size) {
                add_elements(
                    dishimg[i],
                    restorant[i],
                    weightdish[i],
                    pricedish[i],
                    tegdish[i],
                    "false"
                )
            }
        }
    }
    @WorkerThread
    fun addRest(oz: Int) {
        /*val rr=JSONObject().apply {
            put("name", "q")
            put("price", 0)
            put("weight", 0)

        }
        val d = arrayOf(rr)*/
        val httpUrlConnection= URL(ENDPOINT + RESTORAUNT).openConnection() as HttpURLConnection
        val body = JSONObject().apply {
           /* put("name", "qw")
            put("adress", "qw")
            put("teg", "qw")
            put("otziv", "qw")
            put("products", d)*/
            put(OZENKA, oz)

        }

        httpUrlConnection.apply {
            connectTimeout = 10000 // 10 seconds
            requestMethod = "POST"
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
        }
        OutputStreamWriter(httpUrlConnection.outputStream).use {
            it.write(body.toString())
        }
        httpUrlConnection.responseCode
        httpUrlConnection.disconnect()
    }
   /*  fun reset_buttons(myIter:Int){
        val anim: Animation? = AnimationUtils.loadAnimation(this, R.anim.alpha);
        val animtext: Animation? = AnimationUtils.loadAnimation(this, R.anim.combo);
        val size = 3
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
        buttons[myIter].startAnimation(anim)
        textViewanim.startAnimation(animtext)
        buttons[myIter].layoutParams.width=sizeInPX2
    }*/
    fun plus(view: View){
        val tt = findViewById(R.id.iterator)as TextView;
        tt.text = (tt.text.toString().toInt() + 1).toString()
    }
    fun minus(view: View){
        val tt = findViewById(R.id.iterator)as TextView;
        if(tt.text.toString().toInt()>=1)
            tt.text = (tt.text.toString().toInt() - 1).toString()
    }
    fun Ordered(view: View){
        val intent= Intent(this, Order::class.java)
        startActivity(intent)
    }
    fun openRestaurantMenu(view: View){
        val intent= Intent(this, Glavnaya1::class.java)
        startActivity(intent)
        finish()
    }

    fun all_menu(view: View){
        relativeLay1R.setBackgroundResource(R.drawable.button_shadow)
        relativeLay2R.setBackgroundResource(R.drawable.shadow)
        relativeLay3R.setBackgroundResource(R.drawable.shadow)
        relativeLay4R.setBackgroundResource(R.drawable.shadow)
        relativeImg1.setImageResource(R.drawable.ic_menu_red)
        relativeImg2.setImageResource(R.drawable.ic_cold_black)
        relativeImg3.setImageResource(R.drawable.ic_hot_black)
        relativeImg4.setImageResource(R.drawable.ic_drink_black)
        show_restourant(flexrestor, elemnt.size - 1,"")

     }
    fun cold_platters(view: View){
        relativeLay1R.setBackgroundResource(R.drawable.shadow)
        relativeLay2R.setBackgroundResource(R.drawable.button_shadow)
        relativeLay3R.setBackgroundResource(R.drawable.shadow)
        relativeLay4R.setBackgroundResource(R.drawable.shadow)
        relativeImg1.setImageResource(R.drawable.ic_menu_black)
        relativeImg2.setImageResource(R.drawable.ic_cold_red)
        relativeImg3.setImageResource(R.drawable.ic_hot_black)
        relativeImg4.setImageResource(R.drawable.ic_drink_black)
        show_restourant(flexrestor, elemnt.size - 1,"Закуски")

    }
    fun soups(view: View){
        relativeLay1R.setBackgroundResource(R.drawable.shadow)
        relativeLay2R.setBackgroundResource(R.drawable.shadow)
        relativeLay3R.setBackgroundResource(R.drawable.button_shadow)
        relativeLay4R.setBackgroundResource(R.drawable.shadow)
        relativeImg1.setImageResource(R.drawable.ic_menu_black)
        relativeImg2.setImageResource(R.drawable.ic_cold_black)
        relativeImg3.setImageResource(R.drawable.ic_hot_red)
        relativeImg4.setImageResource(R.drawable.ic_drink_black)
        show_restourant(flexrestor, elemnt.size - 1,"Горячее")

    }
    fun beverages(view: View){
        relativeLay1R.setBackgroundResource(R.drawable.shadow)
        relativeLay2R.setBackgroundResource(R.drawable.shadow)
        relativeLay3R.setBackgroundResource(R.drawable.shadow)
        relativeLay4R.setBackgroundResource(R.drawable.button_shadow)
        relativeImg1.setImageResource(R.drawable.ic_menu_black)
        relativeImg2.setImageResource(R.drawable.ic_cold_black)
        relativeImg3.setImageResource(R.drawable.ic_hot_black)
        relativeImg4.setImageResource(R.drawable.ic_drink_red)
        show_restourant(flexrestor, elemnt.size - 1,"Напитки")

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
            likedmap.put(name,liked)
            valuelike.put(name,i)
        }
        httpURLConnection.disconnect()
        Handler(Looper.getMainLooper()).post {

        }
    }

    @WorkerThread
    private fun getaboutrest() {
        val httpURLConnection= URL(ENDPOINT3 + RESTABOUT).openConnection() as HttpURLConnection
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
            val about = jsonrest.getString("about")
            aboutrest.put(name,about)
        }
        httpURLConnection.disconnect()
        Handler(Looper.getMainLooper()).post {

        }
    }
    @WorkerThread
    fun addaboutrest(name: String, about: String) {
        val httpUrlConnection= URL(ENDPOINT3 + RESTABOUT).openConnection() as HttpURLConnection
        val body = JSONObject().apply {
            put("name", name)
            put("about", about)

        }
        httpUrlConnection.apply {
            connectTimeout = 10000 // 10 seconds
            requestMethod = "POST"
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
        }
        OutputStreamWriter(httpUrlConnection.outputStream).use {
            it.write(body.toString())
        }
        httpUrlConnection.responseCode
        httpUrlConnection.disconnect()
    }

    @WorkerThread
    fun editlike(name: String, like: String,idus:Int) {
        val httpUrlConnection= URL(ENDPOINT2 + DISHLIKED+"/$idus").openConnection() as HttpURLConnection
        val body = JSONObject().apply {
            put("name", name)
            put("liked", like)

        }
        httpUrlConnection.apply {
            connectTimeout = 10000 // 10 seconds
            requestMethod = "PATCH"
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
        }
        OutputStreamWriter(httpUrlConnection.outputStream).use {
            it.write(body.toString())
        }
        httpUrlConnection.responseCode
        httpUrlConnection.disconnect()
    }
    fun likeClick(view: View,name: String){
        val b = view as ImageButton
        var primer=1
        for((key,value)in valuelike){
            if(name==key){
                primer=value+2
            }
        }
        for((key,value)in likedmap) {
            if (key == name && b.tag == "false") {
                Thread{
                    editlike(name,"true",primer)
                }.start()
                b.setImageResource(R.drawable.ic_heart_like_red)

            } else if (key == name && b.tag == "true") {
                Thread{
                    editlike(name,"false",primer)
                }.start()
                b.setImageResource(R.drawable.ic_heart_black)
            }

        }
        if(b.tag=="false"){
            b.tag=="true"
        }else if(b.tag=="true"){
            b.tag="false"
        }

    }
    fun otziv(view:View){
        val alert2 = androidx.appcompat.app.AlertDialog.Builder(this)
        alert2.setTitle("Добавить отзыв про $namerestor")
        val input = EditText(this)
        alert2.setView(input)
        alert2.setPositiveButton("Ok") { dialog, whichButton ->
            val value: String = input.text.toString()
            Thread{
               addaboutrest(namerestor,value)
            }.start()
        }
        alert2.show()
    }
    fun otzivs(view:View){
        val alert = androidx.appcompat.app.AlertDialog.Builder(this)
        alert.setTitle("Отзывы про $namerestor")
        for((key,value) in aboutrest){
           if(key==namerestor){
               alert.setMessage(value)
           }

        }

        alert.show()
    }

    @WorkerThread
    fun editozenky(name: String, ozenka: String,kolvo:String,idus:Int) {
        val httpUrlConnection= URL(ENDPOINT4 + OZENOCHKA+"/$idus").openConnection() as HttpURLConnection
        val body = JSONObject().apply {
            put("name", name)
            put("ozenka", ozenka)
            put("kolvo",kolvo)

        }
        httpUrlConnection.apply {
            connectTimeout = 10000 // 10 seconds
            requestMethod = "PATCH"
            doOutput = true
            setRequestProperty("Content-Type", "application/json")
        }
        OutputStreamWriter(httpUrlConnection.outputStream).use {
            it.write(body.toString())
        }
        httpUrlConnection.responseCode
        httpUrlConnection.disconnect()
    }
    fun rating(view: View){
        var ozenili=0

        var kolvoozenki=0
        var index=1
                val builder = AlertDialog.Builder(this)
                    builder.setTitle("Оцените ресторан")
                    .setSingleChoiceItems(
                        Rating, -1
                    ) { dialog, item ->
                        Toast.makeText(
                            this, "Оценки ресторана:  ${Rating[item]}",
                            Toast.LENGTH_SHORT
                        ).show()

                       ozenili=item+1
                    }
                    .setPositiveButton(
                        "OK"
                    ) { dialog, id ->
                        kolvoozenki= restozenkal.getValue(namerestor)[1].toInt()+1
                        when(namerestor) {
                            "Ulitka" ->index=1
                            "Shaverma Like"->index=2
                            "Pizza"->index=3
                            "Sushi" ->index=1
                        }
                        sumozenok+=restozenkal.getValue(namerestor)[0].toDouble()+ozenili

                        Thread {
                                           editozenky(  namerestor, sumozenok.toString(),  kolvoozenki.toString(), index)
                                }.start()


                        // User clicked OK, so save the selectedItems results somewhere
                        // or return them to the component that opened the dialog
                    }
                    .setNegativeButton("Отмена") { dialog, id ->
                    }

                builder.show()
            }
        }


/*val imageSlider = findViewById<ImageSlider>(R.id.viewPager3)

val slideModels: MutableList<SlideModel> = ArrayList()
slideModels.add(
    SlideModel(R.drawable.ic_dark_tort)
)
slideModels.add(
    SlideModel("https://reston.ua/uploads/img/zavedeniya/original/54212b9.jpg")
)
slideModels.add(
    SlideModel("https://www.dniprohotel.ua/images/m/dnipro-m.jpg")
)
imageSlider.setImageList(slideModels, true)*/