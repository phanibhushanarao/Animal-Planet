 
package com.elc.android.planet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.elc.android.planet.quiz.Constants;
import com.elc.android.planet.quiz.GamePlay;
import com.elc.android.planet.quiz.Helper;


public class EndgameActivity extends Activity implements OnClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.endgame);
		GamePlay currentGame = ((QuizApplication)getApplication()).getCurrentGame();
	    	float marks=new Float(currentGame.getRight())/new Float(currentGame.getNumRounds());
		int percent=(int)(marks*100);
	   	String result = "You Got " + currentGame.getRight() + "/" + currentGame.getNumRounds() + "("+percent+"%)"+".. ";
		String comment = Helper.getResultComment(currentGame.getRight(), currentGame.getNumRounds(), getDifficultySettings());
		
		TextView results = (TextView)findViewById(R.id.endgameResult);
		results.setText(result + comment);
		
		int image = Helper.getResultImage(currentGame.getRight(), currentGame.getNumRounds(), getDifficultySettings());
		ImageView resultImage = (ImageView)findViewById(R.id.resultPage);
		resultImage.setImageResource(image);
		
		//handle button actions
		Button finishBtn = (Button) findViewById(R.id.finishBtn);
		finishBtn.setOnClickListener(this);
		Button answerBtn = (Button) findViewById(R.id.answerBtn);
		answerBtn.setOnClickListener(this);
		
	}
	
	private int getDifficultySettings() {
		SharedPreferences settings = getSharedPreferences(Constants.SETTINGS, 0);
		int diff = settings.getInt(Constants.DIFFICULTY, 2);
		return diff;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch (keyCode)
		{
		case KeyEvent.KEYCODE_BACK :
			return true;
		}
		
		return super.onKeyDown(keyCode, event);
	}

	public void onClick(View v) {
		switch (v.getId()){
		case R.id.finishBtn :
			Intent i1=new Intent(getApplicationContext(),SplashActivity.class);
			i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i1);
			break;
			
		case R.id.answerBtn :
			Intent i = new Intent(this, AnswersActivity.class);
			startActivityForResult(i, Constants.PLAYBUTTON);
			break;
		}
	}

}
