package com.example.cougartales;

import java.util.Date;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Game")
public class Game extends ParseObject {
	
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
	 * HOME TEAM
	 */
	public Team getHomeTeam() {
		return (Team) get("homeTeam");
	}
	
	public void setHomeTeam(Team awayTeam) {
		put("awayTeam", awayTeam);
	}
	
	/*
	 * HOME SCORE
	 */
	public int getHomeScore() {
		return getInt("homeScore");
	}
	
	public void setHomeScore(int homeScore) {
		put("homeScore", homeScore);
	}

	/*
	 * AWAY TEAM
	 */
	public Team getAwayTeam() {
		return (Team) get("awayTeam");
	}

	public void setAwayTeam(Team awayTeam) {
		put("awayTeam", awayTeam);
	}
	
	/*
	 * AWAY SCORE
	 */
	public int getAwayScore() {
		return getInt("awayScore");
	}
	
	public void setAwayScore(int awayScore) {
		put("awayScore", awayScore);
	}
	
	/*
	 * START TIME
	 */
	public Date getStartTime() {
		return getDate("startTime");
	}
	
	public void setStartTime(Date startTime) {
		put("startTime", startTime);
	}
	
	/*
	 * IN PROGRESS
	 */
	public boolean isInProgress() {
		return getBoolean("inProgress");
	}
	
	public void setInProgress(boolean inProgress) {
		put("inProgress", inProgress);
	}
	
	/*
	 * PLACE
	 */
	public String getPlace() {
		return getString("place");
	}
	
	public void setPlace(String place) {
		put("place", place);
	}
}
