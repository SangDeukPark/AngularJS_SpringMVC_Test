package com.ace.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ADMININFO")
public class AdminInfo extends PageVO {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private String id;
	private String email;	
	private String password;
	private String name;
	private String nameEN;
	private String phoneNumber;
	private String mobileNumber;
	private String createdDate;
	private String modifiedDate;
	private String createdBy;
	private String modifiedBy;
	private String loginFailCount;
	private Date lastLoginDate;
	private String firstPassYN;
	private Date lastPassCreatedDate;
	private String lastPassYN;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEN() {
		return nameEN;
	}
	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getLoginFailCount() {
		return loginFailCount;
	}
	public void setLoginFailCount(String loginFailCount) {
		this.loginFailCount = loginFailCount;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public String getFirstPassYN() {
		return firstPassYN;
	}
	public void setFirstPassYN(String firstPassYN) {
		this.firstPassYN = firstPassYN;
	}
	public Date getLastPassCreatedDate() {
		return lastPassCreatedDate;
	}
	public void setLastPassCreatedDate(Date lastPassCreatedDate) {
		this.lastPassCreatedDate = lastPassCreatedDate;
	}
	public String getLastPassYN() {
		return lastPassYN;
	}
	public void setLastPassYN(String lastPassYN) {
		this.lastPassYN = lastPassYN;
	}
	@Override
	public String toString() {
		return "AdminInfo [id=" + id + ", email=" + email + ", password="
				+ password + ", name=" + name + ", nameEN=" + nameEN
				+ ", phoneNumber=" + phoneNumber + ", mobileNumber="
				+ mobileNumber + ", createdDate=" + createdDate
				+ ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", loginFailCount="
				+ loginFailCount + ", lastLoginDate=" + lastLoginDate
				+ ", firstPassYN=" + firstPassYN + ", lastPassCreatedDate="
				+ lastPassCreatedDate + ", lastPassYN=" + lastPassYN + "]" + super.toString();
	}
	
}
