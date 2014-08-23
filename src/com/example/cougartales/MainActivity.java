package com.example.cougartales;

import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cougartales.NewMainActivity.PlaceholderFragment;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseTwitterUtils;
import com.parse.ParseUser;
import com.parse.internal.AsyncCallback;

public class MainActivity extends ListActivity implements
NavigationDrawerFragment.NavigationDrawerCallbacks {

/**
* Fragment managing the behaviors, interactions and presentation of the
* navigation drawer.
*/
private NavigationDrawerFragment mNavigationDrawerFragment;

/**
* Used to store the last screen title. For use in
* {@link #restoreActionBar()}.
*/
private CharSequence mTitle;
 

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
			public void done(List<Game> scoreList, ParseException e) {
				if (e == null) {
					Log.d("score", "Retrieved " + scoreList.size() + " scores");

					adapter = new MainFeedListAdapter(MainActivity.this,
							R.layout.main_feed__item, scoreList);

					setListAdapter(adapter);
				} else {
					Log.d("score", "Error: " + e.getMessage());
				}
			}
		});
		
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.new_main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			startActivity(new Intent(getApplicationContext(), NewMainActivity.class));
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
	
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	


}
