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
 *	销售退货明细
 */

@Entity
@Table(name="sale_return_item")
public class SaleReturnItem {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sale_return_item_id")
	private Long id;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=SaleReturnOrder.class)
	@JoinColumn(name="sale_return_order_id")*/
	private Long saleReturnOrderId;
	/*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Goods.class)
	@JoinColumn(name="goods_name")*/
	private String goodsName;
	@Column(name="sale_return_item_price")
	private Double price;
	@Column(name="sale_return_item_count")
	private Long count;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSaleReturnOrderId() {
		return saleReturnOrderId;
	}
	public void setSaleReturnOrderId(Long saleReturnOrderId) {
		this.saleReturnOrderId = saleReturnOrderId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
	
	
	
}
