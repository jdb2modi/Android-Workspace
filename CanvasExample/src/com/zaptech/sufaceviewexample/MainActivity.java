package com.zaptech.sufaceviewexample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity 
{
     @Override
     public void onCreate(Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         setContentView(new MyView(this));
     }

     public class MyView extends View
     {
         public MyView(Context context) 
         {
              super(context);
         }

         @Override
         protected void onDraw(Canvas canvas) 
         {
            super.onDraw(canvas);
            int x = getWidth();
            int y = getHeight();
            int radius;
            radius = 100;
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            // Use Color.parseColor to define HTML colors
            paint.setColor(Color.parseColor("#CD5C5C"));
            canvas.drawCircle(x / 2, y / 2, radius, paint);
        }
     }
}