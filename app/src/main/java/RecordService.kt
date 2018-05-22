import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaRecorder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.Toast
import com.akakim.legion.DBHelper
import com.akakim.legion.MainActivity
import com.akakim.legion.R
import com.akakim.legion.common.OnTimerChangedListener
import com.akakim.legion.data.DataInterface
import com.akakim.legion.data.RecordItem
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-05-01
 *
 * 서비스에서 어떻게 인자값을 전달하는가..........
 */


class RecordService : Service(){

    val TAG                     = "RecordService"


    var fileName            : String?   = null
    var filePath            : String?   = null

    var startTimeMillis     : Long = 0L
    var elapseMillis        : Long = 0L
    var elapsedSeconds      : Int = 0

    var recorder            : MediaRecorder? = null
    var db                  : DBHelper? = null
    val timerFormat         : SimpleDateFormat = SimpleDateFormat( "mm:ss", Locale.getDefault() )

    var timer               : Timer? = null
    var incrementTimerTask  : TimerTask? = null

    // interface
    var onTimerChangeListener : OnTimerChangedListener? = null

    val channelid = packageName + ".record_service"


    val REQUEST_ALARM_CODE = 1001
    override fun onCreate() {
        super.onCreate()

        db = DBHelper.getInstance(applicationContext)
    }


    override fun onBind(intent: Intent?): IBinder? {

        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        startRecording()
        return START_STICKY


    }

    override fun onDestroy() {

        stopRecording()
        super.onDestroy()


    }


    fun stopRecording(){
        recorder?.stop()

        elapseMillis = System.currentTimeMillis() - startTimeMillis
        recorder?.release()


        incrementTimerTask?.cancel()
        incrementTimerTask = null
        recorder = null
        try{

            val item = RecordItem(-1,fileName.toString(),elapseMillis,
                    System.currentTimeMillis() ,filePath.toString())

            this.db?.addItem( RecordItem.TABLE_RECORD , item as DataInterface )


            Toast.makeText(this,"레코딩 성공 ",Toast.LENGTH_SHORT).show()
        }catch ( e : Exception){
            e.printStackTrace()
        }


//        try{
//            db?.addItem(RecordItem.TABLE_RECORD, )
//        }catch ( e : Exception ){
//            e.printStackTrace()
//        }

    }


    /**
     * TODO : 파일 path 정하기 .
     */
    fun setFileNameAndPath(name : String ) : Boolean {

        val builder : StringBuilder = StringBuilder( name )
        builder.append(".mp4")
//         = File ( baseContext.filesDir, builder.toString())

        Log.d( "absoulePath", baseContext.dataDir.absolutePath)

        fileName = name
        filePath = baseContext.dataDir.absolutePath + builder.toString()


        return true
    }


    fun startRecording(){



        val sdf = SimpleDateFormat("yyyy/M/dd hh:mm:ss")
        val currentDate = sdf.format( Date())



        setFileNameAndPath(currentDate + " someName")


        recorder = MediaRecorder()
        recorder?.let {
            it.setAudioSource( MediaRecorder.AudioSource.MIC)
            it.setOutputFormat( MediaRecorder.OutputFormat.MPEG_4)
            it.setOutputFile( filePath )
            it.setAudioEncoder( MediaRecorder.AudioEncoder.AAC )
            it.setAudioChannels( 1 )


            // 오디오샘플링 퀄리티 조건 ..

            it.setAudioSamplingRate( 44100 )
            it.setAudioEncodingBitRate( 192000 )


        }

        try{
            recorder?.prepare()
            recorder?.start()


            // 시간 기록
            startTimeMillis = System.currentTimeMillis()
        }catch ( e : IOException){
            e.printStackTrace()
        }



    }

    fun startTimer(){

        timer = Timer()

        incrementTimerTask = object:TimerTask(){
            override fun run() {
                elapsedSeconds++

                onTimerChangeListener?.onTimerChanged( elapsedSeconds )

                val mgr : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                mgr.notify(     REQUEST_ALARM_CODE,createNotification())

                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }

        timer?.scheduleAtFixedRate( incrementTimerTask,1000,1000)
    }

    fun stopTimer(){
        timer?.cancel()
    }


    fun createNotification() : Notification{

        val builder = NotificationCompat.Builder( applicationContext , channelid )
                .setSmallIcon( R.drawable.ic_mic_white_36dp)
                .setContentTitle( getString(R.string.notification_recording))
                .setContentText(timerFormat.format( elapsedSeconds * 1000))
                .setOngoing( true )


        builder.setContentIntent( PendingIntent.getActivity(

                applicationContext,REQUEST_ALARM_CODE,
                 Intent( applicationContext,MainActivity::class.java ),
                        0
        ))

        return builder.build()
    }


}