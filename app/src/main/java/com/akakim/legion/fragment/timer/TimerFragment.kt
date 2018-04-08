package com.akakim.legion.fragment.timer

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.akakim.legion.R
import com.akakim.legion.adapter.list.CycleAdapter
import com.akakim.legion.data.BreatheCycleItem
import com.akakim.legion.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_timer.*


/**
 *
 *
 * TODO: 시간이 트리거링이 되어 리스트의 항목을 삭제하는 기능이 들어가야한다.
 */
class TimerFragment : BaseFragment() {


    var adapter     : CycleAdapter?
    var cyclerItems : ArrayList<BreatheCycleItem>

    init {
        adapter     = null
        cyclerItems = ArrayList<BreatheCycleItem>()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            cyclerItems = arguments.getParcelableArrayList( ARG_CYCLE_ITEMS )

        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater?.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter                     = CycleAdapter( context, cyclerItems )
        rvCycleList.adapter         = adapter
        rvCycleList.layoutManager   = LinearLayoutManager( context )
    }

    companion object {

        private val ARG_INIT_TIMER      = "initTimer"
        private val ARG_CYCLE_ITEMS     = "cycleItems"

        fun newInstance(initTimer: String, cycleItems: ArrayList<BreatheCycleItem>): TimerFragment {
            val fragment = TimerFragment()
            val args = Bundle()
            args.putString(ARG_INIT_TIMER, initTimer)
            args.putParcelableArrayList(ARG_CYCLE_ITEMS, cycleItems)

            fragment.arguments = args
            return fragment
        }
    }
}
