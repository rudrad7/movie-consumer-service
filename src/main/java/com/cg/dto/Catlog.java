package com.cg.dto;

public class Catlog
{
	private int movieId;
	private String movieName;
	public Catlog() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public Catlog(int movieId, String movieName) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
	}

	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	@Override
	public String toString() {
		return "Catlog [movieId=" + movieId + ", movieName=" + movieName + "]";
	}
	
}
