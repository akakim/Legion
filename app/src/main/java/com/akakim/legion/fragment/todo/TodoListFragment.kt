package com.akakim.legion.fragment.todo

import android.os.Bundle
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.akakim.legion.DBHelper

import com.akakim.legion.R
import com.akakim.legion.activity.CreateTodoItemActivity
import com.akakim.legion.adapter.list.TodoListAdapter
import com.akakim.legion.adapter.list.`interface`.OnSingleItemClickListener
import com.akakim.legion.data.DataInterface
import com.akakim.legion.data.TodoListItem
import com.akakim.legion.fragment.BaseFragment
import com.akakim.legion.util.DefaultDecorator
import kotlinx.android.synthetic.main.fragment_todo_list.*


class TodoListFragment : BaseFragment(),OnSingleItemClickListener {

    var dbHelper: DBHelper? = null
    override fun onClick(position: Int) {

    }

    private var todoList = ArrayList<TodoListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            todoList = arguments.getParcelableArrayList(LIST_KEY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        dbHelper = DBHelper.getInstance( context )



        return inflater?.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode ) {

            TODOLIST_FRAGMENT_REQUEST_ACTIVITY_CODE ->{


                Log.d("onActiviytResult", data?.getParcelableExtra<TodoListItem>(CreateTodoItemActivity.CREATED_ITEM_KEY).toString())




                    val aItem = data?.getParcelableExtra<TodoListItem>( CreateTodoItemActivity.CREATED_ITEM_KEY)

                    Log.d( "onActivityResult " , aItem.toString())



                    if( aItem == null ){
                        Toast.makeText( context,"새로운 할일목록 생성 실패 ",Toast.LENGTH_SHORT ).show()
                    } else if( dbHelper?.addItem( TodoListItem.TABLE_TODO_LIST, aItem as DataInterface ) != -1L) {

                        rvTodoList.adapter.itemCount.apply {
                            rvTodoList.adapter.notifyItemInserted( this )
                        }
                    }else {
                        Toast.makeText( context,"새로운 할일목록 생성 실패 ",Toast.LENGTH_SHORT ).show()
                    }



            }
            else ->{

            }
        }
    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val layoutManger = LinearLayoutManager( context )

        layoutManger.orientation = LinearLayoutManager.VERTICAL

        val adapter  = TodoListAdapter(context, this )

        rvTodoList.layoutManager = layoutManger
        rvTodoList.addItemDecoration( DefaultDecorator(context))

        rvTodoList.adapter =  adapter
        fbAdd.setOnClickListener {
            val i = Intent( context, CreateTodoItemActivity::class.java )
            activity.startActivityForResult( i , TODOLIST_FRAGMENT_REQUEST_ACTIVITY_CODE)
        }


    }


    companion object {

        val LIST_KEY = "listItem"

        val TODOLIST_FRAGMENT_REQUEST_ACTIVITY_CODE = 500
        fun newInstance( todoList : ArrayList<TodoListItem>): TodoListFragment {
            val fragment = TodoListFragment()
            val args = Bundle()

            args.putParcelableArrayList(LIST_KEY, todoList )
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
