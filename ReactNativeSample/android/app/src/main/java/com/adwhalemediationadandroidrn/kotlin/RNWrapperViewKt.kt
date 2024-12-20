package com.adwhalemediationadandroidrn.kotlin

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import kotlin.math.max

class RNWrapperViewKt(context : Context?) : FrameLayout(context!!) {

    override fun requestLayout() {
        super.requestLayout()
        post(measureAndLayout)
    }

    private final var measureAndLayout = Runnable {
        measure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY))
        layout(left, top, right, bottom)
    }

    override fun onMeasure(widthMeasureSpec : Int, heightMeasureSpec : Int) {
        var maxWidth = 0
        var maxHeight = 0

        for(i in 0 until childCount) {
            val child : View = getChildAt(i)
            if(child.visibility != GONE) {
                child.measure(widthMeasureSpec, MeasureSpec.UNSPECIFIED)

                maxWidth = max(maxWidth, child.measuredWidth)
                maxHeight = max(maxHeight, child.measuredHeight)
            }
        }

        val finalWidth = max(maxWidth, suggestedMinimumWidth)
        val finalHeight = max(maxHeight, suggestedMinimumHeight)

        setMeasuredDimension(finalWidth, finalHeight)
    }
}