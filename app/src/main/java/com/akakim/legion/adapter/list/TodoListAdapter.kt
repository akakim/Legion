package com.akakim.legion.adapter.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.akakim.legion.R
import com.akakim.legion.adapter.list.`interface`.OnSingleItemClickListener
import com.akakim.legion.adapter.list.viewholder.TodoViewHolder
import com.akakim.legion.data.TodoListItem

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-03-25
 */
class TodoListAdapter : RecyclerView.Adapter<TodoViewHolder> {

    val context : Context
    val todoArrayList : ArrayList<TodoListItem>

//    val multiSelectionList : SparseBooleanArray

    var listener : OnSingleItemClickListener? = null
    constructor(context : Context,todoArrayList : ArrayList<TodoListItem> ,listener : OnSingleItemClickListener?) : super(){

        this.context = context
        this.todoArrayList = todoArrayList
        this. listener = listener
//        this.multiSelectionList = SparseBooleanArray( this.todoArrayList.size )
    }
    override fun onBindViewHolder(holder: TodoViewHolder?, position: Int) {


        val todoListItem = todoArrayList.get( position )

        holder?.tvTitle?.text = todoListItem.todoTitle


        holder?.cbTodo?.apply {
            if ( todoListItem.checked == 1 ){
                check( true )
            }else {
                check(false )
            }

            setOnCheckedChangeListener { buttonView, isChecked ->


                if (isChecked) {
                    todoListItem.checked = 1
                } else {
                    todoListItem.checked = 0
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TodoViewHolder {

        val v = TodoViewHolder (LayoutInflater.from(context).inflate( R.layout.item_todo, null,false ))
        return v
    }

    override fun getItemCount(): Int {

        return todoArrayList.size
    }


}