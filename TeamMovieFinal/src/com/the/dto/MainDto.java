package com.the.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class MainDto {
	private Long bookingId;
	private Long customerId;
	private Long movieId;
	private LocalDateTime showTime;
	private String seatsBooked;
	private String name;
	private String phone;
	private String movieName;
	private String genre;
	private LocalDate releaseDate;
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getMovieId() {
		return movieId;
	}
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public LocalDateTime getShowTime() {
		return showTime;
	}
	public void setShowTime(LocalDateTime showTime) {
		this.showTime = showTime;
	}
	public String getSeatsBooked() {
		return seatsBooked;
	}
	public void setSeatsBooked(String seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
		return Objects.hash(bookingId, customerId, genre, movieId, movieName, name, phone, releaseDate, seatsBooked,
				showTime);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MainDto other = (MainDto) obj;
		return Objects.equals(bookingId, other.bookingId) && Objects.equals(customerId, other.customerId)
				&& Objects.equals(genre, other.genre) && Objects.equals(movieId, other.movieId)
				&& Objects.equals(movieName, other.movieName) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone) && Objects.equals(releaseDate, other.releaseDate)
				&& Objects.equals(seatsBooked, other.seatsBooked) && Objects.equals(showTime, other.showTime);
	}
	@Override
	public String toString() {
		return "예약 내역 [예약 번호=" + bookingId + ", 고객Id=" + customerId + ", 영화번호=" + movieId + ", 상영시간="
				+ showTime + ", 좌석번호=" + seatsBooked + ", 고객 이름=" + name + ", 핸드폰 번호=" + phone + ", 영화 이름="
				+ movieName + ", 장르=" + genre + ", 개봉일=" + releaseDate + "]";
	}

	
	public MainDto() {}
	public MainDto(Long bookingId, Long customerId, Long movieId, LocalDateTime showTime, String seatsBooked,
			String name, String phone, String movieName, String genre, LocalDate releaseDate) {
		super();
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.movieId = movieId;
		this.showTime = showTime;
		this.seatsBooked = seatsBooked;
		this.name = name;
		this.phone = phone;
		this.movieName = movieName;
		this.genre = genre;
		this.releaseDate = releaseDate;
	}
	public MainDto(Long bookingId, Long customerId, String name, String movieName, LocalDateTime showTime, String seatsBooked) {
		super();
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.name = name;
		this.movieName = movieName;
		this.showTime = showTime;
		this.seatsBooked = seatsBooked;
	}
	
	
	
	
	
}
