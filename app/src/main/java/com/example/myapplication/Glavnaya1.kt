package com.example.myapplication

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_glavnaya1.*
import kotlinx.android.synthetic.main.activity_pinned_buttons.*


class Glavnaya1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glavnaya1)
        //val basketmenu=Basket_menu()
        val findmenu=Find_menu()
        val personmenu=Person_menu()
        val savedmenu=Saved_menu()
        val pinnedbuttons=PinnedButtons()
        setFragment(pinnedbuttons)
        navigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu->setFragment(pinnedbuttons)

              //  R.id.orders->setFragment(basketmenu)
                R.id.find->setFragment(findmenu)
                R.id.person->setFragment(personmenu)
                R.id.saved->setFragment(savedmenu)
            }
            true
        }
    }
    private fun setFragment(fragment: Fragment)=supportFragmentManager.beginTransaction().apply {
        replace(R.id.flFragment,fragment)
        commit()
    }
    fun selectImageInAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }

    companion object {
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
    }

}