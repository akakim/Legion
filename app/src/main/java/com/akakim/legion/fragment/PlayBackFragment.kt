package com.akakim.legion.fragment

import android.app.DialogFragment
import android.content.Context
import android.net.Uri



/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-24
 */


class PlayBackFragment : DialogFragment(), OnFragmentInteractionListener{


    override fun onAttach(context: Context?) {
        super.onAttach(context)

//        if (context is OnFragmentInteractionListener) {
//            mListener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onFragmentInteraction(uri: Uri) {

    }

}