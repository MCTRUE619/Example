package com.example.myapplication
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap:GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
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

    override fun onMapReady(googleMap: GoogleMap) {
       mMap=googleMap
        val Dnepr = LatLng(48.45, 34.98333)
        mMap.addMarker(MarkerOptions().position(Dnepr).title("Dnepr"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Dnepr))
    }

}
