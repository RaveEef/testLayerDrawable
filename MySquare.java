package com.example.android.mylayerdrawable;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.LinearInterpolator;

/**
 * Created by evast on 30-7-2017.
 */

public class MySquare extends View {
    Paint p;

    @Override
    public void draw(Canvas canvas) {
        //Toast.makeText(getContext(), ""+ this.isClickable(), Toast.LENGTH_LONG).show();
        RectF square = new RectF(   0.25f*canvas.getWidth(),
                                    0.25f*canvas.getHeight(),
                                    0.75f*canvas.getWidth(),
                                    0.75f*canvas.getHeight());

        canvas.drawRect(square, p);
        super.draw(canvas);
    }



    @Override
    public void setOnClickListener(OnClickListener l) {

        l = new OnClickListener(){

            @Override
            public void onClick(View v) {
                ViewPropertyAnimator rotateAnimation = v.animate();
                rotateAnimation.setDuration(500);
                rotateAnimation.rotationBy(45);
                rotateAnimation.setInterpolator(new LinearInterpolator());

                final View viewToRotate = v;
                rotateAnimation.setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        viewToRotate.setClickable(false);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        viewToRotate.setClickable(true);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

                rotateAnimation.start();
            }
        };
        super.setOnClickListener(l);
    }

    private void init(){
        p = new Paint();
        p.setAntiAlias(true);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(8f);
        p.setColor(Color.MAGENTA);
    }

    public MySquare(Context context) {
        super(context);
        init();
    }

    public MySquare(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MySquare(Context context, AttributeSet attrs, int defStyleAttr) {
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

        super.setMeasuredDimension(Math.min(width, height), Math.min(width, height));

    }
}
