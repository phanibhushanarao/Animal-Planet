package com.elc.android.planet;

import java.util.List;

import com.elc.android.planet.quiz.GamePlay;
import com.elc.android.planet.quiz.Question;
import com.elc.android.planet.util.Utility;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class FourImages extends Activity implements OnClickListener {
	private GamePlay currentGame;
	private Question currentQ;
	ImageButton firsti, secondi, thirdi, fouri;
	Button nextBtn;
	TextView question;
	String imgname1,imgname2,imgname3,imgname4,rightanswer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fourimages);

		currentGame = ((QuizApplication) getApplication()).getCurrentGame();
		currentQ = currentGame.getNextQuestion();
		Log.d("cureentQ",currentQ+"");
		question = (TextView) findViewById(R.id.textView1);
		firsti = (ImageButton) findViewById(R.id.image1);
		firsti.setOnClickListener(this);
		secondi = (ImageButton) findViewById(R.id.image2);
		secondi.setOnClickListener(this);
		thirdi = (ImageButton) findViewById(R.id.image3);
		thirdi.setOnClickListener(this);
		fouri = (ImageButton) findViewById(R.id.image4);
		fouri.setOnClickListener(this);
		setQuestions();

	}

	private void setQuestions() {
		// set the question text from current question
		String question1 = Utility.capitalise(currentQ.getQuestion()) + "?";
		question.setText(question1);
	
		String answer = currentQ.getAnswer().trim();
		
		// set the available options
		List<String> answers = currentQ.getQuestionOptions();
			
		String ans1=Utility.capitalise(answers.get(0)).trim();
		imgname1=ans1;
		String uri1 = "drawable/"+ans1;
		int imageResource1 = getResources().getIdentifier(uri1, null,getPackageName());
		Drawable image1 = getResources().getDrawable(imageResource1);
		firsti.setImageDrawable(image1);
			
		String ans2=Utility.capitalise(answers.get(1)).trim();
		imgname2=ans2;
		String uri2 = "drawable/"+ans2;
		int imageResource2 = getResources().getIdentifier(uri2, null,getPackageName());
	    
	    Drawable image2= getResources().getDrawable(imageResource2);
	    secondi.setImageDrawable(image2);
		
		String ans3=Utility.capitalise(answers.get(2)).trim();
		imgname3=ans3;
		String uri3 = "drawable/"+ans3;
		int imageResource3 = getResources().getIdentifier(uri3, null,getPackageName());
	   
		Drawable image3= getResources().getDrawable(imageResource3);
	    thirdi.setImageDrawable(image3);
		
		String ans4=Utility.capitalise(answers.get(3)).trim();
		imgname4=ans4;
		String uri4 = "drawable/"+ans4;
		int imageResource4 = getResources().getIdentifier(uri4, null,getPackageName());
	  
	    Drawable image4= getResources().getDrawable(imageResource4);
	    fouri.setImageDrawable(image4);
		rightanswer= currentQ.getAnswer().trim();
		
	}

	public void onClick(View arg0) {
				
		switch(arg0.getId())
		{
		case R.id.image1:
						
				if (rightanswer.equals(imgname1))
				{
					currentGame.incrementRightAnswers();
				}
				else{
					currentGame.incrementWrongAnswers();
				}
				

				 if (currentGame.isGameOver()) {
					Intent i = new Intent(this, EndgameActivity.class);
					startActivity(i);
					break;
				}
				else {
				Intent i = new Intent(this, FourImages.class);
				startActivity(i);
				break;
			  	}
						
		case R.id.image2:
			

			if (rightanswer.equals(imgname2))
			{
				Log.d("image2", imgname2);
				Log.d("answer", currentQ.getAnswer());
				currentGame.incrementRightAnswers();
			}
			else{
				Log.d("image2", imgname2);
				Log.d("answer", currentQ.getAnswer());
				Log.d("Questions", "Incorrect Answer!");
				currentGame.incrementWrongAnswers();
			}
			
			 if (currentGame.isGameOver()) {
				Intent i = new Intent(this, EndgameActivity.class);
				startActivity(i);
				break;
			}
			else {
			Intent i = new Intent(this, FourImages.class);
			startActivity(i);
			Log.d("Questions3", "nextpage!");
			break;
		    
			}
			
		case R.id.image3:
			
			if (rightanswer.equals(imgname3))
			{
				Log.d("image3", imgname3);
				Log.d("answer", currentQ.getAnswer());
				currentGame.incrementRightAnswers();
			}
			else{
				Log.d("image3", imgname3);
				Log.d("answer", currentQ.getAnswer());
				Log.d("Questions", "Incorrect Answer!");
				currentGame.incrementWrongAnswers();
			}
		
			 if (currentGame.isGameOver()) {
				Intent i = new Intent(this, EndgameActivity.class);
				startActivity(i);
				break;
			}
			else {
			Intent i = new Intent(this, FourImages.class);
			startActivity(i);
			Log.d("Questions3", "nextpage!");

			break;
		  	}
		
		case R.id.image4:
			

			if (rightanswer.equals(imgname4))
			{
				Log.d("image4", imgname4);
				Log.d("answer", currentQ.getAnswer());
				currentGame.incrementRightAnswers();
			}
			else{
				Log.d("image4", imgname4);
				Log.d("answer", currentQ.getAnswer());
				Log.d("Questions", "Incorrect Answer!");
				currentGame.incrementWrongAnswers();
			}
			

			 if (currentGame.isGameOver()) {
				Intent i = new Intent(this, EndgameActivity.class);
				startActivity(i);
				break;
			}
			else {
			Intent i = new Intent(this, FourImages.class);
			startActivity(i);
			Log.d("Questions3", "nextpage!");

			break;
		  }
		}
		  
	}
	

		

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	
	private boolean checkAnswer() {
		
		String answer = getSelectedAnswer();
		if (answer==null){
			Log.d("Questions", "No images selection made - returning");
			return false;
		}
		else {
			//Log.d("Questions", "Valid Checkbox selection made - check if correct");
			if (currentQ.getAnswer().equals(answer))
			{
				Log.d("Questions", answer);
				Log.d("Questions", currentQ.getAnswer());
				currentGame.incrementRightAnswers();
			}
			else{
				Log.d("Questions", "Incorrect Answer!");
				currentGame.incrementWrongAnswers();
			}
			return true;
		}
	}
	
	private String getSelectedAnswer() {
		
		List<String> answers = currentQ.getQuestionOptions();
		if (firsti.isPressed()) {
			
			Log.d("status1",firsti.isPressed()+"");
			return answers.get(0);
		}
		else
		if (secondi.isPressed()) {
			Log.d("status2",firsti.isPressed()+"");
			return answers.get(1);
			
		}else
		if (thirdi.isPressed()) {
			Log.d("status3",firsti.isPressed()+"");
			return answers.get(2);
		}else
		if (fouri.isPressed()) {
			Log.d("status4",firsti.isPressed()+"");
			return answers.get(3).trim();
		}
		else
		return null;
	}
	
}
