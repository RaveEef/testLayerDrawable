package com.example.android.mylayerdrawable;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDrawable myDrawable = (MyDrawable)findViewById(R.id.viewGroup_myDrawable);
        MySquare mySquare = new MySquare(this);
        myDrawable.addView(mySquare);
        MyCircle myCircle = new MyCircle(this);
        myDrawable.addView(myCircle);
        MyTriangle myTriangle = new MyTriangle(this);
        myDrawable.addView(myTriangle);

        View.OnClickListener onClickRotateSquare = null;
        mySquare.setOnClickListener(onClickRotateSquare);

        /*frame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                ((Animatable)imageView.getDrawable()).start();
            }
        });*/
    }
}
