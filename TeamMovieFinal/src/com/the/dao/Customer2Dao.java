package com.the.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.the.dto.Customer2Dto;
import com.the.util.DBConn;

public class Customer2Dao {

	public static void delete(long customerId) {
		DBConn.statementUpdate("delete from customer2 where customer_id=" + customerId);
	}

	public static void update(long customerId, String name, String phone) {
		String sql = String.format("update customer2 set name='%s', phone='%s' where customer_id=%d", name, phone,
				customerId);
		DBConn.statementUpdate(sql);
	}

	public static void insert(Customer2Dto dto) {
		String sql = String.format("insert into customer2(customer_id, name, phone)" + "values(%d, '%s', '%s')",
				dto.getCustomerId(), dto.getName(), dto.getPhone());
		DBConn.statementUpdate(sql);

	}

	public static ArrayList<Customer2Dto> selectAll() {
		ArrayList<Customer2Dto> dtos = new ArrayList<Customer2Dto>();
		String sql = "select * from customer2 order by customer_id";
		ResultSet rs = DBConn.statementQuery(sql);
		try {
			while (rs.next()) {
				dtos.add(new Customer2Dto(
						rs.getLong("customer_Id"), 
						rs.getString("name"), 
						rs.getString("phone")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	public static Customer2Dto findbyId(long customerId) {

		Customer2Dto dtos = null;
		String sql = String.format("select * from customer2 where customer_id = %d", customerId);
		ResultSet rs = DBConn.statementQuery(sql);

		try {
			if (rs.next()) {
				dtos = new Customer2Dto(rs.getLong("customer_id"), rs.getString("name"), rs.getString("phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dtos;
	}

}
