package com.akakim.legion.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment


/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-31
 */


open class BaseFragment : Fragment(){

    protected var mListener: OnFragmentInteractionListener? = null





    override fun onAttach(context: Context) {
        super.onAttach(context)


        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener{
        fun onFragmentInteraction( tag : String, uri : Uri )
    }
}