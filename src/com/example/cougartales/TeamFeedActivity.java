package com.example.cougartales;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;

public class TeamFeedActivity extends Activity {

	private Team team;
	public EditText tweet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_feed);

		try {
			team = ParseObject.<Team> createWithoutData(Team.class,
					getIntent().getExtras().getString("teamObjectId"))
					.<Team> fetchIfNeeded();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getActionBar().setTitle(team.getSport());
		ActionBar mActionBar = getActionBar();
		mActionBar.setBackgroundDrawable(new ColorDrawable(0xff800000));
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowTitleEnabled(true);

		new AsyncTask<Twitter, Long, List<Status>>() {

			@Override
			protected List<twitter4j.Status> doInBackground(Twitter... twitters) {
				try {
					Twitter twitter = twitters[0];
					return twitter.getUserTimeline(team.getTwitterHandle());

				} catch (TwitterException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(List<twitter4j.Status> result) {
				ListView teamFeed = (ListView) TeamFeedActivity.this
						.findViewById(R.id.team_feed_list);
				teamFeed.setAdapter(new StatusArrayAdapter(
						TeamFeedActivity.this, R.layout.team_feed_item, result));
			};

		}.execute(TwitterFactory.getSingleton());

		tweet = (EditText) findViewById(R.id.editText1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_feed, menu);
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
		
		if (id == R.id.send) {
			final String message = team.getTwitterHandle() + " " + tweet.getText().toString();
			
			new AsyncTask<Void, Void, Void>() {

				@Override
				protected Void doInBackground(Void... params) {

					try {
						TwitterFactory.getSingleton().updateStatus(message);
					} catch (TwitterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					return null;
				}
				
			}.execute();
			
			Toast.makeText(TeamFeedActivity.this, "Tweet sent!", Toast.LENGTH_SHORT);
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
