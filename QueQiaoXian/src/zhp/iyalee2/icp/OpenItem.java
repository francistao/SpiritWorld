//package zhp.iyalee2.icp;
//
//import zhp.android.utils.Utils_File;
//import zhp.iyalee2.datas.FinalData;
//import android.content.Context;
//import android.os.AsyncTask;
//
///**
// * 用在点击了某个item后打开新的Activity。
// * @用法 OpenItem oi = new OpenItem(xxx, item);  oi.open();
// * @author 郑海鹏
// * @since 2015年7月31日
// */
//public class OpenItem{
//
//	Context context;
//	ItemValue item;
//	
//	@SuppressWarnings("unused")
//	private OpenItem(){}
//	
//	public OpenItem(Context context, ItemValue item) {
//		this.context = context;
//		this.item = item;
//	}
//	
//	/**
//	 * 这个方法只能在主线程中执行，请勿放到子线程中
//	 */
//	public void open(){
//		new AsyncTask<Void, Void, Void>() {
//
//			@Override
//			protected Void doInBackground(Void... params) {
//				Utils_File.getInstance().copyToAssets(context, item.getTargetPath(), FinalData.TARGET);
//				return null;
//			}
//			
//			public void openByType(){
//				switch(item.getType()){
//				case Picture:
//					Utils_File.getInstance().copyToAssets(context, item.getContainPath(), FinalData.CONTAIN_JPG);					
//					break;
//					
//				case Video:
//					Utils_File.getInstance().copyToAssets(context, item.getContainPath(), FinalData.CONTAIN_3GP);
//					break;
//					
//				case Model:
//					Utils_File.getInstance().copyToAssets(context, item.getContainPath(), FinalData.CONTAIN_ZIP);
//					break;
//					
//					default:
//						android.util.Log.i("郑海鹏", "OpenItem#openByType(): " + "未处理的类型！");
//				}
//			}
//			
//		}.execute();
//	}
//	
//
//	
//	
//}
