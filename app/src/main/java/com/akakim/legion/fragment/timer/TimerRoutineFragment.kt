package com.akakim.legion.fragment.timer

import android.os.Bundle
import android.os.SystemClock
import android.support.v7.widget.LinearLayoutManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.akakim.legion.R
import com.akakim.legion.adapter.list.RoutineAdapter
import com.akakim.legion.data.BreatheRoutineCycleItem
import com.akakim.legion.fragment.BaseFragment
import kotlinx.android.synthetic.main.share_layout_timer.*


/**
 *
 *
 * TODO: 시간이 트리거링이 되어 리스트의 항목을 삭제하는 기능이 들어가야한다.
 */
class TimerRoutineFragment : BaseFragment() {


    var adapter             : RoutineAdapter? = null
    var cyclerItemRoutines  : ArrayList<BreatheRoutineCycleItem> = ArrayList()

    var isStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            cyclerItemRoutines = arguments.getParcelableArrayList( ARG_CYCLE_ITEMS )

        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter                     = RoutineAdapter( context, cyclerItemRoutines)
        rvCycleList.adapter         = adapter
        rvCycleList.layoutManager   = LinearLayoutManager( context )


        val iniValue : Long = cyclerItemRoutines.sumBy { it.term }.toLong()

        chronometer.apply {
            base = iniValue + SystemClock.elapsedRealtime()

            setOnChronometerTickListener {

            }
        }
//        chronometer.base = iniValue + SystemClock.elapsedRealtime()
//        chronometer.setOnChronometerTickListener {
//
//        }

//        chronometer.format = "H:MM:SS"



//        chronometer.apply {
//
//            setOnClickListener {
//
//                if( isStarted ){
//                    this.stop()
//                    isStarted= false
//                }else {
//                    this.start()
//                    isStarted = true
//                }
//
//            }
//
//
//            setOnChronometerTickListener {
//                chronometer ->
//
//
//
//            }
//        }
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
