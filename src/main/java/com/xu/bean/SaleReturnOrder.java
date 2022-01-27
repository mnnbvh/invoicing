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
 *	销售退货
 *id:销售退货ID
 *customer：关联到顾客ID
 *pay:支付方式
 *returnDate:退货时间
 *total:总金额
 */
@Entity
@Table(name="sale_return_order")
public class SaleReturnOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sale_return_order_id")
	private Long id;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Customer.class)
	@JoinColumn(name="customer_id")*/
	private Long customerId;
	@Column(name="sale_return_order_pay")
	private String pay;
	@Column(name="sale_return_order_returnDate")
	private Date returnDate;
	@Column(name="sale_return_order_total")
	private Double total;
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
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}
