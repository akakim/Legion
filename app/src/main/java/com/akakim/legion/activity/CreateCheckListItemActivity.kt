package com.akakim.legion.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.akakim.legion.R

class CreateCheckListItemActivity : BaseActivity() , View.OnClickListener {


    /*
    *
    * var groupName : String,
        var sequence : Int,
        var viewType :Int,
        var content : String,
        var  score : Int

        */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView( R.layout.activity_create_check_list_item)
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
