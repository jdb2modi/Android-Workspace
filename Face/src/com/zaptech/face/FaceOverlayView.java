package com.zaptech.face;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Camera.Face;
import android.os.Build;
import android.view.View;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class FaceOverlayView extends View {

	private Paint mPaint;
	private Paint mTextPaint;
	private int mDisplayOrientation;
	private int mOrientation;
	private Face[] mFaces;

	public FaceOverlayView(Context context) {
		super(context);
		initialize();
	}

	private void initialize() {
		// We want a green box around the face:
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.GREEN);
		mPaint.setAlpha(128);
		

//		mTextPaint = new Paint();
//		mTextPaint.setAntiAlias(true);
//		mTextPaint.setDither(true);
//		mTextPaint.setTextSize(20);
//		mTextPaint.setColor(Color.GREEN);
//		mTextPaint.setStyle(Paint.Style.FILL);
	}

	public void setFaces(Face[] faces) {
		mFaces = faces;
		invalidate();
	}

	public void setOrientation(int orientation) {
		mOrientation = orientation;
	}

	public void setDisplayOrientation(int displayOrientation) {
		mDisplayOrientation = displayOrientation;
		invalidate();
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (mFaces != null && mFaces.length > 0) {
			Matrix matrix = new Matrix();
			Util.prepareMatrix(matrix, false, mDisplayOrientation, getWidth(),
					getHeight());
			canvas.save();
			matrix.postRotate(mOrientation);
			canvas.rotate(-mOrientation);
			RectF rectF = new RectF();
		
			for (Face face : mFaces) {
				rectF.set(face.rect);
				matrix.mapRect(rectF);
				canvas.drawRect(rectF, mPaint);
//				canvas.drawText("Score " + face.score, rectF.right, rectF.top,
//						mTextPaint);
			}
			canvas.restore();
		}
	}
}