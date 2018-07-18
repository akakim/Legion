package com.akakim.legion.widget

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.akakim.legion.R
import com.akakim.legion.data.RecordItem
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
    var recievedData        : RecordItem

    var onFileNameInterface : OnFileInterface?


    constructor(context: Context?,isAdInDialog :Boolean,
                recievedData: RecordItem,
                onFileNameInterface : OnFileInterface?)
            : super(context){

        this.isAdInDialog = isAdInDialog
        this.onFileNameInterface = onFileNameInterface

        this.recievedData = recievedData


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView ( R.layout.dialog_file_input)


        edFileName.setText( recievedData.recordFileName,TextView.BufferType.EDITABLE)

        btnConfirm.setOnClickListener{

            // "" 인경위 처리는 Cancel과 동일하게 .

            if( "".equals( edFileName.text.toString() )){
                onFileNameInterface?.fileNameCanceled(recievedData)

            }else {
                onFileNameInterface?.fileNameConfirmed( recievedData , edFileName.text.toString() )

            }
            dismiss()

        }

        btnCancel.setOnClickListener{
            onFileNameInterface?.fileNameCanceled(recievedData)
            dismiss()

        }
    }


    override fun show() {
        super.show()

        this.window.clearFlags( WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM)
        this.window.setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    interface OnFileInterface{

        fun fileNameConfirmed(recordItem: RecordItem , targetName : String)
        fun fileNameCanceled(recordItem : RecordItem)
    }
}


