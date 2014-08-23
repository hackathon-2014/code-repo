package com.example.cougartales;

import java.util.Date;

import android.util.Log;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

@ParseClassName("Game")
public class Game extends ParseObject {

	// cached home and away teams
	Team homeTeam;
	Team awayTeam;

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
		if (homeTeam == null || homeTeam.getObjectId() != getString("homeTeam")) {
			try {
				homeTeam = createWithoutData(Team.class, getString("homeTeam"))
						.fetch();
			} catch (ParseException e) {
				Log.e("getHomeTeam", e.getMessage());
			}
		}

		return homeTeam;
	}

	public void setHomeTeam(Team awayTeam) {
		put("awayTeam", awayTeam.getObjectId());
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
		if (awayTeam == null || awayTeam.getObjectId() != getString("awayTeam")) {
			try {
				awayTeam = createWithoutData(Team.class, getString("awayTeam"))
						.fetch();
			} catch (ParseException e) {
				Log.e("getAwayTeam", e.getMessage());
			}
		}

		return awayTeam;
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
