package zhp.android.classs;

import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

public class EditorActionListener_Enter implements TextView.OnEditorActionListener{
	final Button btn;
	
	public EditorActionListener_Enter(Button performButton){
		this.btn = performButton;
	}
	
	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE) {
			btn.performClick();
			return true;
		}
		return false;
	}

}
