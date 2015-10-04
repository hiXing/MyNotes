package com.tools.act;



import com.tools.views.ToggleButton;
import com.toos.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setToggenButton();
	}
private void setToggenButton(){
		ToggleButton toggleBtn = (ToggleButton) findViewById(R.id.setting_notice_toggleBtn);;
	    //切换开关
	    toggleBtn.toggle();
	    //切换无动画
	    toggleBtn.toggle(false);
	    //开关切换事件
	    toggleBtn.setOnToggleChanged(new ToggleButton.OnToggleChanged(){
	            @Override
	            public void onToggle(boolean on) {
	            	
	            }
	    });

	    toggleBtn.setToggleOn();
	    toggleBtn.setToggleOff();
	    //无动画切换
//	    toggleBtn.setToggleOn(false);
//	    toggleBtn.setToggleOff(false);

	    //禁用动画
//	    toggleBtn.setAnimate(false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}
