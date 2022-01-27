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
 * 采购单明细
 * id:
 * name:商品名称
 * purchaseOrderId:关联到采购单ID
 * price:单价
 * count:数量
 */
@Entity
@Table(name="purchase_item")
@Component
public class PurchaseItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="purchase_item_id")
	private Long id;
	/*@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Goods.class)
	@JoinColumn(name="goods_name")*/
	private String name;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Suppliers.class)
	@JoinColumn(name="suppliers_id",nullable=false)*/
	private Long supplierId;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=PurchaseOrder.class)
	@JoinColumn(name="purchase_order_id")*/
	private Long purchaseOrderId;
	@Column(name="purchase_price")
	private Double price;
	@Column(name="purchase_count")
	private Long count;
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
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}
	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
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
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	
	
}
