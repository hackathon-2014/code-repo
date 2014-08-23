package com.example.cougartales;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;


public class MainActivity extends Activity {

	private static final String PARSE_APPLICATION_ID = "CoYwoCt5AGoBn8moKltCjUH6Ma2doDq2EdIVPBJa";
	private static final String PARSE_CLIENT_KEY = "LKVQZ8JPRPZSQeheZvS2riUHqkAnJmwhV9trk9j9";

	private static final String TWITTER_CONSUMER_KEY = "nbnHu4MmqsYrB2UvuLPExSXbl";
	private static final String TWITTER_CONSUMER_SECRET = "bBOkynKsFeMcbCOBhgdnN2QhHfDLT3hAPTrPgNvcfE1H2jnBvr";

	private MainFeedListAdapter adapter;
	private ListView lv;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// setup Parse with Twitter
		Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);
		ParseTwitterUtils.initialize(TWITTER_CONSUMER_KEY,
				TWITTER_CONSUMER_SECRET);
		
		// go to the feed if the user has already authenticated through Twitter
		if (ParseUser.getCurrentUser() != null) {
			startActivity(new Intent(MainActivity.this, MainFeedActivity.class));
		}
		
		lv = (ListView) findViewById(R.id.listView1);
		List<CharlestonTeam> teams =  genTemptData();
		
		adapter = new MainFeedListAdapter(this, R.layout.main_feed__item, teams);
		
		lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
	
	public List<CharlestonTeam> genTemptData() {
		List<CharlestonTeam> a = new LinkedList<CharlestonTeam>();
		for(int i=0; i<10; i++) 
		{
			a.add(new CharlestonTeam());
		}
		
		return a;
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

	public void login(View view) {
		ParseTwitterUtils.logIn(this, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException err) {
				if (user != null) {
					Log.d("Login", "Login successful!");
					startActivity(new Intent(MainActivity.this,
							MainFeedActivity.class));
				} else if (err != null) {
					Log.e("Login", err.getMessage());
				}
			}
		});
	}
}
