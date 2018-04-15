package com.akakim.legion.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.akakim.legion.R
import com.akakim.legion.data.TodoListItem
import kotlinx.android.synthetic.main.activity_create_todo_item.*

class CreateTodoItemActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo_item)

        btnConfirm.setOnClickListener {




            val aNewItem = TodoListItem(
                    "pk",
                    edTitle.text.clearSpans().toString(),
                    "content",
                    "2018-01-01",
                    0
            )



            val bundle = Bundle()

            bundle.putParcelable(  CREATED_ITEM_KEY ,aNewItem)

            var  i = Intent()
            i.putExtra(CREATE_BUNDLE_ITEM_KEY,bundle)
//            var i = Intent()
//            i.extras = bundle
            setResult( Activity.RESULT_OK  )

        }
    }






    companion object {

        val  CREATE_BUNDLE_ITEM_KEY = "bundleItem"
        val  CREATED_ITEM_KEY       = "todo_item"
    }
}
