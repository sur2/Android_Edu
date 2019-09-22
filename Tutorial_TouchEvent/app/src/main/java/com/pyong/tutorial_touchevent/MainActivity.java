package com.pyong.tutorial_touchevent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    protected class MyView  extends View {

        int x = 600, y = 600;
        String string = "";

        public MyView(Context context) {
            super(context);
            setBackgroundColor(Color.YELLOW);
        }

        protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.MAGENTA);
            paint.setTextSize(70);
            canvas.drawRect(x, y, x + 50, y + 50, paint);
            canvas.drawText("액션의 종류 : " + string, 100, 120, paint);
        }

        public boolean onTouchEvent(MotionEvent event) {
            x = (int) event.getX();
            y = (int) event.getY();

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                string  = "ACTION_DOWN";
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                string = "ACTION_MOVE";
            }
            if(event.getAction() == MotionEvent.ACTION_UP) {
                string = "ACTION_UP";
            }
            invalidate();
            return true;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyView myView = new MyView(this);
        setContentView(myView);
    }
}
