package com.zaptech.andipaint;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
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
	private Context mContext;
	private Paint mPaint;
	private float mX, mY;
	private static final float TOLERANCE = 5;
	private String mstrData;
	private FrameLayout mContent;
	private SharedPreferences mShared;
	public static final String MyPREFERENCES = "MyPrefs";

	public CanvasView(Context c, AttributeSet attrs) {
		super(c, attrs);
		mContext = c;
		init();
		initPaint();

	}

	// override onSizeChanged
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		// your Canvas will draw onto the defined Bitmap
		mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

		mCanvas = new Canvas(mBitmap);

	}

	public void init() {
		mShared = mContext.getSharedPreferences(MyPREFERENCES,
				Context.MODE_APPEND);
		// we set a new Path
		mPath = new Path();
		mRect = new Rect();
		mContent = (FrameLayout) findViewById(R.id.mContent);
	}

	public void initPaint() {
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeWidth(4f);
	}

	// override onDraw
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// draw the mPath with the mPaint on the canvas when onDraw

		// pencil = md.getMpencil().toString().trim();

		if (mShared.contains("draw")) {
			mstrData = mShared.getString("draw", "").toString();

			if (mstrData.equalsIgnoreCase("pencil")) {

				canvas.drawPath(mPath, mPaint);

			} else if (mstrData.equalsIgnoreCase("shapes")) {

				canvas.drawRect(100, 100, 300, 300, mPaint);

			}

		}

	}

	// when ACTION_DOWN start touch according to the x,y values
	private void startTouch(float x, float y) {
		if (mstrData.equalsIgnoreCase("pencil")) {
			mPath.moveTo(x, y);
			mX = x;
			mY = y;
		}

	}

	// when ACTION_MOVE move touch according to the x,y values
	private void moveTouch(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOLERANCE || dy >= TOLERANCE) {
			if (mstrData.equalsIgnoreCase("pencil")) {
				mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			}

			mX = x;
			mY = y;
		}

	}

	public void clearCanvas() {

	
		
	//	mCanvas.drawColor(0);
		mPath.reset();
		//mCanvas.drawARGB(0, 0, 0, 0);
		invalidate();

	}

	// when ACTION_UP stop touch
	private void upTouch() {
		if (mstrData.equalsIgnoreCase("pencil")) {
			mPath.lineTo(mX, mY);
		}

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
			saveBitmap();

			break;
		}

		return true;
	}

	class getPreferenceData {
		private SharedPreferences mShared;
		public static final String MyPREFERENCES = "MyPrefs";

	}

	public Bitmap saveBitmap() {

		Toast.makeText(getContext(), "inside save", 2500).show();
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/myimage.png");

		try {

			System.err.println("Inside Saving");
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100,
					new FileOutputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mBitmap;
	}

}
