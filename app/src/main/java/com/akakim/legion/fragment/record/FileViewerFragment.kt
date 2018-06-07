package com.akakim.legion.fragment.record

import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.os.FileObserver
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.akakim.legion.R
import com.akakim.legion.adapter.list.RecordAdapter
import com.akakim.legion.adapter.list.`interface`.OnSingleItemClickListener
import com.akakim.legion.data.RecordItem

import com.akakim.legion.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_file_viewr.*


class FileViewerFragment : BaseFragment(), OnSingleItemClickListener {



    // TODO 개선방향 : observer가 필요하다.......

//    private var paramPos : Int = -1

    val fileList  = ArrayList<RecordItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {

            fileList.addAll(  arguments.getParcelableArrayList<RecordItem>(ARG_RECORD_LIST ) )
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view  = inflater!!.inflate(R.layout.fragment_file_viewr, container, false)




        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        rvRecordList.adapter = RecordAdapter( context, fileList , this )


        val layoutManager = LinearLayoutManager(context)

        // 새로운
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true


        rvRecordList.layoutManager = layoutManager

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }

    open fun notifyNewItemInserted(recordItem : RecordItem ){


        this.fileList.add(0,recordItem)
        rvRecordList.adapter.notifyItemInserted( 0 )

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)


    }



    override fun onClick(position: Int) {


        context.startActivity( Intent ())

    }


    companion object {


        val ARG_RECORD_LIST : String = "recordList"

        fun newInstance(recordList: ArrayList<RecordItem>): FileViewerFragment {
            val fragment = FileViewerFragment()
            val args = Bundle()

            args.putParcelableArrayList(ARG_RECORD_LIST, recordList)
            fragment.arguments = args
            return fragment
        }
    }
}