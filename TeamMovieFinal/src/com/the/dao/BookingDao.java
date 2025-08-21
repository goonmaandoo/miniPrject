package com.the.dao;

import java.sql.ResultSet;
import java.time.LocalDateTime;

import com.the.dto.BookingDto;
import com.the.util.DBConn;
import com.the.util.UserInput;

public class BookingDao {

	public static void delete(long bookingId) {
		DBConn.statementUpdate("delete from bookings where booking_id=" + bookingId);
	}

	public static void update(long bookingId, LocalDateTime showTime, String seatsBooked) {
		String sql = String.format("update bookings set showtime=TO_DATE('%s', "
						+ "'YYYY-MM-DD HH24:MI:SS'), seats_booked='%s' where booking_id=%d",
				UserInput.dateToString(showTime), seatsBooked, bookingId);
		DBConn.statementUpdate(sql);
	}

	public static void insert(BookingDto dto) {
		String sql = String.format(
				"insert into bookings (customer_Id, movie_Id, showTime, seats_Booked) "
						+ "values(%d, %d, TO_DATE('%s', 'YYYY-MM-DD HH24:MI:SS'), '%s')",
				dto.getCustomerId(), dto.getMovieId(), 
				UserInput.dateToString(dto.getShowTime()), dto.getSeatsBooked());
		DBConn.statementUpdate(sql);

	}	
	   public static BookingDto findById(long bookingId) {
		      BookingDto dtos = null;
		      String sql = String.format("select * from bookings where booking_id = %d ", bookingId);
		      ResultSet rs = DBConn.statementQuery(sql);
		      try {
		         if (rs.next()) {
		            dtos = new BookingDto(rs.getLong("customer_id"), rs.getLong("movie_id"),
		                  rs.getTimestamp("showtime").toLocalDateTime(), rs.getString("seats_booked"));
		         }
		      } catch (Exception e) {
		         e.printStackTrace();
		      }

		      return dtos;
		   }


}