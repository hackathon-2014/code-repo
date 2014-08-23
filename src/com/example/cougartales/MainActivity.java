package com.example.cougartales;

import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.app.ActionBar;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;
import com.parse.internal.AsyncCallback;

public class MainActivity extends ListActivity {

	private MainFeedListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final com.parse.twitter.Twitter parseTwitter = ParseTwitterUtils
				.getTwitter();
		parseTwitter.authorize(this, new AsyncCallback() {

			@Override
			public void onSuccess(Object arg0) {
				Log.d("Twitter Auth Token", parseTwitter.getAuthToken());
				Log.d("Twitter Auth Token Secret",
						parseTwitter.getAuthTokenSecret());

				Twitter twitter = TwitterFactory.getSingleton();
				twitter.setOAuthConsumer(parseTwitter.getConsumerKey(),
						parseTwitter.getConsumerSecret());
				twitter.setOAuthAccessToken(new AccessToken(parseTwitter
						.getAuthToken(), parseTwitter.getAuthTokenSecret()));
			}

			@Override
			public void onFailure(Throwable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCancel() {
				// TODO Auto-generated method stub

			}
		});

		ActionBar mActionBar = getActionBar();
		mActionBar.setBackgroundDrawable(new ColorDrawable(0xff800000));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(true);
		
		ParseQuery<Game> query = ParseQuery.getQuery(Game.class);
		query.findInBackground(new FindCallback<Game>() {
			public void done(final List<Game> games, ParseException e) {
				if (e == null) {
					Log.d("score", "Retrieved " + games.size() + " scores");

					adapter = new MainFeedListAdapter(MainActivity.this,
							R.layout.main_feed__item, games);

					setListAdapter(adapter);
					
					getListView().setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1, int index,
								long arg3) {
							Game game = games.get(index);
							
							Team charleston;
							
							if (game.getHomeTeam().getName().equals("College of Charleston")) {
								charleston = game.getHomeTeam();
							} else {
								charleston = game.getAwayTeam();
							}
							
							Intent intent = new Intent(MainActivity.this, TeamFeedActivity.class);
							intent.putExtra("teamObjectId", charleston.getObjectId());
							startActivity(intent);
						}
					});
				} else {
					Log.d("score", "Error: " + e.getMessage());
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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

	public void login(View view) {
		ParseTwitterUtils.logIn(this, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException err) {
				if (user != null) {
					Log.d("Login", "Login successful!");
				} else if (err != null) {
					Log.e("Login", err.getMessage());
				}
			}
		});
	}
}
