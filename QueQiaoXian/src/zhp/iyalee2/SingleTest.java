package zhp.iyalee2;

import zhp.android.data.FinalValue;
import zhp.android.utils.Utils_File;
import zhp.iyalee2.aractivities.single.ARActivity_SinglePicture;
import zhp.iyalee2.utils.Utils_OpenSingleItem;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SingleTest extends Activity implements OnClickListener{
	Button btn_confirm;
	
	String targetPath = FinalValue.FOLDER_BASE_PATH + "target.jpg";
	String containPath = FinalValue.FOLDER_BASE_PATH + "a.jpg";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		findViews();
	}

	private void findViews() {		
		btn_confirm = new Button(this);
		btn_confirm.setText("确定");
		btn_confirm.setOnClickListener(this);
		((ViewGroup)findViewById(android.R.id.content)).addView(btn_confirm);
	}

	@Override
	public void onClick(View v) {
		Toast.makeText(this, "加载资源中", Toast.LENGTH_SHORT).show();
		Utils_OpenSingleItem.openPictureActivity(this, targetPath, containPath);
//		ItemValue item = new ItemValue(FinalValue.FOLDER_BASE_PATH + "arworld/target.jpg", "标题", "简介", FinalValue.FOLDER_BASE_PATH + "arworld/a.jpg", ContainType.Picture, -1);
//		Utils_OpenSingleItem.startSingleItem(this, item);
	}

	@SuppressWarnings("unused")
	@Deprecated
	private void loadContentAndShow() {
		Utils_File.getInstance().copyToAssets(this, targetPath, "single/target.jpg");
		Utils_File.getInstance().copyToAssets(this, containPath, "single/containPicture.jpg");
		Intent intent = new Intent(this, ARActivity_SinglePicture.class);
		startActivity(intent);
	}
}
