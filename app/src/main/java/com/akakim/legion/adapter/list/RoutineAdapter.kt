package com.akakim.legion.adapter.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.akakim.legion.R
import com.akakim.legion.adapter.list.viewholder.CycleViewHolder
import com.akakim.legion.data.BreatheRoutineCycleItem

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-04-09
 */

class RoutineAdapter : RecyclerView.Adapter<CycleViewHolder> {

    var context             : Context?
    var routineCycleItems: ArrayList<BreatheRoutineCycleItem>


    constructor(context : Context, routineCycleItems: ArrayList<BreatheRoutineCycleItem>){

        this.context            = context
        this.routineCycleItems  = routineCycleItems
    }


    override fun onBindViewHolder(holder: CycleViewHolder, position: Int) {

        val aItem  = routineCycleItems.get( position )

        holder.apply {
            tvSequence?.text     = position.toString()
            tvDoThat?.text       = aItem.doThat
            tvCycleTerm?.text    = aItem.term.toString() + " 초 "           // TODO : 포맷 값을 생각하기.


        }


    }

    override fun getItemCount(): Int {
        return routineCycleItems.size
    }

    open fun getTotalCycleTerm() :Int{

        val sum = routineCycleItems.sumBy { it.term }

        return sum
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CycleViewHolder {

        val view = LayoutInflater.from( context ).inflate(R.layout.item_breathe_routine,parent,false)

        return CycleViewHolder( view )
    }

}