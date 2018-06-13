package com.akakim.legion.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.TextView
import com.akakim.legion.R
import com.akakim.legion.adapter.list.RoutineAdapter
import com.akakim.legion.data.BreatheRoutineCycleItem
import com.akakim.legion.fragment.routine.TimerRoutineFragment
import com.akakim.legion.util.DefaultDecorator
import kotlinx.android.synthetic.main.shared_layout_routine_input.*


class CreateRoutineActivity : BaseActivity() , View.OnClickListener{


    lateinit  var routineAdapter    : RoutineAdapter
    var routineList       : ArrayList<BreatheRoutineCycleItem> = ArrayList<BreatheRoutineCycleItem>()


    @ColorInt
    var selectedColor     : Int = 0

    @ColorInt
    var inspirationColor  : Int = 0
    @ColorInt
    var expirationColor   : Int = 0
    @ColorInt
    var stopColor         : Int = 0
    @ColorInt
    var etcColor          : Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_routine)
        inspirationColor    = getColor( R.color.inspirationRoutine )
        expirationColor     = getColor( R.color.expirationRoutine )
        stopColor           = getColor( R.color.stopRoutine )
        etcColor            = getColor( R.color.etcRoutine )



        routineAdapter              = RoutineAdapter( this ,routineList )
        rvPreView.layoutManager     = LinearLayoutManager( this )
        rvPreView.addItemDecoration( DefaultDecorator( this ))
        rvPreView.adapter           = routineAdapter

//        routineAdapter


        btnCommit.setOnClickListener {

                // DB Input


//            if( routineList.size != 0 ) {
//                val bundle = Bundle()
//                bundle.putParcelableArrayList( TimerRoutineFragment.ARG_CYCLE_ITEMS, routineList )
//            }


        }


        radioGroup.setOnCheckedChangeListener { group, checkedId ->

            when(checkedId){
                R.id.btnExpiration  -> {
                    edRoutine.setText("날숨", TextView.BufferType.EDITABLE)
                    selectedColor = inspirationColor

                }
                R.id.btnInspiration -> {
                    edRoutine.setText("들숨", TextView.BufferType.EDITABLE)
                    selectedColor = expirationColor
                }
                R.id.btnStopBreathe -> {
                    edRoutine.setText("멈춤", TextView.BufferType.EDITABLE)
                    selectedColor  = stopColor
                }
                else -> {
                    edRoutine.setText("", TextView.BufferType.EDITABLE)
                    selectedColor = etcColor
                }
            }
        }

        numberPicker.minValue = 1
        numberPicker.maxValue = 60
        numberPicker.wrapSelectorWheel = false


    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
