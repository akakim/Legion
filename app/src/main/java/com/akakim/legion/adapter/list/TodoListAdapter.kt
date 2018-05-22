package com.akakim.legion.adapter.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.akakim.legion.DBHelper
import com.akakim.legion.R
import com.akakim.legion.adapter.list.`interface`.OnSingleItemClickListener
import com.akakim.legion.adapter.list.viewholder.TodoViewHolder
import com.akakim.legion.data.DataInterface
import com.akakim.legion.data.TodoListItem

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @DATE_COLUMN 2018-03-25
 */
class TodoListAdapter : RecyclerView.Adapter<TodoViewHolder> {

    val context : Context

    val dbHelper : DBHelper?

//    val multiSelectionList : SparseBooleanArray

    var listener : OnSingleItemClickListener? = null
    constructor(context : Context,listener : OnSingleItemClickListener?) : super(){

        this.context = context
        this.dbHelper = DBHelper.getInstance( context )
        this.listener = listener

    }
    override fun onBindViewHolder(holder: TodoViewHolder?, position: Int) {


//        dbHelper?.getItemAtTodoListItem( position ).apply {
//            holder?.tvTitle!!.text = todoTitle
//
//
//        }

        val todoListItem = dbHelper?.getItemAtTodoListItem( position )

        holder?.apply {
            tvTitle?.text = todoListItem?.todoTitle
            cbTodo?.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked) {
                    todoListItem?.checked = 1
                    dbHelper?.updateItem( TodoListItem.TABLE_TODO_LIST , todoListItem as? DataInterface)

                } else {
                    todoListItem?.checked = 0
                    dbHelper?.updateItem( TodoListItem.TABLE_TODO_LIST , todoListItem )

                }
            }
        }



    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TodoViewHolder {

        val v = TodoViewHolder(LayoutInflater.from(context).inflate( R.layout.item_todo, null,false ))
        return v
    }



    override fun getItemCount(): Int {

        val count : Int?  = dbHelper?.getCount( TodoListItem.TABLE_TODO_LIST )

        if (count == null ){
            return 0
        }

        return count
    }


}