package com.akakim.legion.adapter.fragment

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.akakim.legion.R

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-18
 */


open class RecordingFragmentAdapter : FragmentStatePagerAdapter{


    val titles : List<String>

    val fragmentManager : FragmentManager
    val context : Context

    constructor(context : Context, fragmentManager: FragmentManager) : super (fragmentManager ){
        this.fragmentManager =  fragmentManager
        this.context = context

        titles = listOf(
                this.context.getString( R.string.tabTitleRecord),
                this.context.getString( R.string.tabTitleSavedRecordings)
        )

    }


    override fun getItem(position: Int): Fragment {


        val fragment : Fragment = Fragment()
//        FileViewerFragment

        when(position){

//            0 ->{
//                retu
//            }
            1->{

//                return FileViewerFragment.newInstance( )
            }

            else -> {

            }
        }
        return fragment

    }

    override fun getCount(): Int {

        return titles.size
    }
}