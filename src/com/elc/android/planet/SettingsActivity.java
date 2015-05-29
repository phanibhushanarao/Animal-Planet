package com.elc.android.planet;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.elc.android.planet.quiz.Constants;

public class SettingsActivity extends Activity implements OnClickListener {

	private Spinner gametype, noofquestions;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		final String[] question = getResources().getStringArray(
				R.array.noofquestions);
		 
		//set listener on update button
		Button updateBtn = (Button) findViewById(R.id.nextBtn);
		updateBtn.setOnClickListener(this);
		updateButtonWithPreferences();
		Button backBtn=(Button) findViewById(R.id.backbtn);
		backBtn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		gametype.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Global.position = arg2;

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

		noofquestions.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub

				Global.questions = Integer.parseInt(question[arg2]);

			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	
	 // Method to update default check box
	 
	private void updateButtonWithPreferences() {
		gametype = (Spinner) findViewById(R.id.spinner2);
		noofquestions = (Spinner) findViewById(R.id.noofquestions);
		RadioButton c1 = (RadioButton) findViewById(R.id.easySetting);
		RadioButton c2 = (RadioButton) findViewById(R.id.mediumSetting);
		SharedPreferences settings = getSharedPreferences(Constants.SETTINGS, 0);
		int diff = settings.getInt(Constants.DIFFICULTY, Constants.MEDIUM);

		switch (diff) {
		case Constants.EASY:
			c1.toggle();
			break;

		case Constants.MEDIUM:
			c2.toggle();
			break;

		}

	}

	public void onClick(View arg0) {
		// check which settings set and return to menu
		 
		if (!checkSelected()) {
			return;
		} else {
			SharedPreferences settings = getSharedPreferences(
					Constants.SETTINGS, 0);
			Editor e = settings.edit();
			e.putInt(Constants.DIFFICULTY, getSelectedSetting());
			e.commit();
			finish();
		}
      
	}

	//Method to check that a checkbox is selected
	 private boolean checkSelected() {
		RadioButton c1 = (RadioButton) findViewById(R.id.easySetting);
		RadioButton c2 = (RadioButton) findViewById(R.id.mediumSetting);
		return (c1.isChecked() || c2.isChecked());
	}
	
	private int getSelectedSetting() {
		RadioButton c1 = (RadioButton) findViewById(R.id.easySetting);
		RadioButton c2 = (RadioButton) findViewById(R.id.mediumSetting);
		if (c1.isChecked()) {
			return Constants.EASY;
		}
		if (c2.isChecked()) {
			return Constants.MEDIUM;
		}

		return Constants.EXTREME;
	}

}
