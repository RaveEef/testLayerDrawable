package com.example.android.mylayerdrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by evast on 30-7-2017.
 */

public class MyTriangle extends View {

    Paint p;

    @Override
    public void draw(Canvas canvas) {
        float radius = (float)Math.sqrt(
                Math.pow((double)(canvas.getWidth()/4), 2) +
                Math.pow((double)(canvas.getHeight()/4), 2));

        RectF square = new RectF(   0.25f*canvas.getWidth(),
                0.25f*canvas.getHeight(),
                0.75f*canvas.getWidth(),
                0.75f*canvas.getHeight());

        PointF top = new PointF(0.5f*canvas.getWidth(), ((canvas.getHeight()/2f) - radius));
       // PointF bottomLeft = new PointF(0.25f*canvas.getWidth(), 0.75f*canvas.getHeight());
        //PointF bottomRight = new PointF(0.75f*canvas.getWidth(), 0.75f*canvas.getHeight());

        Path path = new Path();
        path.moveTo(top.x, top.y);
        float x = (float)(canvas.getWidth()/2f + (radius * Math.cos(Math.toRadians(120))));
        float y = (float)(canvas.getHeight()/2f + (radius * Math.sin(Math.toRadians(120))));
        path.lineTo(x, y);
        float xx = (float)(canvas.getWidth()/2f + (radius * Math.cos(Math.toRadians(240))));
        float yy = (float)(canvas.getHeight()/2f + (radius * Math.sin(Math.toRadians(240))));
        path.lineTo(xx, yy);
        path.close();

        canvas.drawPath(path, p);
        super.draw(canvas);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        l = new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }
        super.setOnClickListener(l);
    }

    //TODO: rotate triangle backwards when clicked
    private  void init(){
        p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(8f);
        p.setColor(Color.YELLOW);

    }
    public MyTriangle(Context context) {
        super(context);
        init();
    }

    public MyTriangle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTriangle(Context context, AttributeSet attrs, int defStyleAttr) {
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
