package com.akakim.legion.fragment

import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.akakim.legion.R
import com.akakim.legion.activity.CreateTodoItemActivity
import com.akakim.legion.adapter.list.TodoListAdapter
import com.akakim.legion.adapter.list.`interface`.OnSingleItemClickListener
import com.akakim.legion.data.TodoListItem
import kotlinx.android.synthetic.main.fragment_todo_list.*


class TodoListFragment : BaseFragment(),OnSingleItemClickListener {
    override fun onClick(position: Int) {

    }

    private var todoList = ArrayList<TodoListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            todoList = arguments.getParcelableArrayList( LIST_KEY )
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        return inflater?.inflate(R.layout.fragment_todo_list, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode ) {

            TODOLIST_FRAGMENT_REQUEST_ACTIVITY_CODE ->{
                val result = data?.extras

                if ( result == null ){

                    // TODO : 오류 보고로 올림
                    Toast.makeText(context,"Unexpedtec Error" ,Toast.LENGTH_SHORT).show()
                }else {


//                    val aNewItem : Bundle = result.getBundle( CreateTodoItemActivity.CREATE_BUNDLE_ITEM_KEY)
//
//                    aNewItem.getParcelable< TodoListItem >( Cr)

//                            result.getParcelable(  CreateTodoItemActivity.CREATED_ITEM_KEY )
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

        val adapter  = TodoListAdapter(context, todoList ,this )
        rvTodoList.adapter =  adapter
        rvTodoList.layoutManager = layoutManger

        fbAdd.setOnClickListener {
            val i = Intent( context, CreateTodoItemActivity::class.java )
            activity.startActivityForResult( i ,TODOLIST_FRAGMENT_REQUEST_ACTIVITY_CODE )
        }

    }


    companion object {

        val LIST_KEY = "listItem"

        val TODOLIST_FRAGMENT_REQUEST_ACTIVITY_CODE = 500
        fun newInstance( todoList : ArrayList<TodoListItem>): TodoListFragment {
            val fragment = TodoListFragment()
            val args = Bundle()

            args.putParcelableArrayList( LIST_KEY , todoList )
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
