package com.cg.dto;

public class Rating
{
	private int movieId;
	private int movieRating;
	public Rating(int movieId, int movieRating, String movieName) {
		super();
		this.movieId = movieId;
		this.movieRating = movieRating;
	
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", movieRating=" + movieRating + "]";
	}
	public Rating() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Rating(int movieId, int movieRating) {
		super();
		this.movieId = movieId;
		this.movieRating = movieRating;
	}
	public int getMovieRating() {
		return movieRating;
	}
	public void setMovieRating(int movieRating) {
		this.movieRating = movieRating;
	}


}
