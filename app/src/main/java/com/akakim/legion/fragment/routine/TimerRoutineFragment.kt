package com.akakim.legion.fragment.routine

import android.content.DialogInterface
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer

import com.akakim.legion.R
import com.akakim.legion.adapter.list.RoutineAdapter
import com.akakim.legion.data.BreatheRoutineCycleItem
import com.akakim.legion.fragment.BaseFragment

import kotlinx.android.synthetic.main.share_layout_timer.*
import java.util.*


/**
 *
 *
 * TODO: 시간이 트리거링이 되어 리스트의 항목을 삭제하는 기능이 들어가야한다.
 */
class TimerRoutineFragment : BaseFragment()
        ,DialogInterface.OnClickListener
        ,View.OnClickListener
        ,Chronometer.OnChronometerTickListener {



    lateinit var builder  : AlertDialog.Builder
    var initValue: Long = 0L


    var dialog : AlertDialog? = null
    var adapter             : RoutineAdapter? = null
    var cyclerItemRoutines  : ArrayList<BreatheRoutineCycleItem> = ArrayList()

    var isStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            cyclerItemRoutines = arguments!!.getParcelableArrayList( ARG_CYCLE_ITEMS )

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        builder = AlertDialog.Builder(requireContext(), 0)


        builder.apply {
            this.setTitle("알림").setMessage("루틴을 시작하시겠습니까?")
            builder.setPositiveButton( "확인", this@TimerRoutineFragment)
            dialog = builder.create()

        }

        val view =  inflater.inflate(R.layout.fragment_timer, container, false)

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter                     = RoutineAdapter( requireContext(), cyclerItemRoutines)
        rvCycleList.adapter         = adapter
        rvCycleList.layoutManager   = LinearLayoutManager( context )




        initValue = cyclerItemRoutines.sumBy { it.term }.toLong() * 1000
        Log.d("initValue" , initValue.toString())




        chronometer.apply{

            isCountDown = true
//            format  =   "MM:SS"

            base = SystemClock.elapsedRealtime() + initValue
//            text= initValue.toString()
            setOnClickListener( this@TimerRoutineFragment  )
            setOnChronometerTickListener ( this@TimerRoutineFragment )

        }


        btnInitialize.setOnClickListener( this@TimerRoutineFragment)
//        chronometer.text=  cyclerItemRoutines.sumBy { it.term }.toString()



//        chronometer.apply {
//            setStartTime(initValue)
//
//            setOnClickListener {
//                dialog?.show()
//            }
//        }


    }

    override fun onClick(v: View?) {

        when(v?.id){
            R.id.chronometer ->{

                if (isStarted ){
                    chronometer.stop()


                }else {
//                    chronometer.base = SystemClock.elapsedRealtime() + initValue
                    chronometer.start()
                }
                isStarted= !isStarted
            }

            R.id.btnInitialize ->{
                // 초기화 .

                chronometer.stop()

                chronometer.base = SystemClock.elapsedRealtime() +  initValue

                isStarted = false
            }
        }
    }


    override fun onClick(dialog: DialogInterface?, which: Int) {

        when (which){
            DialogInterface.BUTTON_POSITIVE -> {
                Log.d(javaClass.simpleName,"ButtonPositive ")
//                chronometer.text=  cyclerItemRoutines.sumBy { it.term }.toString()
                chronometer.start()

            }

            else -> {
                Log.d(javaClass.simpleName,"else  ")
            }
        }
    }

    override fun onChronometerTick(chronometer: Chronometer?) {



        var onTickValue  = chronometer?.base!! - SystemClock.currentThreadTimeMillis()
        Log.d(this@TimerRoutineFragment.javaClass.simpleName,"onTick() : "   +  onTickValue )


        if( cyclerItemRoutines.get(0).term >= onTickValue/1000 ) {
            adapter?.notifyItemRemoved(0)
        }


    }


    companion object {


        open val ARG_INIT_TIMER      = "initTimer"
        open val ARG_CYCLE_ITEMS     = "routineCycleItems"

        fun newInstance(initTimer: String, routineCycleItems: ArrayList<BreatheRoutineCycleItem>): TimerRoutineFragment {
            val fragment = TimerRoutineFragment()
            val args = Bundle()
            args.putString(ARG_INIT_TIMER, initTimer)
            args.putParcelableArrayList(ARG_CYCLE_ITEMS, routineCycleItems)

            fragment.arguments = args
            return fragment
        }
    }
}
