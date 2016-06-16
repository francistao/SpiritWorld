package zhp.iyalee2.views.recyclerviews;

import java.util.ArrayList;

import zhp.android.handlers.StrategyHandler;
import zhp.iyalee2.beans.ButtonData;
import zhp.iyalee2.views.buttons.Button_ChangeColorWhenTouch;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
	// =============================================
	//	变量
	// =============================================
	public ArrayList<ButtonData> mDataSet;
	public StrategyHandler handler;
	public Context context;
	
	// =============================================
	//	常量
	// =============================================
	
	// =============================================
	//	构造方法
	// =============================================
	public RecyclerViewAdapter(Context context, StrategyHandler handler, ArrayList<ButtonData> datas) {
		this.context = context;
		this.handler = handler;
		this.mDataSet = datas;
	}
	
	
	

	@Override
	public int getItemCount() {
		return mDataSet.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		if(holder == null)
			zhp.android.debug.Debug.Log(this.getClass().getName(), "holder == null");
		
		if(holder.button == null){
			zhp.android.debug.Debug.Log(this.getClass().getName(), "holder.button == null");
		}
		
		if(mDataSet == null){
			zhp.android.debug.Debug.Log(this.getClass().getName(), "mDataSet == null");
		}
		
		if(mDataSet.get(position) == null){
			zhp.android.debug.Debug.Log(this.getClass().getName(), "mDataSet.get(position) == null");
		}
		
		holder.button.setOptions(mDataSet.get(position));
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		Button_ChangeColorWhenTouch button = new Button_ChangeColorWhenTouch(context);
		ViewHolder vh = new ViewHolder(button);
		return vh;
	}
	
	/**
	 * 添加一个Item， 如果position大于总数量，添加到开始。
	 */
	public void addItem(ButtonData data, int position) {
		if(position > mDataSet.size()){
			position = 0;
		}
		
		mDataSet.add(position, data);
		notifyItemInserted(position);
	}
	
	// =============================================
	//	内部类
	// =============================================
	public class ViewHolder extends RecyclerView.ViewHolder {
		public Button_ChangeColorWhenTouch button;

		public ViewHolder(Button_ChangeColorWhenTouch v) {
			super(v);
			button = v;
		}
	}
}
