package com.elc.android.planet;

import com.elc.android.planet.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class RulesActivity extends Activity implements OnClickListener{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rules);
		
		//finish button
		Button backBtn = (Button) findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
	}
	
	public void onClick(View arg0) {
		
		finish();
	}

}
