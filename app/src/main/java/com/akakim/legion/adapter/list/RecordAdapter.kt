package com.akakim.legion.adapter.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.akakim.legion.DBHelper
import com.akakim.legion.R
import com.akakim.legion.adapter.list.`interface`.OnSingleItemClickListener
import com.akakim.legion.adapter.list.viewholder.RecordViewHolder
import com.akakim.legion.common.Constant
import com.akakim.legion.data.OnEventListener
import com.akakim.legion.data.RecordFileObserver
import com.akakim.legion.data.RecordItem
import com.akakim.legion.util.Util
//import com.akakim.utillibrary.database.OnDatabaseChangedListener

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @DATE_COLUMN 2018-03-20
 */
class RecordAdapter : RecyclerView.Adapter<RecordViewHolder> , OnEventListener {


    override fun onEvent(event: Int, file: String?) {

    }

//    override fun onNewDatabaseEntryAdded() {
//
//        notifyItemInserted( itemCount - 1 )
//
//    }
//
//    override fun onDatabaseEntryRenamed() {
//
//    }


    val context             : Context
    val listener            : OnSingleItemClickListener?
    val dbHelper            : DBHelper?
    var selected            = -1





//    var observer : RecordFileObserver? = null

    var recordList =  ArrayList< RecordItem >()


    constructor(context : Context,recordList : ArrayList< RecordItem >,listener : OnSingleItemClickListener): super(){

        this.context    = context
        this.dbHelper   = DBHelper.getInstance( context )
        this.listener   = listener
        this.recordList = recordList        // call by reference로 임시로 작업. ->  call by value로 변경하고 옵저버 등록에 대한 조사 필요
//        observer = RecordFileObserver (context.getExternalFilesDir( Constant.defaultDirectory ).toString(), this )


    }


    override fun onBindViewHolder(holder: RecordViewHolder?, position: Int) {


        val item = this.recordList.get( position )
        holder?.apply {
            itemView.setOnClickListener{

                listener?.onClick( position )


            }

            tvRecordFileName?.text  = "제목 : " + Util.removeExtension( item.recordFileName)
            tvFileLength?.text      = " 파일길이 : " + item.recordLength.toString()
//            tvDate?.text            = item.recordDate


        }

        //TODO : Database 구축 이후의 처리 .
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from( context ).inflate( R.layout.item_recording,null,true )
        return RecordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recordList.size

    }



}