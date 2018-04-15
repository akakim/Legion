package com.akakim.legion.adapter.list.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.akakim.legion.R
import kotlinx.android.synthetic.main.item_breathe_routine.view.*

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-04-09
 */

class CycleViewHolder : RecyclerView.ViewHolder{

    val tvSequence          : TextView?
    val tvDoThat           : TextView?
    val tvCycleTerm         : TextView?

    constructor(itemView: View?): super(itemView){

        tvSequence      = itemView?.findViewById( R.id. tvSequence )
        tvDoThat        = itemView?.findViewById( R.id. tvDoThat )
        tvCycleTerm     = itemView?.findViewById( R.id. tvCycleTerm )



    }

}