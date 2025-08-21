package com.the.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class BookingDto {

	private Long bookingId;
	private Long customerId;
	private Long movieId;
	private LocalDateTime showTime;
	private String seatsBooked;
	
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
	
	@Override
	public int hashCode() {
		return Objects.hash(bookingId, customerId, movieId, seatsBooked, showTime);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingDto other = (BookingDto) obj;
		return Objects.equals(bookingId, other.bookingId) && Objects.equals(customerId, other.customerId)
				&& Objects.equals(movieId, other.movieId) && Objects.equals(seatsBooked, other.seatsBooked)
				&& Objects.equals(showTime, other.showTime);
	}
	@Override
	public String toString() {
		return "예약 내역 [예약번호=" + bookingId + ", 고객Id=" + customerId + ", 영화번호=" + movieId
				+ ", 상영시간=" + showTime + ", 좌석번호=" + seatsBooked + "]";
	}
	
	public BookingDto() {}
	public BookingDto(Long bookingId, Long customerId, Long movieId, LocalDateTime showTime, String seatsBooked) {
		super();
		this.bookingId = bookingId;
		this.customerId = customerId;
		this.movieId = movieId;
		this.showTime = showTime;
		this.seatsBooked = seatsBooked;
	}
	public BookingDto(Long customerId, Long movieId, LocalDateTime showTime, String seatsBooked) {
		super();
		this.customerId = customerId;
		this.movieId = movieId;
		this.showTime = showTime;
		this.seatsBooked = seatsBooked;
	}
	
	
	
	
}
