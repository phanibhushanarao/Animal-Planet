
package com.elc.android.planet;

import com.elc.android.planet.quiz.GamePlay;

import android.app.Application;


public class QuizApplication extends Application{
	private GamePlay currentGame;
	
	public void setCurrentGame(GamePlay currentGame) {
		this.currentGame = currentGame;
	}
	
	public GamePlay getCurrentGame() {
		return currentGame;
	}
}
