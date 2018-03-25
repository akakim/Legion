package com.akakim.legion

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.MenuItem
import android.widget.Toast
import com.akakim.legion.activity.BaseActivity
import com.akakim.legion.common.FragmentConstant
import com.akakim.legion.data.TodoListItem
import com.akakim.legion.fragment.FileViewerFragment
import com.akakim.legion.fragment.RecordFragment
import com.akakim.legion.fragment.TimerFragment
import com.akakim.legion.fragment.TodoListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {



        val argBundle = Bundle()
        argBundle.let {
            when(item.itemId){
                R.id.menuTodoList ->{
                    // TODO : 엑셀처럼 정리된 형식의 파일 출력을 염두해두기
                    // TODO : Widget에서 읽기 전용부터 만들기 염두해두기
                    // TODO : Widget에서 쓰기 까지만 만들기. 더이상은 안되.

                    // input test Data
                    val todoList = ArrayList<TodoListItem>()

                    todoList.add( TodoListItem("pk1","복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem("pk1","복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem("pk1","복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem("pk1","복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem("pk1","복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem("pk1","복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem("pk1","복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem("pk1","복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))

                    val todoListFrag = TodoListFragment()
                    it.putParcelableArrayList( TodoListFragment.LIST_KEY, todoList )

                    todoListFrag.arguments = it

                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.flMain, todoListFrag, FragmentConstant.TODO_LIST_FRAGMENT)
                            .commit()
                }


                R.id.menuRecord -> {

                    val recordFragment = RecordFragment()
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.flMain, recordFragment , FragmentConstant.TODO_LIST_FRAGMENT)
                            .commit()

                }
                R.id.menuTimerChecker -> {
                    val timerFragment = TimerFragment()
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.flMain, timerFragment , FragmentConstant.TODO_LIST_FRAGMENT)
                            .commit()
                }
                R.id.menuRecordList ->{

                    val fileViewerFragment = FileViewerFragment()
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.flMain, fileViewerFragment , FragmentConstant.TODO_LIST_FRAGMENT)
                            .commit()
                }

                else -> {
                    Toast.makeText(this,"noting selected.. .",Toast.LENGTH_SHORT).show()
                }
            }
        }

        return true
    }


    /**
     *
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationView.setNavigationItemSelectedListener( this )

        navigationView.setCheckedItem( R.id.menuTodoList )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //TODO : TodoListFragment를 위한 onActivityResult 처리

//        fragmentManager.get
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
