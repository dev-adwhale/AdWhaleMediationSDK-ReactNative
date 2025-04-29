package com.adwhalemediationadandroidexpo.java;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

public class RNWrapperView extends FrameLayout {

    public RNWrapperView(Context context) {
        super(context);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
        post(measureAndLayout);
    }


    private final Runnable measureAndLayout = () -> { // React의 App.Component 시작이랑 비슷하네?
        measure(
                MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY));
        layout(getLeft(), getTop(), getRight(), getBottom());
    };

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int maxWidth = 0;
        int maxHeight = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                child.measure(widthMeasureSpec, MeasureSpec.UNSPECIFIED);

                maxWidth = Math.max(maxWidth, child.getMeasuredWidth());
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight());
            }
        }

        int finalWidth = Math.max(maxWidth, getSuggestedMinimumWidth());
        int finalHeight = Math.max(maxHeight, getSuggestedMinimumHeight());

        setMeasuredDimension(finalWidth, finalHeight);
    }
}
