package com.akakim.legion.adapter.list.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.akakim.legion.R
import kotlinx.android.synthetic.main.item_recording.view.*

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-20
 */

class RecordViewHolder : RecyclerView.ViewHolder {

    val tvRecordFileName        : TextView?
    val tvFileLength            : TextView?
//    val tvDate                  : TextView?

    constructor(itemView: View?) : super(itemView){


        tvRecordFileName    = itemView?.findViewById( R.id.tvRecordFileName )
        tvFileLength        = itemView?.findViewById( R.id.tvFileLength )
//        tvDate              = itemView?.findViewById( R.id.tvDate )

    }
}