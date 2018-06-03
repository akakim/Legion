package com.akakim.legion

import android.app.AlertDialog
import android.content.*
import android.database.DatabaseUtils
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import com.akakim.legion.activity.BaseActivity
import com.akakim.legion.activity.TimerActivity
import com.akakim.legion.common.Constant
import com.akakim.legion.common.FragmentConstant
import com.akakim.legion.data.DataInterface
import com.akakim.legion.data.RecordItem
import com.akakim.legion.fragment.*
import com.akakim.legion.fragment.record.FileViewerFragment
import com.akakim.legion.fragment.record.RecordFragment
import com.akakim.legion.fragment.todo.TodoListFragment
import com.akakim.legion.widget.FileNameDialog
import com.akakim.utillibrary.service.RecordingService
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener
                    ,BaseFragment.OnFragmentInteractionListener
                    ,FileNameDialog.OnFileInterface{


    var reciever : BroadcastReceiver?           = null
    lateinit var serviceFilter : IntentFilter
    override fun onFragmentInteraction(fragmentTag: String, uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {




            when(item.itemId){
                R.id.menuTodoList ->{
                    // TODO : 엑셀처럼 정리된 형식의 파일 출력을 염두해두기
                    // TODO : Widget에서 읽기 전용부터 만들기 염두해두기
                    // TODO : Widget에서 쓰기 까지만 만들기. 더이상은 안되.
                    // TODO : CheckList의 아이템은 여기에 한개씩 들어간다.


                    currentFragmentTAG =  FragmentConstant.TODO_LIST_FRAGMENT
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.flMain, TodoListFragment() as Fragment, TodoListFragment::class.java.simpleName)
                            .commit()

                }

                R.id.menuBreathRoutineList->{


                }


                R.id.menuFeedBackList->{



                }
                R.id.menuRecord -> {

                    val recordFragment = RecordFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.flMain, recordFragment , RecordFragment::class.java.simpleName)
                            .commit()

                }
                R.id.menuTimerChecker -> {


                    val i = Intent ( this , TimerActivity::class.java )

                    startActivity( i )

                }
                R.id.menuRecordList ->{

                    val dbHelper = DBHelper.getInstance( this )

                    val list = ArrayList<RecordItem> ()
                    if( dbHelper != null ){

                        list.addAll( dbHelper.getRecordItemList() )

                    }

                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.flMain,  FileViewerFragment.newInstance( list  ) , FileViewerFragment::class.java.simpleName)
                            .commit()
                }

                else -> {

                    Log.e("MainActivity","onItemSelected unexpected error occured")

                }
            }


//        navigationView.cl
//        navigationView.
        return true
    }


    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView.setNavigationItemSelectedListener( this )
//        navigationView.is
//        navigationView

        navigationView.menu.getItem(0).setChecked( true )

        onNavigationItemSelected( navigationView.menu.getItem(0) )


        serviceFilter   = IntentFilter(RecordService.ACTION_TEMP_FILE_READY )
        serviceFilter.addAction( RecordService.ACTION_RECORDING_ERROR )

        reciever        = object : BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {

                when(intent?.action){
                    RecordService.ACTION_TEMP_FILE_READY ->{


                        var recordItem  = intent.getParcelableExtra<RecordItem>( Constant.recordItemKey)


                        val fileNameDialog = FileNameDialog( this@MainActivity,false,recordItem,this@MainActivity )

                        fileNameDialog.window.setGravity(Gravity.CENTER)
                        fileNameDialog.show()
                    }
                    RecordService.ACTION_RECORDING_ERROR->{


                        val what = intent.getIntExtra("what",-1)
                        val extra = intent.getIntExtra("extra",-1)

                        Log.d("onReceive","what : " + what + " extra : " + extra )
                    }
                }

            }

        }




        ActivityCompat.requestPermissions( this ,permissionList, requestCode)


    }

    override fun onResume() {
        super.onResume()

        registerReceiver( reciever ,serviceFilter)
    }

    override fun onStop() {
        super.onStop()

        if ( reciever != null ) {
            unregisterReceiver(reciever)
        }
    }

    fun createTableUsingPrimaryKey(tableName: String, columnPair: Array< Pair<String,String> >) : String{

        val builder =StringBuilder()

        builder.append("CREATE TABLE ")
        builder.append(tableName)
        builder.append("(")

        builder.append( DataInterface._ID )
        builder.append( " " + DataInterface.INTEGER_TYPE)
        builder.append( " PRIMARY KEY," )

        for( aItem in columnPair ){
            builder.append(aItem.first+ " " + aItem.second+",")
        }

        val middle : CharSequence = builder.removeRange( builder.length-1 , builder.length)

        val lastBuilder = StringBuilder( middle )


        lastBuilder.append(")")


        return lastBuilder.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //TODO : TodoListFragment를 위한 onActivityResult 처리

        supportFragmentManager.findFragmentByTag( currentFragmentTAG).apply {
            this.onActivityResult( requestCode,resultCode,data )
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d(this.localClassName, " onOp;tionItemSelected");

        return super.onOptionsItemSelected(item)


    }

    /**
     *
     */
    // copy 에서 알아서 처리가 되어있으므로 신경쓰지않아도 된다.
    // 버퍼 사이즈는 . 8kb이다.
    override fun fileNameConfirmed( recordItem: RecordItem , targetName : String  ) {


        val resourceFile                        = File ( recordItem.recordFilePath,recordItem.recordFileName)


        try{

            if( targetName.equals( recordItem.recordFileName )){


                val resultRow = DBHelper.getInstance(this)?.addItem(RecordItem.TABLE_RECORD, recordItem as DataInterface)

                Log.d(javaClass.simpleName,"result Row " + resultRow  )

                Toast.makeText(this, "레코딩 성공 ", Toast.LENGTH_SHORT).show()



            }else {
                val file = File(recordItem.recordFilePath, targetName)


                // 중복 파일 처리

                if (!file.exists()) {
                    file.createNewFile()
                }


                if (resourceFile.exists()) {
                    resourceFile.copyTo(file, true, DEFAULT_BUFFER_SIZE)
                    recordItem.recordFileName = targetName


                    val resultRow = DBHelper.getInstance(this)?.addItem(RecordItem.TABLE_RECORD, recordItem as DataInterface)


                    Toast.makeText(this, "레코딩 성공 ", Toast.LENGTH_SHORT).show()


                    Log.d(javaClass.simpleName,"result Row " + resultRow  )

                    resourceFile.delete()
                } else {
                    throw FileNotFoundException("file is not exist")
                }
            }
//            if(resourceFile.exists()){
//                resourceFile.delete()
//            }


        }catch(e: IOException){
            e.printStackTrace()
            Toast.makeText(this,"파일 생성을 실패 했습니다. ", Toast.LENGTH_SHORT ).show()
        }catch (e: FileNotFoundException ){
            e.printStackTrace()
            Toast.makeText(this,"파일 생성을 실패 했습니다. ", Toast.LENGTH_SHORT ).show()
        }
        catch ( e: FileAlreadyExistsException){
            e.printStackTrace()
            Toast.makeText(this,"파일 생성을 실패 했습니다. ", Toast.LENGTH_SHORT ).show()
        } catch ( e : Exception){
            e.printStackTrace()
            Toast.makeText(this,"파일 생성을 실패 했습니다. ", Toast.LENGTH_SHORT ).show()
        }


    }

    override fun fileNameCanceled( recordItem: RecordItem ) {
        val resourceFile = File ( recordItem.recordFilePath,recordItem.recordFileName)

        if(resourceFile.exists()){
            resourceFile.delete()

        }


        Toast.makeText(this,"파일 생성이 취소되었습니다. ", Toast.LENGTH_SHORT ).show()

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode){
            requestCode ->{

                var isGranted = true

                for ( i in grantResults ){

                    if ( i == android.content.pm.PackageManager.PERMISSION_DENIED ){
                        isGranted = false
                    }
                }


                // TODO 권한에 맞게끔 다를 수있겠지만 일단 pass
                if( !isGranted ){

                    val builder = AlertDialog.Builder( this )

                    builder.setTitle( " 알림 " )
                    builder.setMessage( "파일 저장과 녹음이 되어야만 앱을 사용할 수 있습니다.")
                    builder.setPositiveButton( "확인" ,DialogInterface.OnClickListener { dialog, which ->

                        finish()
                    })
                }

            }
        }
    }
}
