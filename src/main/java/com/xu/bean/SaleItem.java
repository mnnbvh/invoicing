package com.xu.bean;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

/**
 * 
 * @author XKS22
 *	销售订单明细
 *	id:销售订单明细ID
 *	saleOrderId:关联到销售订单ID
 *	name:商品名称
 *	price:商品单价
 *	count:商品数量
 */
@Entity
@Table(name="sale_item")
public class SaleItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sale_item_id")
	private Long id;
	/*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=SaleOrder.class)
	@JoinColumn(name="sale_order_id")*/
	private Long saleOrderId;
	private Long customerId;
	@Column(name="sale_item_name")
	private String name;
	@Column(name="sale_item_price")
	private Double price;
	@Column(name="sale_item_count")
	private Long count;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSaleOrderId() {
		return saleOrderId;
	}
	public void setSaleOrderId(Long saleOrderId) {
		this.saleOrderId = saleOrderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	

}
