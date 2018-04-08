package com.akakim.legion.adapter.list.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.akakim.legion.R
import kotlinx.android.synthetic.main.item_todo.view.*

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-25
 */

class TodoViewHolder : RecyclerView.ViewHolder {


    val tvTitle     : TextView?
    val cbTodo      : CheckBox?

    constructor( itemView : View? ) : super (itemView ){


            tvTitle         = itemView?.findViewById(R.id.tvTitle )
            cbTodo          = itemView?.findViewById(R.id.cbTodo )


    }
}