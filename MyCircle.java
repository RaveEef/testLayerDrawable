package com.example.android.mylayerdrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by evast on 30-7-2017.
 */

public class MyCircle extends View{

    Paint p;

    @Override
    public void draw(Canvas canvas) {
        float radius = (float)Math.sqrt(
                Math.pow((double)(canvas.getWidth()/4), 2) +
                Math.pow((double)(canvas.getHeight()/4), 2));
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, radius, p);
        super.draw(canvas);
    }

    private void init(){
        p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(8f);
        p.setColor(Color.GREEN);
    }

    public MyCircle(Context context) {
        super(context);
        init();
    }

    public MyCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
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

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
