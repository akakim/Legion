package com.akakim.legion.fragment.record

import android.os.Bundle
import android.content.Intent
import android.os.Environment
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast

import com.akakim.legion.R
import com.akakim.legion.common.Constant
import com.akakim.legion.fragment.BaseFragment
import com.akakim.legion.service.RecordService

import kotlinx.android.synthetic.main.fragment_record.*
import java.io.File


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



        var intent = Intent( context, RecordService::class.java)

            if ( start ){

                btnRecord.text = "정지"

                Toast.makeText(context,"녹음 시작",Toast.LENGTH_SHORT).show()


                val folder = File(Environment.getExternalStorageDirectory() , Constant.defaultDirectory )

                if( !folder.exists()){
                    folder.mkdir()
                }


                // 시간제기 시작

                chronometer.apply {
                    this.base = SystemClock.elapsedRealtime()
                    chronometer.start()
                    setOnChronometerTickListener {

                    }
                }

                context.startService( intent )

                // keep screen on while recording
                activity.window.addFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON )

                tvRecordingStatus.text= "녹음중..."



            } else {


                // stop Recording
                // TODO : 버튼 이미지 설정



                //  시간 기록
                chronometer.stop()
                chronometer.base = SystemClock.elapsedRealtime()    //  초기화

                timeWhenPaused = 0
                tvRecordingStatus.text = " 버튼을 클릭하시면 녹음이 시작됩니다."


                context.stopService( intent )

                // keep screen on while recording
                activity.window.clearFlags( WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON )
            }




    }

    fun onPauseRecord( pause : Boolean ) {


        if( pause ) {
            btnPause.text = "재생"

            timeWhenPaused = chronometer.base - SystemClock.elapsedRealtime()
            chronometer.stop()

        }else {

            btnPause.text = "일시정지"

            chronometer.base = (SystemClock.elapsedRealtime() + timeWhenPaused)
            chronometer.start()
        }

    }
    companion object {

        open val ARG_POSITION   = "position"

             val LOG_TAG        = "RecordFragment"
             var position       =  -1
        fun newInstance(position: Int): RecordFragment {
            val fragment = RecordFragment()
            val args = Bundle()

            args.putInt(ARG_POSITION, position )
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
