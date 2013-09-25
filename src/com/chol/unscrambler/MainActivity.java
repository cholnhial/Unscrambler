package com.chol.unscrambler;

import java.io.BufferedInputStream;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	private Thread searchThread;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final EditText searchBox = (EditText) findViewById(R.id.searchBox);
		Button searchButton = (Button) findViewById(R.id.searchButton);

		searchButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {

				// Show the progress bar while searching
				setContentView(R.layout.loading);

				startSearch(searchBox.getText().toString());
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void startSearch(String searchString) {
		final String pattern = searchString;

		searchThread = new Thread(new Runnable() {
			public void run() {

				Unscrambler unscramble = new Unscrambler("", "");
				WordList wordList = new WordList(new BufferedInputStream(
						getResources().openRawResource(R.raw.words)));

				String text = new String();
				String word = new String();
				while ((word = wordList.readWord()) != null) {

					if (word.length() == pattern.length()) {
						unscramble.setWord(word.toLowerCase(),
								pattern.toLowerCase());
						if (unscramble.containsCharacters()) {
							text = text + word + "\n";
						}
					}
				}

				Intent intent = new Intent(MainActivity.this,
						ResultActivity.class);
				intent.putExtra("results", text);
				startActivity(intent);

			}
		});
		
		searchThread.start();
	}
	
	@SuppressWarnings("deprecation")
	public boolean onKeyDown(int keyCode, KeyEvent event){
	     System.out.println("KEY PRESSED!\n");
		switch(keyCode){
		case KeyEvent.KEYCODE_BACK:
		{
		   searchThread.
			Intent intent = new Intent(MainActivity.this, MainActivity.class);
			startActivity(intent);
		}
		break;
		
		default:
			return false;
		
		}
		return false;
		
	}

}
