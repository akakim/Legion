package com.akakim.legion.adapter.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.akakim.legion.R
import com.akakim.legion.adapter.list.viewholder.CycleViewHolder
import com.akakim.legion.data.BreatheCycleItem

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-04-09
 */

class CycleAdapter : RecyclerView.Adapter<CycleViewHolder> {

    var context             : Context?
    var cycleItems          : ArrayList<BreatheCycleItem>


    constructor(context : Context , cycleItems: ArrayList<BreatheCycleItem>){

        this.context        = context
        this.cycleItems     = cycleItems
    }


    override fun onBindViewHolder(holder: CycleViewHolder?, position: Int) {

        val aItem  = cycleItems.get( position )

        holder?.apply {
            tvSequence?.text     = position.toString()
            tvDoThat?.text       = aItem.doThat
            tvCycleTerm?.text    = aItem.term.toString()           // TODO : 포맷 값을 생각하기.


        }


    }

    override fun getItemCount(): Int {
        return cycleItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CycleViewHolder {

        val view = LayoutInflater.from( context ).inflate(R.layout.item_breathe_cycle,parent,false)

        return CycleViewHolder( view )
    }

}