package com.example.android.mylayerdrawable;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by evast on 24-7-2017.
 */

public class MyDrawable extends ViewGroup {

    private int displayWidth, displayHeight;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();

        final int cLeft = this.getPaddingLeft();
        final int cTop = this.getPaddingTop();
        final int cRight = this.getMeasuredWidth() - this.getPaddingRight();
        final int cBottom = this.getMeasuredHeight() - this.getPaddingBottom();
        final int cWidth = cRight - cLeft;
        final int cHeight = cBottom - cTop;

        for(int i = 0; i < count; i++){
            View child = getChildAt(i);

            child.measure(  MeasureSpec.makeMeasureSpec(cWidth, MeasureSpec.AT_MOST),
                            MeasureSpec.makeMeasureSpec(cHeight, MeasureSpec.AT_MOST));

            int finalSize = Math.min(child.getMeasuredWidth(), child.getMeasuredHeight());
            child.layout(cLeft, cTop, finalSize, finalSize);

        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 2000;
        int desiredHeight = 2000;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY)
            width = widthSize; //Must be this size
        else if (widthMode == MeasureSpec.AT_MOST)
            width = Math.min(desiredWidth, widthSize); //Can't be bigger than..
        else
            width = desiredWidth; //No limit

        if (heightMode == MeasureSpec.EXACTLY)
            height = heightSize;
        else if (heightMode == MeasureSpec.AT_MOST)
            height = Math.min(desiredHeight, heightSize);
        else
            height = desiredHeight;

        super.setMeasuredDimension(Math.min(width, height), Math.min(width, height));
    }

    private void initSize(Context context){
        final WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        final Display display  = windowManager.getDefaultDisplay();
        Point deviceDisplay = new Point();
        display.getSize(deviceDisplay);
        displayWidth = deviceDisplay.x;
        displayHeight = deviceDisplay.y;
    }

    public MyDrawable(Context context) {
        super(context, null, 0);
        initSize(context);
    }

    public MyDrawable(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initSize(context);
    }

    public MyDrawable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initSize(context);
    }
}