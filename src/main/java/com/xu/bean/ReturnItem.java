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
 * 采购退货明细
 * id:采购退货明细ID
 * returnOrderId:关联采购退货单
 * name:商品名称
 * price:商品单价
 * count:商品总数
 */
@Entity
@Table(name="return_item")
public class ReturnItem {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="return_item_id")
	private Long id;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=ReturnOrder.class)
	@JoinColumn(name="return_order_id")*/
	private Long returnOrderId;
	/*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Goods.class)
	@JoinColumn(name="goods_name")*/
	private String goodsName;
	@Column(name="return_item_price")
	private Double price;
	@Column(name="return_item_count")
	private Long count;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getReturnOrderId() {
		return returnOrderId;
	}
	public void setReturnOrderId(Long returnOrderId) {
		this.returnOrderId = returnOrderId;
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
