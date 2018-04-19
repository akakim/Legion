package com.akakim.legion.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.text.format.DateUtils
import android.view.View
import android.widget.RadioGroup
import com.akakim.legion.R
import com.akakim.legion.common.Constant
import com.akakim.legion.data.TodoListItem
import kotlinx.android.synthetic.main.activity_create_todo_item.*

class CreateTodoItemActivity : BaseActivity() , View.OnClickListener ,RadioGroup.OnCheckedChangeListener{



    var practiceType = Constant.typeBest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo_item)

        btnConfirm.setOnClickListener (this )

        typeRadioGroup.setOnCheckedChangeListener( this )
    }


    override fun onClick(v: View?) {

        when( v?.id ){
            R.id.btnConfirm ->{
                val resultTime = DateUtils.formatElapsedTime(SystemClock.elapsedRealtime())

                val aNewItem = TodoListItem(
                        0,
                        edTitle.text.clearSpans().toString(),
                        edContent.text.toString(),
                        resultTime,
                        0
                )



                val bundle = Bundle()

                bundle.putParcelable(  CREATED_ITEM_KEY ,aNewItem)

                var  i = Intent()
                i.putExtra(CREATE_BUNDLE_ITEM_KEY,bundle)
                setResult( Activity.RESULT_OK  )

                finish()

            }
        }


    }


    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {

        when ( checkedId ){
            R.id.btnBest ->{
                practiceType = Constant.typeBest

            }
            R.id.btnPronunciation->{
                practiceType = Constant.typePronunciation

            }
            R.id.btnVoice ->{
                practiceType = Constant.typeVoice

            }
            R.id.btnBreath ->{
                practiceType = Constant.typeBreath
            }

        }
    }



    companion object {

       const val  CREATE_BUNDLE_ITEM_KEY = "bundleItem"
       const val  CREATED_ITEM_KEY       = "todo_item"
    }
}
