package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AdapterR(private val tegproverki:String,private val razmer:Int): RecyclerView.Adapter<AdapterR.MyViewHolder>() {
    val restimgsp = arrayOf(
        R.drawable.ic_dark_tort,
        R.drawable.kfc_logo,
        R.drawable.lovely_sushi_here,
        R.drawable.ic_dark_tort,
        R.drawable.kfc_logo,
        R.drawable.lovely_sushi_here,
        R.drawable.ic_dark_tort,
        R.drawable.kfc_logo,
        R.drawable.lovely_sushi_here,
        R.drawable.ic_dark_tort,
        R.drawable.kfc_logo,
        R.drawable.lovely_sushi_here
    )
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var title:TextView? = null
    var time:TextView? = null
    var startext:TextView? = null
    var starimg:ImageView? = null
    var restimg:ImageView? = null
    var tegr:TextView? = null
        init {

            title = itemView.findViewById(R.id.textView26)
            time = itemView.findViewById(R.id.textView35)
            startext = itemView.findViewById(R.id.textViewstar)
            starimg = itemView.findViewById(R.id.imageView31)
            restimg = itemView.findViewById(R.id.test3)
            tegr = itemView.findViewById(R.id.textViewtegg)
        }

    }
/*
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.maket, parent, false)
        return MyViewHolder(itemView)
    }
  /*  fun onClick(v: View?) {
        val intent = Intent(v?.context, Restaurant_menu::class.java)
        v?.context?.startActivity(intent)
    }*/
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      if(tegproverki!=""&& tegproverki== restaurantsteg[position]) {
          holder.tegr?.text = restaurantsteg[position]
          holder.title?.text = restorant[position]
          holder.time?.text = timerestor[position]
          if(spisok2[position].toInt()==0) {
              holder.startext?.text = spisok[position]
          }else   holder.startext?.text =
              (spisok[position].toDouble() / spisok2[position].toInt()).toString()
          holder.starimg?.setImageResource(R.drawable.gratis_png_icono_de_estrella_ico_naranja_splat_s_thumbnail)
          holder.restimg?.setImageResource(restimgsp[position])
          holder.restimg?.setOnClickListener {
              namerestor = restorant[position]
              restortime = timerestor[position]
              imagerestor = restimgsp[position]
              restoranzenka = spisok[position]
              val intent = Intent(holder.itemView?.context, Restaurant_menu::class.java)
              holder.itemView?.context?.startActivity(intent)
              // onClick(holder.itemView)
          }
      }else if (tegproverki==""){
          holder.tegr?.text = restaurantsteg[position]
          holder.title?.text = restorant[position]
          holder.time?.text = timerestor[position]
          if(spisok2[position].toInt()==0) {
              holder.startext?.text = spisok[position]
          }else   holder.startext?.text =
              (spisok[position].toDouble() / spisok2[position].toInt()).toString()
          holder.starimg?.setImageResource(R.drawable.gratis_png_icono_de_estrella_ico_naranja_splat_s_thumbnail)
          holder.restimg?.setImageResource(restimgsp[position])
          holder.restimg?.setOnClickListener {
              namerestor = restorant[position]
              restortime = timerestor[position]
              imagerestor = restimgsp[position]
              restoranzenka = spisok[position]
              val intent = Intent(holder.itemView?.context, Restaurant_menu::class.java)
              holder.itemView?.context?.startActivity(intent)
              // onClick(holder.itemView)
          }
      }else if(tegproverki==restaurantsteg[position+2]) {
          holder.tegr?.text = restaurantsteg[position + 2]
          holder.title?.text = restorant[position + 2]
          holder.time?.text = timerestor[position + 2]
          if(spisok2[position+2].toInt()==0) {
              holder.startext?.text = spisok[position+2]
          }else   holder.startext?.text =
              (spisok[position+2].toDouble() / spisok2[position+2].toInt()).toString()
          holder.starimg?.setImageResource(R.drawable.gratis_png_icono_de_estrella_ico_naranja_splat_s_thumbnail)
          holder.restimg?.setImageResource(restimgsp[position + 2])
          holder.restimg?.setOnClickListener {
              namerestor = restorant[position + 2]
              restortime = timerestor[position + 2]
              imagerestor = restimgsp[position + 2]
              restoranzenka = spisok[position + 2]
              val intent = Intent(holder.itemView?.context, Restaurant_menu::class.java)
              holder.itemView?.context?.startActivity(intent)
          }
      }else if(tegproverki==restaurantsteg[position+3]){
          holder.tegr?.text = restaurantsteg[position+3]
          holder.title?.text = restorant[position+3]
          holder.time?.text = timerestor[position+3]
          if(spisok2[position+3].toInt()==0) {
              holder.startext?.text = spisok[position+3]
          }else   holder.startext?.text =
              (spisok[position+3].toDouble() / spisok2[position+3].toInt()).toString()
          holder.starimg?.setImageResource(R.drawable.gratis_png_icono_de_estrella_ico_naranja_splat_s_thumbnail)
          holder.restimg?.setImageResource(restimgsp[position+3])
          holder.restimg?.setOnClickListener {
              namerestor = restorant[position+3]
              restortime = timerestor[position+3]
              imagerestor = restimgsp[position+3]
              restoranzenka = spisok[position+3]
              val intent = Intent(holder.itemView?.context, Restaurant_menu::class.java)
              holder.itemView?.context?.startActivity(intent)
          }
      }
    }

    override fun getItemCount(): Int {
       return razmer
    }
}