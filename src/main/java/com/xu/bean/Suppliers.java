package com.xu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * 
 * @author XKS22
 *   供应商
 *id:
 *name:供应商名字
 *address:地址
 *zip:邮编
 *telPhone:电话
 *linkMan:联系人
 *linkTel:联系人电话
 *bank:开户银行
 *bankAccount:银行账号
 *email:邮箱
 */
@Entity
@Table(name="suppliers")
@Component
public class Suppliers {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="suppliers_id")
	private Long id;
	@Column(name="supplier_name")
	private String name;
	private String address;
	private String zip;
	private String telPhone;
	private String linkMan;
	private String linkTel;
	private String bank;
	private Long  bankAccount;
	private String email;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getTelPhone() {
		return telPhone;
	}
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getLinkTel() {
		return linkTel;
	}
	public void setLinkTel(String linkTel) {
		this.linkTel = linkTel;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public Long getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(Long bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
