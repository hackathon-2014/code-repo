package com.example.cougartales;

import java.util.List;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import com.parse.ParseException;
import com.parse.ParseObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TeamFeedActivity extends Activity {

	private Team team;

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
		return super.onOptionsItemSelected(item);
	}
}
