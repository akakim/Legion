package com.akakim.legion.widget

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import com.akakim.legion.R
import kotlinx.android.synthetic.main.dialog_file_input.*

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-05-22
 *
 * 어떤 화면이든 파일을 수정하든
 * 새로 생성하든 이름을 입력하는 다이얼로그
 *
 */


class FileNameDialog : AlertDialog{

    val isAdInDialog        : Boolean
    val initFileName        : String
    val initLength            : Long
    var onFileNameInterface : OnFileInterface?


    constructor(context: Context?,isAdInDialog :Boolean,
                initFileName: String ,
                initLength : Long,
                onFileNameInterface : OnFileInterface?)
            : this(context,true, null ,isAdInDialog, initFileName ,initLength,onFileNameInterface)


    constructor(context: Context?, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?,
                isAdInDialog :Boolean,initFileName: String,initLength : Long,onFileNameInterface : OnFileInterface?)
            : super(context, cancelable, cancelListener){

        this.isAdInDialog = isAdInDialog
        this.onFileNameInterface = onFileNameInterface
        this.initFileName = initFileName
        this.initLength = initLength




    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView ( R.layout.dialog_file_input)


        edFileName.setText( initFileName,TextView.BufferType.EDITABLE)


        btnConfirm.setOnClickListener{

            // "" 인경위 처리는 Cancel과 동일하게 .

            if( "".equals( edFileName.text.toString() )){
                onFileNameInterface?.fileNameCanceled()
            }else {
                onFileNameInterface?.fileNameConfirmed(edFileName.text.toString() )
            }
            dismiss()

        }

        btnCancel.setOnClickListener{
            onFileNameInterface?.fileNameCanceled()
            dismiss()
        }
    }

    open interface OnFileInterface{
        fun fileNameConfirmed(fileName : String , length : Long )
        fun fileNameCanceled()
    }
}