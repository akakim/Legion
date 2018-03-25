package com.akakim.legion.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.akakim.legion.R

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [RecordFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [RecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecordFragment : Fragment() {



    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
//            mParam1 = arguments.getString(ARG_PARAM1)
//            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        if (mListener != null) {
            mListener!!.onFragmentInteraction(uri)
        }
    }

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

    companion object {

        fun newInstance(param1: String, param2: String): RecordFragment {
            val fragment = RecordFragment()
            val args = Bundle()

            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
