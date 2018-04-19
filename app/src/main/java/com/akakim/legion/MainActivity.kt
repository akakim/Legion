package com.akakim.legion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import com.akakim.legion.activity.BaseActivity
import com.akakim.legion.activity.TimerActivity
import com.akakim.legion.common.FragmentConstant
import com.akakim.legion.data.CheckList
import com.akakim.legion.data.DataInterface
import com.akakim.legion.data.TodoListItem
import com.akakim.legion.fragment.*
import com.akakim.legion.fragment.record.FileViewerFragment
import kotlinx.android.synthetic.main.activity_main.*





class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener
                    ,BaseFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(fragmentTag: String, uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {


        Log.d(this.localClassName, " onNavigation Item Selected")
        val argBundle = Bundle()
        argBundle.let {
            when(item.itemId){
                R.id.menuTodoList ->{
                    // TODO : 엑셀처럼 정리된 형식의 파일 출력을 염두해두기
                    // TODO : Widget에서 읽기 전용부터 만들기 염두해두기
                    // TODO : Widget에서 쓰기 까지만 만들기. 더이상은 안되.
                    // TODO : CheckList의 아이템은 여기에 한개씩 들어간다.


                    // input test Data
                    val todoList = ArrayList<TodoListItem>()

                    todoList.add( TodoListItem(1,"복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem(2,"복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem(3,"복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem(4,"복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem(5,"복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem(6,"복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem(7,"복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))
                    todoList.add( TodoListItem(8,"복식 호흡","3초에 한번 4초에 한번 등등 ","2018-03-25",0))

                    val todoListFrag = TodoListFragment()
                    it.putParcelableArrayList( TodoListFragment.LIST_KEY, todoList )

                    todoListFrag.arguments = it

                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.flMain, todoListFrag as Fragment, FragmentConstant.TODO_LIST_FRAGMENT)
                            .commit()
                }


                R.id.menuRecord -> {

//                    val recordFragment = RecordFragment()
//                    supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.flMain, recordFragment , FragmentConstant.TODO_LIST_FRAGMENT)
//                            .commit()

                }
                R.id.menuTimerChecker -> {
//                    val timerFragment = TimerRoutineFragment()
//                    supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.flMain, timerFragment , FragmentConstant.TODO_LIST_FRAGMENT)
//                            .commit()
                    val i = Intent ( this , TimerActivity::class.java )

                    startActivity( i )

                }
                R.id.menuRecordList ->{

                    val fileViewerFragment = FileViewerFragment()
                    supportFragmentManager
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


        navigationView.menu.getItem(0).setChecked( true )

        onNavigationItemSelected( navigationView.menu.getItem(0) )



        val checkList =  CheckList(1,"숨쉬기",10)

    }


    fun createTableUsingPrimaryKey(tableName: String,columnPair: Array< Pair<String,String> >) : String{

        val builder =StringBuilder()

        builder.append("CREATE TABLE ")
        builder.append(tableName)
        builder.append("(")

        builder.append( DataInterface._ID )
        builder.append( " " + DataInterface.INTEGER_TYPE)
        builder.append( " PRIMARY KEY," )

        for( aItem in columnPair ){
            builder.append(aItem.first+ " " + aItem.second+",")
        }

        val middle : CharSequence = builder.removeRange( builder.length-1 , builder.length)

        val lastBuilder = StringBuilder( middle )


        lastBuilder.append(")")


        return lastBuilder.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //TODO : TodoListFragment를 위한 onActivityResult 처리

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        Log.d(this.localClassName, " onOp;tionItemSelected");

        return super.onOptionsItemSelected(item)


    }
}
