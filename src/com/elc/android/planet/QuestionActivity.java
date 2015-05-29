/**
 * 
 */
package com.elc.android.planet;

import java.util.List;

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

import com.elc.android.planet.quiz.GamePlay;
import com.elc.android.planet.quiz.Question;
import com.elc.android.planet.util.Utility;
/**
 * @author robert.hinds
 *
 */
public class QuestionActivity extends Activity {

	private Question currentQ;
	private GamePlay currentGame;
//uploaded in github
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Global.position==0)
        {
        	Intent in = new Intent(getApplicationContext(),OneImage.class);
        	in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(in);
        }
        else
        {
        	Intent in1 = new Intent(getApplicationContext(),FourImages.class);
        	in1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    		startActivity(in1);
        }
    
   	}
	
	

}
