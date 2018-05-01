import android.app.Notification
import android.app.Service
import android.content.Intent
import android.media.MediaRecorder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import com.akakim.legion.DBHelper
import com.akakim.legion.common.OnTimerChangedListener
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-05-01
 */


class RecordService : Service(){

    val TAG                     = "RecordService"


    var fileName    : String?   = null
    var filePath    : String?   = null

    var startTimeMillis : Long = 0L
    var elapseMillis    : Long = 0L
    var elapsedSeconds  : Int = 0

    var recorder            : MediaRecorder? = null
    var db                  : DBHelper? = null
    val timerFormat         : SimpleDateFormat = SimpleDateFormat( "mm:ss", Locale.getDefault() )

    var timer               : Timer? = null
    var incrementTimerTask  : TimerTask? = null

    // interface
    var onTimerChangeListener : OnTimerChangedListener? = null

    val channelid = packageName + ".record_service"

    override fun onCreate() {
        super.onCreate()

        db = DBHelper(applicationContext)
    }


    override fun onBind(intent: Intent?): IBinder? {

        return null
    }



    fun startTimer(){

        timer= Timer()
        incrementTimerTask = object : TimerTask(){
            override fun run(){
                elapseMillis++
                onTimerChangeListener?.onTimerChanged( elapsedSeconds  )

            }
        }
    }


    fun setFileNameAndPath(name : String ) : Boolean {


        // 중복 검사 .
//        val f = File( baseContext.dataDir )

        return true;
    }


    fun startRecording(){
        setFileNameAndPath("someName")


        recorder = MediaRecorder()
        recorder?.let {
            it.setAudioSource( MediaRecorder.AudioSource.MIC)
            it.setOutputFormat( MediaRecorder.OutputFormat.MPEG_4)

        }



    }
    fun createNotification() : Notification{

        val builder = NotificationCompat.Builder( applicationContext , channelid )
//                .setSmallIcon( )



    }


}