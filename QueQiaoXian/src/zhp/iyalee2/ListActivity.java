//package zhp.iyalee2;
//
//import java.util.ArrayList;
//
//import tc.lingjingworld.Activity_Start;
//import zhp.android.activities.SlidingFinishActionBarActivity;
//import zhp.android.handlers.StrategyHandler;
//import zhp.android.utils.Utils_Activity;
//import zhp.iyalee2.aractivities.ARActivity_3G2;
//import zhp.iyalee2.aractivities.ARActivity_Animals;
//import zhp.iyalee2.aractivities.ARActivity_MJ;
//import zhp.iyalee2.aractivities.single.ARActivity_SinglePicture;
////import zhp.iyalee2.backup.AnimalsActivity;
////import zhp.iyalee2.backup.MovieActivity;
////import zhp.iyalee2.backup.PictureActivity;
//import zhp.iyalee2.beans.ButtonData;
//import zhp.iyalee2.views.recyclerviews.RecyclerView_WithBtn;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.LinearLayout;
//
//public class ListActivity extends SlidingFinishActionBarActivity {
//	LinearLayout bgLayout;
//	RecyclerView_WithBtn recyclerView;
//	StrategyHandler handler;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_list);
//
//		initHandler();
//		findOrInitViews();
//		Utils_Activity.getInstance().setStateBarColor(this, getResources().getColor(R.color.colorPrimary));
//	}
//
//	/**
//	 * 实例化及初始化各视图
//	 */
//	private void findOrInitViews() {
//		bgLayout = (LinearLayout) findViewById(R.id.activity_list_bgLayout);
//		initRecyclerView();
//	}
//
//	private void initRecyclerView() {
//		recyclerView = new RecyclerView_WithBtn(this, getItems(), handler);
//		bgLayout.addView(recyclerView);
//	}
//
//	private void initHandler() {
//		handler = new StrategyHandler();
//	}
//
//	private ArrayList<ButtonData> getItems() {
//		ArrayList<ButtonData> datas = new ArrayList<ButtonData>();
//		
//		ButtonData data1 = new ButtonData("动物");
//		data1.setListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(ListActivity.this, ARActivity_Animals.class);
//				startActivity(intent);
//			}
//		});
//
//		ButtonData data3 = new ButtonData("图片");
//		data3.setListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(ListActivity.this, ARActivity_MJ.class);
//				startActivity(intent);
//			}
//		});
//		
//		ButtonData data4 = new ButtonData("视频");
//		data4.setListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(ListActivity.this, ARActivity_3G2.class);
//				startActivity(intent);
//			}
//		});
//		
//		ButtonData data5 = new ButtonData("single");
//		data5.setListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
////				Intent intent = new Intent(ListActivity.this, SingleTest.class);
////				startActivity(intent);
//			}
//		});
//		
//		ButtonData data6 = new ButtonData("注册");
//		data6.setListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(ListActivity.this, SignUpTest.class);
//				startActivity(intent);
//			}
//		});
//		
//		ButtonData data8 = new ButtonData("添加好友");
//		data8.setListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(ListActivity.this, AddFriend.class);
//				startActivity(intent);
//			}
//		});
//		
//		ButtonData data9 = new ButtonData("上传头像测试");
//		data9.setListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(ListActivity.this, UploadIcon.class);
//				startActivity(intent);
//			}
//		});
//		
////		ButtonData data10 = new ButtonData("获取头像测试");
////		data10.setListener(new View.OnClickListener() {
////			
////			@Override
////			public void onClick(View v) {
////				Intent intent = new Intent(ListActivity.this, GetIconTest.class);
////				startActivity(intent);
////			}
////		});
//		
//		ButtonData data11 = new ButtonData("进入界面");
//		data11.setListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(ListActivity.this, Activity_Start.class);
//				startActivity(intent);
//			}
//		});
//		
//		ButtonData data12 = new ButtonData("上传Item测试");
//		data12.setListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(ListActivity.this, UploadItemTest.class);
//				startActivity(intent);
//			}
//		});
//		
//		ButtonData data13 = new ButtonData("ARActivity_SinglePicture");
//		data13.setListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(ListActivity.this, ARActivity_SinglePicture.class);
//				startActivity(intent);
//			}
//		});
//		
//		
//		datas.add(data1);
//		datas.add(data3);
//		datas.add(data4);
//		datas.add(data5);
//		datas.add(data6);
//		datas.add(data8);
//		datas.add(data9);
////		datas.add(data10);
//		datas.add(data11);
//		datas.add(data12);
//		datas.add(data13);
//		return datas;
//	}
//
//	
//	
//}
