package com.rs.corona.models;

public class locationstat {
	
	private String state;
	private String country;
	private int latestcases;
	private int prvDayDiff;
	public int getPrvDayDiff() {
		return prvDayDiff;
	}
	public void setPrvDayDiff(int prvDayDiff) {
		this.prvDayDiff = prvDayDiff;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestcases() {
		return latestcases;
	}
	public void setLatestcases(int latestcases) {
		this.latestcases = latestcases;
	}
	@Override
	public String toString() {
		return "locationstat [state=" + state + ", country=" + country + ", latestcases=" + latestcases
				+ ", prvDayDiff=" + prvDayDiff + "]";
	}
	

}
