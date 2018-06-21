package com.akakim.utillibrary.widget

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import java.util.*

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-22
 */


class SimpleSpinnerTypeSelector : Spinner, AdapterView.OnItemSelectedListener {

    open var initCode : String? = ""
    open var selectedCode : String? = ""

    open var codeList: Array<out String>? = null
    lateinit var innerAdapter : ArrayAdapter<String>


    override fun onNothingSelected(parent: AdapterView<*>?) {
        selectedCode = initCode
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if ( codeList != null ) {
            selectedCode = codeList?.get(position)
        }
    }




    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs )


    open fun setFixedData( initCode : String , codeList: Array<out String>? , valueList : Array<out String >? ){
        this.initCode = initCode
        selectedCode = initCode
        this.codeList = codeList

        innerAdapter = ArrayAdapter( context, android.R.layout.simple_spinner_item, valueList )
        innerAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item)
        adapter = innerAdapter

        onItemSelectedListener = this
    }
//    open fun setMutableData( initCode : String , codeList:MutableList<String>, valueList : MutableList<String>){
//
//        selectedCode = initCode
//
//
//        innerAdapter = ArrayAdapter( context, android.R.layout.simple_spinner_item, valueList )
//        innerAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item)
//        adapter = innerAdapter
//
//        onItemSelectedListener = this
//    }
//
//    open fun clearItem(){
//        innerAdapter.clear()
//    }
//
//    open fun addItem( item : String ) {
//        innerAdapter.add( item )
//    }
//
//    open fun addItems( list : Array<out String>? ){
//        innerAdapter.addAll( list?.asList() )
//        innerAdapter.notifyDataSetChanged()
//    }
//
//    open fun addItems( list : MutableList<String>){
//        innerAdapter.addAll( list )
//        innerAdapter.notifyDataSetChanged()
//    }


//    open fun removeItem(){
//        innerAdapter.
//        innerAdapter.notifyDataSetChanged()
//    }



}