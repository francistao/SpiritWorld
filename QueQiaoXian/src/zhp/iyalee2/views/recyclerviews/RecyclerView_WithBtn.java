package zhp.iyalee2.views.recyclerviews;

import java.util.ArrayList;

import zhp.android.handlers.StrategyHandler;
import zhp.iyalee2.beans.ButtonData;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

public class RecyclerView_WithBtn extends RecyclerView{
	StrategyHandler handler;
	RecyclerViewAdapter adapter;

	public RecyclerView_WithBtn(Context context) {
		super(context);
	}
	
	public RecyclerView_WithBtn(Context context, ArrayList<ButtonData> datas, StrategyHandler handler) {
		this(context);
		setHasFixedSize(true);
		RecyclerView.LayoutManager manger = new LinearLayoutManager(context);
		setLayoutManager(manger);
		this.handler = handler;
		adapter = new RecyclerViewAdapter(context, handler, datas);
		setAdapter(adapter);
	}
	
	public void addNewItem(ButtonData data){
		adapter.addItem(data, 0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		super.onTouchEvent(arg0);
		return false;
	}
	
	
	
}
