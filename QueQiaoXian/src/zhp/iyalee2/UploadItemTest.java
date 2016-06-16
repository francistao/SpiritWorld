package zhp.iyalee2;

import java.util.ArrayList;

import zhp.android.activities.SlidingFinishActionBarActivity;
import zhp.iyalee2.beans.ItemValue;
import zhp.iyalee2.database.DBOperation_LocalItem;
import zhp.iyalee2.utils.Utils_UploadItem;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UploadItemTest extends SlidingFinishActionBarActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setBackgroundColor(Color.rgb(200, 200, 200));
		layout.setOrientation(LinearLayout.VERTICAL);
		setContentView(layout);
		
		Button btn = new Button(this);
		btn.setText("上传Item");
		layout.addView(btn);
		btn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		DBOperation_LocalItem dbo = new DBOperation_LocalItem(this);
		dbo.openDataBase();
		ArrayList<ItemValue> items = dbo.getItems();
		dbo.closeDataBase();
		
		ItemValue item = null;
		if(items.size() != 0){
			item = items.get(0);
		}else{
			return;
		}
		
		new Utils_UploadItem() {
			
			@Override
			public void onUploadItemSuccess() {
				Toast.makeText(getApplication(), "上传Item成功！", Toast.LENGTH_SHORT).show();
			}
		}.uploadItem(getApplication(), "zhp", "123456", item);
	}

}
