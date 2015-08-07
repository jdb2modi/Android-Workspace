package com.zaptech.andipaint2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CanvasView extends View {
	private int path = 0, shape = 0;
	private int count = 0;
	public int width;
	public int height;
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Path mPath;
	private Rect mRect;
	Context context;
	private Paint mPaint;
	private float mX, mY;
	private static final float TOLERANCE = 5;
	private String mstrTool;

	private SharedPreferences mShared;
	public static final String MyPREFERENCES = "MyPrefs";

	public CanvasView(Context c, AttributeSet attrs) {
		super(c, attrs);
		context = c;
		mShared = context.getSharedPreferences(MyPREFERENCES,
				Context.MODE_APPEND);
		// we set a new Path
		mPath = new Path();
		mRect = new Rect();

		mShared = context.getSharedPreferences(MyPREFERENCES,
				context.MODE_WORLD_READABLE | context.MODE_WORLD_WRITEABLE);
		mstrTool = mShared.getString("TOOL", "").toString().trim();

		// and we set a new Paint with the desired attributes
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeWidth(4f);
	}

	// override onSizeChanged
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		// your Canvas will draw onto the defined Bitmap
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

		mCanvas = new Canvas(mBitmap);

	}

	// override onDraw
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// draw the mPath with the mPaint on the canvas when onDraw

		// pencil = md.getMpencil().toString().trim();
		if (mstrTool.equals("pencil")) {
			path = 1;
			if (shape == 1) {
				canvas.drawPath(mPath, mPaint);
				canvas.drawRect(100, 100, 300, 300, mPaint);
			}
			canvas.drawPath(mPath, mPaint);
		} else if (mstrTool.equals("shapes")) {
			shape = 1;
			if (path == 1) {
				canvas.drawPath(mPath, mPaint);
				canvas.drawRect(100, 100, 300, 300, mPaint);
			}
			canvas.drawRect(100, 100, 300, 300, mPaint);
		}

	}

	// when ACTION_DOWN start touch according to the x,y values
	private void startTouch(float x, float y) {

		mPath.moveTo(x, y);
		mX = x;
		mY = y;
	}

	// when ACTION_MOVE move touch according to the x,y values
	private void moveTouch(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOLERANCE || dy >= TOLERANCE) {
			mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);

			mX = x;
			mY = y;
		}

	}

	public void clearCanvas() {
		mPath.reset();
		mRect.set(0, 0, 0, 0);
		invalidate();
	}

	// when ACTION_UP stop touch
	private void upTouch() {
		mPath.lineTo(mX, mY);
		mRect.set(0, 0, 0, 0);

	}

	// override the onTouchEvent
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startTouch(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			moveTouch(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			upTouch();
			invalidate();
			break;
		}
		return true;
	}

	class getPreferenceData {
		private SharedPreferences mShared;
		public static final String MyPREFERENCES = "MyPrefs";

	}
}
