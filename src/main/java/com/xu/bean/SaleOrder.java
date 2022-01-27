package com.xu.bean;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

/**
 * 
 * @author XKS22
 *  销售订单
 *  id:销售订单ID
 *  customerId:用户ID
 *  pay:结算方式
 *  saleDate:销售时间
 *  total:总金额
 */
@Entity
@Table(name="sale_order")
public class SaleOrder {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sale_order_id")
	private Long id;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Customer.class)
	@JoinColumn(name="customer_id")*/
	private Long customerId;
	@Column(name="sale_order_pay")
	private String pay;
	@Column(name="sale_order_sale_date")
	private Date saleDate;
	@Column(name="sale_order_total")
	private Double total;
	private String flag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
