package com.zaptech.surfaceviewexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView {

	private SurfaceHolder surfaceHolder;
	private Bitmap bmpIcon;

	public MySurfaceView(Context context) {
		super(context);
		init();
	}

	public MySurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MySurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		surfaceHolder = getHolder();
		bmpIcon = BitmapFactory.decodeResource(getResources(),
				R.drawable.ic_launcher);
		surfaceHolder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				Canvas canvas = holder.lockCanvas(null);
				drawSomething(canvas);

				holder.unlockCanvasAndPost(canvas);
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {

			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {

			}
		});
	}

	protected void drawSomething(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.MITER);
		paint.setStrokeCap(Paint.Cap.SQUARE);
		paint.setColor(Color.RED);
		paint.setStrokeWidth(16);
		paint.setAlpha(100);

		canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2,
				paint);
		canvas.drawBitmap(bmpIcon, getWidth() / 2, getHeight() / 2, null);
	}

}