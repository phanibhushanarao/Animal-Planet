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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class OneImage extends Activity implements OnClickListener
{
	private Question currentQ;
	private GamePlay currentGame;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question);
		currentGame = ((QuizApplication)getApplication()).getCurrentGame();
        currentQ = currentGame.getNextQuestion();
		Button nextBtn = (Button) findViewById(R.id.nextBtn);
		nextBtn.setOnClickListener(this);
                
        //Update the question and answer options..
        setQuestions();
		
    }
 
	private void setQuestions() {
		//set the question text from current question
		String question = Utility.capitalise(currentQ.getQuestion()) + "?";
        TextView qText = (TextView) findViewById(R.id.question);
       // qText.setText(question);
        
        String ans = Utility.capitalise(currentQ.getAnswer()) + "?";
        ImageView  img = (ImageView) findViewById(R.id.logo);
        String uri = "drawable/"+Utility.capitalise(currentQ.getAnswer()).trim();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable image = getResources().getDrawable(imageResource);
        img.setImageDrawable(image);
        
        //set the available options
        List<String> answers = currentQ.getQuestionOptions();
        TextView option1 = (TextView) findViewById(R.id.answer1);
        option1.setText(Utility.capitalise(answers.get(0)));
              
        TextView option2 = (TextView) findViewById(R.id.answer2);
        option2.setText(Utility.capitalise(answers.get(1)));
        
        TextView option3 = (TextView) findViewById(R.id.answer3);
        option3.setText(Utility.capitalise(answers.get(2)));
        
        TextView option4 = (TextView) findViewById(R.id.answer4);
        option4.setText(Utility.capitalise(answers.get(3)));
	}
    

	public void onClick(View arg0) {
		 /**
		 * validate a checkbox has been selected
		 */
		if (!checkAnswer()) 
			{
				Toast.makeText(getApplicationContext(),"select the answer",Toast.LENGTH_SHORT).show();
				return;
			}

		
		/**
		 * check if end of game
		 */
		if (currentGame.isGameOver()){
			//Log.d("Questions", "End of game! lets add up the scores..");
			//Log.d("Questions", "Questions Correct: " + currentGame.getRight());
			//Log.d("Questions", "Questions Wrong: " + currentGame.getWrong());
			Intent i = new Intent(this, EndgameActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			finish();
		}
		else{
			Intent i = new Intent(this, QuestionActivity.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			finish();
		}
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


	/**
	 * Check if a checkbox has been selected, and if it
	 * has then check if its correct and update gamescore
	 */
	private boolean checkAnswer() {
		String answer = getSelectedAnswer();
		if (answer==null){
			//Log.d("Questions", "No Checkbox selection made - returning");
			return false;
		}
		else {
			//Log.d("Questions", "Valid Checkbox selection made - check if correct");
			if (currentQ.getAnswer().equalsIgnoreCase(answer))
			{
				//Log.d("Questions", "Correct Answer!");
				currentGame.incrementRightAnswers();
			}
			else{
				//Log.d("Questions", "Incorrect Answer!");
				currentGame.incrementWrongAnswers();
			}
			return true;
		}
	}


	/**
	 * 
	 */
	private String getSelectedAnswer() {
		RadioButton c1 = (RadioButton)findViewById(R.id.answer1);
		RadioButton c2 = (RadioButton)findViewById(R.id.answer2);
		RadioButton c3 = (RadioButton)findViewById(R.id.answer3);
		RadioButton c4 = (RadioButton)findViewById(R.id.answer4);
		if (c1.isChecked())
		{
			return c1.getText().toString();
		}
		if (c2.isChecked())
		{
			return c2.getText().toString();
		}
		if (c3.isChecked())
		{
			return c3.getText().toString();
		}
		if (c4.isChecked())
		{
			return c4.getText().toString();
		}
		
		return null;
	}
	


}
