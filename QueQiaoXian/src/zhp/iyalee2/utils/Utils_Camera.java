package zhp.iyalee2.utils;

import zhp.android.utils.Utils_PhoneInfo;
import android.app.Activity;

import com.metaio.sdk.jni.IMetaioSDKAndroid;


public class Utils_Camera {
	
	private static Utils_Camera utils;
	
	private Utils_Camera(){
	}
	
	public static Utils_Camera getInstance(){
		if(utils == null){
			utils = new Utils_Camera();
		}
		return utils;
	}
	
	@SuppressWarnings("deprecation")
	public void setCameraAutoFocus(Activity activity){
		if(Utils_PhoneInfo.getInstance().getAndroidVersionCode() < 21){
			android.hardware.Camera camera = (android.hardware.Camera) IMetaioSDKAndroid
					.getCamera(activity);
			android.hardware.Camera.Parameters params = camera.getParameters();
			params.setFocusMode("continuous-picture");
			camera.setParameters(params);
		}else{
//			try {
//				CameraManager manager = (CameraManager) activity.getSystemService(Context.CAMERA_SERVICE);
//				String[] cameraIdList = manager.getCameraIdList();
////				for(int i = 0; i < cameraIdList.length; i++){
////					CameraCharacteristics cameraCharacteristics = manager.getCameraCharacteristics(cameraIdList[i]);
////				}
//				
//				try {
//					CaptureRequest.Builder mPreviewRequestBuilder 
//                    = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
//					CameraCaptureSession mCaptureSession;
//		            // Reset the auto-focus trigger
//		            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,
//		                    CameraMetadata.CONTROL_AF_TRIGGER_CANCEL);
//		            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
//		                    CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
//		            mCaptureSession.capture(mPreviewRequestBuilder.build(), mCaptureCallback,
//		                    mBackgroundHandler);
//		            // After this, the camera will go back to the normal state of preview.
//		            mState = STATE_PREVIEW;
//		            mCaptureSession.setRepeatingRequest(mPreviewRequest, mCaptureCallback,
//		                    mBackgroundHandler);
//		        } catch (CameraAccessException e) {
//		            e.printStackTrace();
//		        }
//				
//				
//				zhp.android.debug.Debug.Log(this.getClass().getName(), "安卓5.0以上暂时不支持自动对焦！");
//			} catch (Exception e) {
//				zhp.android.debug.Debug.Log(this.getClass().getName() + "#setCameraAutoFocus()",
//						"出现异常！");
//			}
//			
		}
		
	}
	
}
