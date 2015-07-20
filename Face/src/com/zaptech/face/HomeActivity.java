package com.zaptech.face;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.FaceDetectionListener;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;

@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class HomeActivity extends Activity implements SurfaceHolder.Callback {

	public static final String TAG = HomeActivity.class.getSimpleName();

	private Camera mCamera;

	// We need the phone orientation to correctly draw the overlay:
	private int mOrientation;
	private int mOrientationCompensation;
	private OrientationEventListener mOrientationEventListener;

	// Let's keep track of the display rotation and orientation also:
	private int mDisplayRotation;
	private int mDisplayOrientation;

	// Holds the Face Detection result:
	private Camera.Face[] mFaces;

	// The surface view for the camera data
	private SurfaceView mView;

	// Draw rectangles and other fancy stuff:
	private FaceOverlayView mFaceView;

	/**
	 * Sets the faces for the overlay view, so it can be updated and the face
	 * overlays will be drawn again.
	 */
	private FaceDetectionListener faceDetectionListener = new FaceDetectionListener() {

		@Override
		public void onFaceDetection(android.hardware.Camera.Face[] faces,
				Camera camera) {
			// TODO Auto-generated method stub
			
			Log.d("onFaceDetection", "Number of Faces:" + faces.length);
			// Update the view now!
			mFaceView.setFaces(faces);
			if(faces.length>0)
			{
				mCamera.takePicture(myShutterCallback, 
					      myPictureCallback_RAW, myPictureCallback_JPG);
				mCamera.startPreview();
			}
			
		}
	};
    ShutterCallback myShutterCallback = new ShutterCallback(){

	  @Override
	  public void onShutter() {
	   // TODO Auto-generated method stub
	   
	  }};
	  
	 PictureCallback myPictureCallback_RAW = new PictureCallback(){

	  @Override
	  public void onPictureTaken(byte[] arg0, Camera arg1) {
	   // TODO Auto-generated method stub
	   
	  }};
	  
	 PictureCallback myPictureCallback_JPG = new PictureCallback(){

	  @Override
	  public void onPictureTaken(byte[] arg0, Camera arg1) {
	   // TODO Auto-generated method stub
	   /*Bitmap bitmapPicture 
	    = BitmapFactory.decodeByteArray(arg0, 0, arg0.length); */
	   
	   Uri uriTarget = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());

	   OutputStream imageFileOS;
	   try {
	    imageFileOS = getContentResolver().openOutputStream(uriTarget);
	    imageFileOS.write(arg0);
	    imageFileOS.flush();
	    imageFileOS.close();
	    
	  //  prompt.setText("Image saved: " + uriTarget.toString());
	    
	   } catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   } catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	   }

	   mCamera.startPreview();
	   mCamera.startFaceDetection();
	  }};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mView = new SurfaceView(this);

		setContentView(mView);
		// Now create the OverlayView:
		mFaceView = new FaceOverlayView(this);
		addContentView(mFaceView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		// Create and Start the OrientationListener:
		mOrientationEventListener = new SimpleOrientationEventListener(this);
		mOrientationEventListener.enable();
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		SurfaceHolder holder = mView.getHolder();
		holder.addCallback(this);
	}

	@Override
	protected void onPause() {
		mOrientationEventListener.disable();
		super.onPause();
	}

	@Override
	protected void onResume() {
		mOrientationEventListener.enable();
		super.onResume();
	}

	@Override
	public void surfaceCreated(SurfaceHolder surfaceHolder) {
		mCamera = Camera.open();
//		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
//        Log.d("No of cameras",Camera.getNumberOfCameras()+"");
//        for (int camNo = 0; camNo < Camera.getNumberOfCameras(); camNo++) {
//            CameraInfo camInfo = new CameraInfo();
//            Camera.getCameraInfo(camNo, camInfo);
//           
//            if (camInfo.facing==(Camera.CameraInfo.CAMERA_FACING_FRONT)) {
//                mCamera = Camera.open(camNo);
//            }
//        }
//        if (mCamera == null) {
//           // no front-facing camera, use the first back-facing camera instead.
//           // you may instead wish to inform the user of an error here...
//             mCamera = Camera.open();
//        }
		mCamera.setFaceDetectionListener(faceDetectionListener);
		mCamera.startFaceDetection();
		try {
			mCamera.setPreviewDisplay(surfaceHolder);
		} catch (Exception e) {
			Log.e(TAG, "Could not preview the image.", e);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2,
			int i3) {
		// We have no surface, return immediately:
		if (surfaceHolder.getSurface() == null) {
			return;
		}
		// Try to stop the current preview:
		try {
			mCamera.stopPreview();
		} catch (Exception e) {
			// Ignore...
		}
		// Get the supported preview sizes:
		Camera.Parameters parameters = mCamera.getParameters();
		List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
		Camera.Size previewSize = previewSizes.get(0);
		// And set them:
		parameters.setPreviewSize(previewSize.width, previewSize.height);
		mCamera.setParameters(parameters);
		// Now set the display orientation for the camera. Can we do this
		// differently?
		mDisplayRotation = Util.getDisplayRotation(HomeActivity.this);
		mDisplayOrientation = Util.getDisplayOrientation(mDisplayRotation, 0);
		mCamera.setDisplayOrientation(mDisplayOrientation);

		if (mFaceView != null) {
			mFaceView.setDisplayOrientation(mDisplayOrientation);
		}

		// Finally start the camera preview again:
		mCamera.startPreview();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
		mCamera.setPreviewCallback(null);
		mCamera.setFaceDetectionListener(null);
		mCamera.setErrorCallback(null);
		mCamera.release();
		mCamera = null;
	}

	/**
	 * We need to react on OrientationEvents to rotate the screen and update the
	 * views.
	 */
	private class SimpleOrientationEventListener extends
			OrientationEventListener {

		public SimpleOrientationEventListener(Context context) {
			super(context, SensorManager.SENSOR_DELAY_NORMAL);
		}

		@Override
		public void onOrientationChanged(int orientation) {
			// We keep the last known orientation. So if the user first orient
			// the camera then point the camera to floor or sky, we still have
			// the correct orientation.
			if (orientation == ORIENTATION_UNKNOWN)
				return;
			mOrientation = Util.roundOrientation(orientation, mOrientation);
			// When the screen is unlocked, display rotation may change. Always
			// calculate the up-to-date orientationCompensation.
			int orientationCompensation = mOrientation
					+ Util.getDisplayRotation(HomeActivity.this);
			if (mOrientationCompensation != orientationCompensation) {
				mOrientationCompensation = orientationCompensation;
				mFaceView.setOrientation(mOrientationCompensation);
			}
		}
	}
}