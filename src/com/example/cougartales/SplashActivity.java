package com.example.cougartales;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseTwitterUtils;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	private static final String PARSE_APPLICATION_ID = "CoYwoCt5AGoBn8moKltCjUH6Ma2doDq2EdIVPBJa";
	private static final String PARSE_CLIENT_KEY = "LKVQZ8JPRPZSQeheZvS2riUHqkAnJmwhV9trk9j9";

	private static final String TWITTER_CONSUMER_KEY = "nbnHu4MmqsYrB2UvuLPExSXbl";
	private static final String TWITTER_CONSUMER_SECRET = "bBOkynKsFeMcbCOBhgdnN2QhHfDLT3hAPTrPgNvcfE1H2jnBvr";

	private static int SPLASH_TIME_OUT = 500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		// setup Parse with Twitter
		ParseObject.registerSubclass(Game.class);
		ParseObject.registerSubclass(Team.class);

		Parse.initialize(this, PARSE_APPLICATION_ID, PARSE_CLIENT_KEY);
		ParseTwitterUtils.initialize(TWITTER_CONSUMER_KEY,
				TWITTER_CONSUMER_SECRET);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				// launch the main activity
				startActivity(new Intent(SplashActivity.this,
						MainActivity.class));

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}
}
