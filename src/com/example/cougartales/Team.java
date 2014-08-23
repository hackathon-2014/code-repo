package com.example.cougartales;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Team")
public class Team extends ParseObject {

	/*
	 * NAME
	 */
	public String getName() {
		return getString("name");
	}
	
	public void setName(String name) {
		put("name", name);
	}
	
	/*
	 * TWITTER HANDLE
	 */
	public String getTwitterHandle() {
		return getString("twitterHandle");
	}
	
	public void setTwitterHandle(String twitterHandle) {
		put("twitterHandle", twitterHandle);
	}
}
