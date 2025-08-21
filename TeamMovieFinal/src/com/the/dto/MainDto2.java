package com.the.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class MainDto2 {
    private Long bookingId;
    private Long customerId;
    private LocalDateTime showTime;
    private String seatsBooked;
    private String name;
    private String movieName;

    // 기본 생성자
    public MainDto2() {
    }

    // 매개변수를 받는 생성자
    public MainDto2(long bookingId, long customerId, LocalDateTime showTime, String seatsBooked, String name, String movieName) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.showTime = showTime;
        this.seatsBooked = seatsBooked;
        this.name = name;
        this.movieName = movieName;
    }

	// Getter 및 Setter
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

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, customerId, movieName, name, seatsBooked, showTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MainDto2 other = (MainDto2) obj;
        return Objects.equals(bookingId, other.bookingId) &&
               Objects.equals(customerId, other.customerId) &&
               Objects.equals(movieName, other.movieName) &&
               Objects.equals(name, other.name) &&
               Objects.equals(seatsBooked, other.seatsBooked) &&
               Objects.equals(showTime, other.showTime);
    }

    @Override
	public String toString() {
    return "예약 내역 [예약 번호=" + bookingId + ", 고객Id=" + customerId + ", 상영시간=" + showTime
            + ", 좌석번호=" + seatsBooked + ", 고객 이름=" + name + ", 영화 제목=" + movieName + "]";
    }
}
