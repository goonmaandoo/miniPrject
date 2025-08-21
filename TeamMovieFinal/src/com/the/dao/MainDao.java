package com.the.dao;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.the.dto.MainDto;
import com.the.dto.MainDto2;
import com.the.util.DBConn;

public class MainDao {

	public ArrayList<MainDto> selectAll() {
		ArrayList<MainDto> dtos = new ArrayList<MainDto>();
		String sql = "select b.booking_id, b.customer_id, b.movie_id, b.showtime, b.seats_booked,\r\n"
				+ "c.name, c.phone, m.movie_name, m.genre, m.release_date\r\n"
				+ "from bookings b, customer2 c, movies m\r\n"
				+ "where b.customer_id = c. customer_id and b.movie_id = m.movie_id order by b.booking_id";
		ResultSet rs = DBConn.statementQuery(sql);
		try {
			while (rs.next()) {
				dtos.add(new MainDto(rs.getLong("booking_id"), rs.getLong("customer_id"), rs.getLong("movie_id"),
						rs.getTimestamp("showtime").toLocalDateTime(), rs.getString("seats_booked"),
						rs.getString("name"), rs.getString("phone"), rs.getString("movie_name"), rs.getString("genre"),
						rs.getDate("release_date").toLocalDate()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	public ArrayList<MainDto2> selectAll2(long customerId) {
		ArrayList<MainDto2> dtos = new ArrayList<MainDto2>();
		String sql = String.format(
				"select b.booking_id, b.customer_id, c.name, m.movie_name, b.showtime, b.seats_booked "
						+ "from bookings b, customer2 c, movies m "
						+ "where b.customer_id = c.customer_id and b.movie_id = m.movie_id and c.customer_id = %d order by b.booking_id",
				customerId);
		ResultSet rs = DBConn.statementQuery(sql);
		try {
			while (rs.next()) {
				dtos.add(new MainDto2(rs.getLong("booking_id"), rs.getLong("customer_id"),
						rs.getTimestamp("showtime").toLocalDateTime(), rs.getString("seats_booked"),
						rs.getString("name"), rs.getString("movie_name")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	public static void delete(long bookingId) {
		DBConn.statementUpdate("delete from bookings where booking_id=" + bookingId);

	}

	public void update(long bookingId, LocalDateTime showTime, long seatsBooked) {
		// TODO Auto-generated method stub

	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
	       // 전화번호 형식 정규 표현식 (XXX-XXXX-XXXX 형식)
	       String phoneRegex = "^\\d{3}-\\d{3}-\\d{4}$";
	       Pattern pattern = Pattern.compile(phoneRegex);
	       Matcher matcher = pattern.matcher(phoneNumber);

	       return matcher.matches();
	   }

	public static String getPhoneLast4(long customerId) {
        String sql = String.format(
            "SELECT SUBSTR(phone, 9, 4) AS last_number FROM customer2 WHERE customer_id = %d", 
            customerId);
        ResultSet rs = DBConn.statementQuery(sql);
        try {
            if (rs.next()) {
                return rs.getString("last_number");
            }else {
               return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
