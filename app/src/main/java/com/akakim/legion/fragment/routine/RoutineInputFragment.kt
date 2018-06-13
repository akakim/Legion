package com.akakim.legion.fragment.routine

import android.content.Context
import android.os.Bundle
import android.support.annotation.ColorInt
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.akakim.legion.R
import com.akakim.legion.adapter.list.RoutineAdapter
import com.akakim.legion.data.BreatheRoutineCycleItem
import com.akakim.legion.util.DefaultDecorator

import kotlinx.android.synthetic.main.shared_layout_routine_input.*


/**
 *
 * 사용자는 처음 수기로 입력을 해야만 한다.
 * TODO : 처음 입력한 수기를 기억하는 걸 만든다. command 처럼 기억했다가 편하게 입력하는 방법 .
 * 그 다음은 Timer 를 젠다.
 * TODO : 아예 입력값 없이 제기만 하는 Timer도 필요할 거같다.
 */
class RoutineInputFragment : Fragment() {

              var mListenerRoutine  : OnRoutineFragmentInteractionListener? = null

    lateinit  var routineAdapter    : RoutineAdapter
              var routineList       : ArrayList<BreatheRoutineCycleItem> = ArrayList<BreatheRoutineCycleItem>()


    @ColorInt var selectedColor     : Int = 0

    @ColorInt var inspirationColor  : Int = 0
    @ColorInt var expirationColor   : Int = 0
    @ColorInt var stopColor         : Int = 0
    @ColorInt var etcColor          : Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (arguments != null) {  }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        inspirationColor    = context.getColor( R.color.inspirationRoutine )
        expirationColor     = context.getColor( R.color.expirationRoutine )
        stopColor           = context.getColor( R.color.stopRoutine )
        etcColor            = context.getColor( R.color.etcRoutine )



        return inflater.inflate(R.layout.shared_layout_routine_input, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


            routineAdapter       = RoutineAdapter(context,routineList )
        rvPreView.layoutManager   = LinearLayoutManager( context)
        rvPreView.addItemDecoration( DefaultDecorator(context))
        rvPreView.adapter         = routineAdapter

//        routineAdapter


        btnCommit.setOnClickListener {


            if( routineList.size != 0 ) {
                val bundle = Bundle()
                bundle.putParcelableArrayList( TimerRoutineFragment.ARG_CYCLE_ITEMS, routineList )
                mListenerRoutine?.onRoutineFragmentInteraction("", bundle)
            }


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
                    edRoutine.setText("",TextView.BufferType.EDITABLE)
                    selectedColor = etcColor
                }
            }
        }

        numberPicker.minValue = 1
        numberPicker.maxValue = 60
        numberPicker.wrapSelectorWheel = false




        btnInput.setOnClickListener {

            // 필수값 체크

//            if( !"".equals( edRoutine.text.toString())){
//
//
//                val aItem = BreatheRoutineCycleItem(0,
//                                edRoutine.text.toString(),
//                                numberPicker.value,
//                                selectedColor)
//
//                this.routineList . add( aItem )
//
//                val itemSize : Int = routineAdapter.itemCount
//                routineAdapter.notifyDataSetChanged()
//            }

        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)


        if (context is OnRoutineFragmentInteractionListener) {
            mListenerRoutine = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnRoutineFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListenerRoutine = null
    }


    interface OnRoutineFragmentInteractionListener {
        fun onRoutineFragmentInteraction(tag: String, bundle : Bundle )
    }


    companion object {


        fun newInstance(param1: String, param2: String): RoutineInputFragment {
            val fragment = RoutineInputFragment()
            val args = Bundle()

            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
