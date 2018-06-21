package com.akakim.utillibrary.widget

import android.content.Context
import android.database.DataSetObserver
import android.widget.ArrayAdapter

/**
 * @author KIM
 * @version 0.0.1
 * @since 0.0.1
 * @date 2018-06-22
 */


open class SimpleAdapterTypeSelector : ArrayAdapter<String>{



    constructor(context: Context?, resource: Int, objects: Array<out String>?) : super(context, resource, objects)

    constructor(context: Context?, resource: Int, objects: MutableList<String>?) : super(context, resource, objects)


    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        super.unregisterDataSetObserver(observer)
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        super.registerDataSetObserver(observer)
    }
}