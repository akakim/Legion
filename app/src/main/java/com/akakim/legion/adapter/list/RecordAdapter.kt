package com.akakim.legion.adapter.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.akakim.legion.R
import com.akakim.legion.adapter.list.`interface`.OnSingleItemClickListener
import com.akakim.legion.adapter.list.viewholder.RecordViewHolder
import com.akakim.legion.data.RecordItem
import com.akakim.utillibrary.database.OnDatabaseChangedListener

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-20
 */
class RecordAdapter : RecyclerView.Adapter<RecordViewHolder> ,OnDatabaseChangedListener{
    override fun onNewDatabaseEntryAdded() {

        notifyItemInserted( itemCount - 1 )
        //TODO  scroll using LinearLayoutManger
    }

    override fun onDatabaseEntryRenamed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    val context : Context
    val recordList : ArrayList<RecordItem>

    val listener : OnSingleItemClickListener
    var selected = -1
    constructor(context : Context,recordList : ArrayList<RecordItem>,listener : OnSingleItemClickListener): super(){

        this.context = context
        this.recordList = recordList
        this.listener = listener
    }
    override fun onBindViewHolder(holder: RecordViewHolder?, position: Int) {


        val item = recordList.get(position)
        holder?.apply {
            itemView.setOnClickListener{
                val preSelcted = selected

                if( selected == -1 ){
                    selected = position
                    notifyItemChanged( selected )
                }else {
                    selected = position
                    notifyItemChanged( preSelcted )
                    notifyItemChanged( selected )

                }

            }

            tvRecordFileName?.text = item.recordFileName
            tvFileLength?.text = item.recordFileName
            tvDate?.text = item.recordDate
        }

        //TODO : Database 구축 이후의 처리 .
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecordViewHolder {

        val view = LayoutInflater.from( context ).inflate( R.layout.item_recording,null,true )

        return RecordViewHolder(view)
    }

    override fun getItemCount(): Int {

        return this.recordList.size
    }

}