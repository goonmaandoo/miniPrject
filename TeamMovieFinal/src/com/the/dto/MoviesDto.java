package com.the.dto;

import java.time.LocalDate;
import java.util.Objects;

public class MoviesDto {
	private Long movieId;
	private String movieName;
	private String genre;
	private LocalDate releaseDate;
	
	
	// getter & setter
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public LocalDate getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(genre, movieId, movieName, releaseDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoviesDto other = (MoviesDto) obj;
		return Objects.equals(genre, other.genre) && Objects.equals(movieId, other.movieId)
				&& Objects.equals(movieName, other.movieName) && Objects.equals(releaseDate, other.releaseDate);
	}
	@Override
	public String toString() {
		return "영화 내역 [영화번호=" + movieId + ", 영화제목=" + movieName + ", 장르=" + genre + ", 개봉일="
				+ releaseDate + "]";
	}
	
	public MoviesDto() {}
	public MoviesDto(Long movieId, String movieName, String genre, LocalDate releaseDate) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}
	
	public MoviesDto(String movieName, String genre, LocalDate releaseDate) {
		super();
		this.movieName = movieName;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}

	
	
	
	
	
	
}
