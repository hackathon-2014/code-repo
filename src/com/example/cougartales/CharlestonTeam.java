package com.example.cougartales;

public class CharlestonTeam {
	private String handle;
	private String picUrl;
	private int myScore;
	private int  oppScore;
	private boolean scoreKnown = false;
	
	
	public String getHandle() {
		return handle;
	}


	public void setHandle(String handle) {
		this.handle = handle;
	}


	public String getPicUrl() {
		return picUrl;
	}


	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}


	public int getMyScore() {
		return myScore;
	}


	public void setMyScore(int myScore) {
		this.myScore = myScore;
	}


	public int getOppScore() {
		return oppScore;
	}


	public void setOppScore(int oppScore) {
		this.oppScore = oppScore;
	}


	public CharlestonTeam() {
		
	}

}
