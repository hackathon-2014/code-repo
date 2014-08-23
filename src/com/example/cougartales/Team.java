package com.example.cougartales;

import java.util.List;

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
	 * NICKNAMES
	 */
	public List<String> getNicknames() {
		return getList("nicknames");
	}
	
	public void setNicknames(List<String> nicknames) {
		put("nicknames", nicknames);
	}
	
	/*
	 * SPORT
	 */
	public String getSport() {
		return getString("sport");
	}
	
	public void setSport(String sport) {
		put("sport", sport);
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
