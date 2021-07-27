package com.example.myapplication
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pref = getSharedPreferences("mypref", Context.MODE_PRIVATE)

        if (pref.getBoolean("firststart", true)) {
            // update sharedpreference - another start wont be the first
            val editor = pref.edit()
            editor.putBoolean("firststart", false)
            editor.commit() // apply changes
            val adapter=MyAdapter(supportFragmentManager)
            adapter.addFragment(Slider1())
            adapter.addFragment(Slider0())
            viewPager.adapter=adapter
            // first start, show your dialog | first-run code goes here
        }
        else{
            startActivity(Intent(this,Glavnaya::class.java))
            finish()
        }

    }
    class MyAdapter(manager: FragmentManager):FragmentPagerAdapter(manager){
        private val FragmentList:MutableList<Fragment> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return FragmentList[position]
        }

        override fun getCount(): Int {
            return FragmentList.size
        }
        fun addFragment(fragment: Fragment){
            FragmentList.add(fragment)

        }
    }
    fun onClick(view: View){
        val currentPage:Int=viewPager.currentItem+1
        val t1 = view.findViewById<TextView>(R.id.textView)
        val t2 = view.findViewById<TextView>(R.id.textView3)
        if(currentPage<2){
            viewPager.currentItem=currentPage
            if(currentPage==1){
                //t1.setBackgroundResource(R.drawable.white_slider)
                //t2.setBackgroundResource(R.drawable.slider)
            }
        }
        else{
            startActivity(Intent(this,Glavnaya::class.java))
            finish()
        }
    }
}