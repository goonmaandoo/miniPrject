package com.the.dto;

import java.util.Objects;

public class Customer2Dto {
	private Long customerId;
	private String name;
	private String phone;
	
	// getter & setter
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
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
	
	
	@Override
	public int hashCode() {
		return Objects.hash(customerId, name, phone);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer2Dto other = (Customer2Dto) obj;
		return Objects.equals(customerId, other.customerId) && Objects.equals(name, other.name)
				&& Objects.equals(phone, other.phone);
	}
	@Override
	public String toString() {
		return "고객 내역 [고객Id=" + customerId + ", 이름=" + name + ", 핸드폰 번호=" + phone + "]";
	}
	
	public Customer2Dto(){}
	public Customer2Dto(Long customerId, String name, String phone) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.phone = phone;
	}
	
	
	
	
	
	
	
	
	
}
