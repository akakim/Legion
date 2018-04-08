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
import com.akakim.legion.common.Constant
import com.akakim.legion.data.OnEventListener
import com.akakim.legion.data.RecordFileObserver
import com.akakim.legion.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_file_viewr.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FileViewrFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FileViewrFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FileViewerFragment : BaseFragment(),OnEventListener{



    private var paramPos : Int = -1

    var observer : RecordFileObserver? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            paramPos = arguments.getInt(ARG_POSITION)
//            mParam2 = arguments.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view  = inflater!!.inflate(R.layout.fragment_file_viewr, container, false)




        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        rvRecordList.adapter


        val layoutManager = LinearLayoutManager(context)

        // 새로운
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true


        rvRecordList.layoutManager = layoutManager

    }



    override fun onAttach(context: Context) {
        super.onAttach(context)

        observer = RecordFileObserver (context.getExternalFilesDir( Constant.defaultDirectory ).toString(), this )

    }


    override fun onEvent(event: Int, file: String?) {

        when (event){

            FileObserver.DELETE ->{

            }


        }
    }

    companion object {


        val ARG_POSITION : String = "position"

        fun newInstance(pos: Int): FileViewerFragment {
            val fragment = FileViewerFragment()
            val args = Bundle()

            args.putInt(ARG_POSITION, pos)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
