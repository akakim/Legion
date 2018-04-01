package com.akakim.legion.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.akakim.legion.R
import com.akakim.utillibrary.service.RecordingService
import kotlinx.android.synthetic.main.fragment_record.*


class RecordFragment : BaseFragment(),View.OnClickListener {



    var startRecording : Boolean
    var pauseRecording : Boolean
    var timeWhenPaused : Long

    init {
        startRecording = true
        pauseRecording = true
        timeWhenPaused = 0
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

            position = arguments.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater?.inflate(R.layout.fragment_record, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        btnRecord.setOnClickListener( this )
        btnPause.setOnClickListener( this )
        btnPause.visibility = View.INVISIBLE

    }


    override fun onClick(v: View?) {


        when ( v?.id ){

            R.id.btnRecord ->{

                onRecord( startRecording )
                startRecording = !startRecording
            }
            R.id.btnPause ->{
                onPauseRecord( pauseRecording )
                pauseRecording = !pauseRecording
            }
        }
    }



    fun onRecord(start: Boolean ){

        var intent = Intent( context, RecordingService::class.java)


        if ( start ){

        }
        if( context != null ){

        }

    }

    fun onPauseRecord( boolean : Boolean ) {


    }
    companion object {

        open val ARG_POSITION   = "position"

             val LOG_TAG        = "RecordFragment"
             var position       =  -1
        fun newInstance(position: Int): RecordFragment {
            val fragment = RecordFragment()
            val args = Bundle()

            args.putInt( ARG_POSITION , position )
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
