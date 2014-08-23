package com.example.cougartales;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
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

	private MainFeedListAdapter adapter;
	private ListView lv;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  Parse.initialize(this, "CoYwoCt5AGoBn8moKltCjUH6Ma2doDq2EdIVPBJa", "LKVQZ8JPRPZSQeheZvS2riUHqkAnJmwhV9trk9j9");

		ParseTwitterUtils.initialize("nbnHu4MmqsYrB2UvuLPExSXbl", "bBOkynKsFeMcbCOBhgdnN2QhHfDLT3hAPTrPgNvcfE1H2jnBvr");
		
		
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
			if(i%2 == 0 ){
				CharlestonTeam t = new CharlestonTeam();
				t.setScoreKnown(true);
				a.add(t);
			}
			else {
			a.add(new CharlestonTeam());
			}
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
				  if(err!=null)
					  Log.d("", "error is  " +err.getMessage());
			    if (user == null) {
			      Log.d("MyApp", "Uh oh. The user cancelled the Twitter login.");
			    } else if (user.isNew()) {
			      Log.d("MyApp", "User signed up and logged in through Twitter!");
			    } else {
			      Log.d("MyApp", "User logged in through Twitter!");
			    }
			    

			  }
			});
		
	}
}
