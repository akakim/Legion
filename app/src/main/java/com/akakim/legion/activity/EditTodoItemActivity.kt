package com.akakim.legion.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.text.format.DateUtils
import com.akakim.legion.R
import com.akakim.legion.data.TodoListItem
import kotlinx.android.synthetic.main.activity_create_todo_item.*

/**
 * Created by RyoRyeong Kim on 2018-04-17.
 */

class EditTodoItemActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo_item)


        btnConfirm.setOnClickListener {



            val resultTime = DateUtils.formatElapsedTime(SystemClock.elapsedRealtime())

            val aNewItem = TodoListItem(
                    "pk",
                    edTitle.text.clearSpans().toString(),
                    edContent.text.toString(),
                    resultTime,
                    0
            )



            val bundle = Bundle()

            bundle.putParcelable(CreateTodoItemActivity.CREATED_ITEM_KEY,aNewItem)

            var  i = Intent()
            i.putExtra(CreateTodoItemActivity.CREATE_BUNDLE_ITEM_KEY,bundle)
            setResult( Activity.RESULT_OK  )

            finish()

        }
    }
}