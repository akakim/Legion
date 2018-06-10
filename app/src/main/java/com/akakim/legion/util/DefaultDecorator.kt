package com.akakim.legion.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.akakim.legion.R

/**
 * Created by RyoRyeong Kim on 2018-04-17.
 */


class DefaultDecorator : RecyclerView.ItemDecoration {

    val context : Context
    var divider : Drawable


    constructor(context: Context):super(){
        this.context = context;

        divider = context.getDrawable( R.drawable.divider )

    }

    override fun onDrawOver(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)

        if (divider == null) return

        parent?.apply {
            if (getOrientation( this ) == LinearLayoutManager.VERTICAL) {

                val left        = parent.paddingLeft
                val right       = this.getWidth() - this.getPaddingRight()
                val childCount  = this.getChildCount()

                for (i in 1 until childCount) {

                    val child = parent.getChildAt(i)
                    val layoutParams = child.layoutParams as RecyclerView.LayoutParams
                    val size = divider.intrinsicHeight
                    val top = child.top - layoutParams.topMargin
                    val bottom = top + size

                    divider.setBounds(left, top, right, bottom)

                    divider.draw(c)
                }

            } else {

                val top = this.getPaddingTop()
                val bottom = this.getHeight() - this.getPaddingBottom()
                val childCount = this.getChildCount()

                for (i in 1 until childCount) {
                    val child = this.getChildAt(i)
                    val params = child.layoutParams as RecyclerView.LayoutParams
                    val size = divider.intrinsicWidth
                    val left = child.left - params.leftMargin
                    val right = left + size
                    divider.setBounds(left, top, right, bottom)
                    divider.draw(c)
                }
            }
        }

    }


    private fun getOrientation(view: RecyclerView): Int {
        if (view.layoutManager is LinearLayoutManager) {
            val layoutManager = view.layoutManager as LinearLayoutManager


            return layoutManager.orientation
        } else
            throw IllegalStateException("Divider Item Decoration can only be used with a LinearLaoutManager")
    }

    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)


    }

    override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)

//        Log.d("getItemOffset()","")

        if( parent != null ) {

            if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
                outRect?.top = Util.convertToDpToPixel(context,10)

            }
            else {
                outRect?.left = divider.intrinsicWidth
//                view?.background = divider
            }


        }

    }
}