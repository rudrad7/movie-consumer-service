package com.cg.dto;

public class MovieInfo 
{
	@Override
	public String toString() {
		return "MovieInfo [movId=" + movId + ", movName=" + movName + ", movRating=" + movRating + "]";
	}
	public MovieInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MovieInfo(int movId, String movName, int movRating) {
		super();
		this.movId = movId;
		this.movName = movName;
		this.movRating = movRating;
	}
	public int getMovId() {
		return movId;
	}
	public void setMovId(int movId) {
		this.movId = movId;
	}
	public String getMovName() {
		return movName;
	}
	public void setMovName(String movName) {
		this.movName = movName;
	}
	public int getMovRating() {
		return movRating;
	}
	public void setMovRating(int movRating) {
		this.movRating = movRating;
	}
	private int movId;
	private String movName;
	private int movRating;

}
