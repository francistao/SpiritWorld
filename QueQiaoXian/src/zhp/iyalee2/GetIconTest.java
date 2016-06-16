//package zhp.iyalee2;
//
//import zhp.android.handlers.StrategyHandler;
//import zhp.iyalee2.utils.Utils_GetUserIcon;
//import android.app.Activity;
//import android.graphics.Bitmap;
//import android.os.Bundle;
//import android.widget.ImageView;
//
//public class GetIconTest extends Activity{
//	ImageView imageView;
//	
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		imageView = new ImageView(this);
//		setContentView(imageView);
//		
//		StrategyHandler handler = new StrategyHandler();
//		
//		new Utils_GetUserIcon(handler) {
//			
//			@Override
//			public void onDownloadFinish(Bitmap bitmap) {
//				imageView.setImageBitmap(bitmap);
//			}
//		}.getUserIcon(getApplication(), "木质的旋律");
//	}
//
//	
//	
//}
