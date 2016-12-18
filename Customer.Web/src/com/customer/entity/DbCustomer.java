package com.customer.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Customer")
public class DbCustomer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="id")
		private Integer id;

		@Column(name="customer_code")
		private Integer customerCode;
		
		@Column(name="first_name")
		private String firstName;
		
		@Column(name="last_name")
		private String lastName;
		
		@Column(name="sex")
		private String sex;
		
		@Column(name="phone_number")
		private String phoneNumber;
		
		@Column(name="email")
		private String email;
		
		@Column(name="address")
		private String address;	
		
		@Column(name="zip_code")
		private Integer zipCode;
		
		@Column(name="date_enter")
		private Date dateEnter;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public Integer getCustomerCode() {
			return customerCode;
		}

		public void setCustomerCode(Integer customerCode) {
			this.customerCode = customerCode;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Integer getZipCode() {
			return zipCode;
		}

		public void setZipCode(Integer integer) {
			this.zipCode = integer;
		}

		public Date getdateEnter() {
			return dateEnter;
		}

		public void setdateEnter(Date date) {
			this.dateEnter = date;
		}

	}
