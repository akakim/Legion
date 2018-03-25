package com.akakim.legion.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.Menu
import com.akakim.legion.R
import com.akakim.legion.adapter.fragment.RecordingFragmentAdapter
import kotlinx.android.synthetic.main.activity_recording.*
import kotlinx.android.synthetic.main.activity_recording.view.*
import kotlinx.android.synthetic.main.toolbar_recording.*

class RecordingActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recording)

        viewPager.adapter = RecordingFragmentAdapter (this, supportFragmentManager )
        tabLayout.setupWithViewPager( viewPager )


        toolbar?.let {

            it.popupTheme =  R.style.ThemeOverlay_AppCompat_Light
            setSupportActionBar( it )
        }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {


//        menuInflater.inflate(com.akakim.utillibrary.R.menu.main_menu, menu )
        return true
    }


    class MyAdapter : FragmentPagerAdapter {

        val fragmentManager :FragmentManager
        constructor(fm : FragmentManager) : super(fm){
            fragmentManager = fm
        }

        override fun getItem(position: Int): Fragment {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun getCount(): Int {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }
}
