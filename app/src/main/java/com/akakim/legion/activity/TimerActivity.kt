package com.akakim.legion.activity

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.akakim.legion.R
import com.akakim.legion.fragment.BaseFragment
import com.akakim.legion.fragment.routine.RoutineInputFragment
import com.akakim.legion.fragment.routine.TimerRoutineFragment

class TimerActivity :
        AppCompatActivity(),
        BaseFragment.OnFragmentInteractionListener,
        RoutineInputFragment.OnRoutineFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)



        // 만약 입력값이있다면 여기서 값을 설정한다.
        supportFragmentManager
                .beginTransaction()
                .replace( R.id.layoutFragment,  RoutineInputFragment() , RoutineInputFragment::javaClass.name )
                .commit()
    }



    override fun onRoutineFragmentInteraction(tag: String, bundle: Bundle) {



        val timerFrag       = TimerRoutineFragment()

        timerFrag.arguments = bundle
        supportFragmentManager
                .beginTransaction()
                .replace( R.id.layoutFragment,  timerFrag , TimerRoutineFragment::javaClass.name )
                .commit()

    }

    override fun onFragmentInteraction(tag: String, uri: Uri) {

    }


}
