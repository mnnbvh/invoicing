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
 *   商品
 * id:
 * name:商品名称
 * unit:单位
 * space:商品产地
 * supplierId:供应商编号
 * approvrId:批准文号
 * batchId:生产批号
 */
@Entity
@Table(name="goods")
public class Goods {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="goods_id",nullable=false,unique=true)
	private Long id;
	@Column(name="goods_name",nullable=false,unique=true)
	private String name;
	private String unit;
	private String space;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Suppliers.class)
	@JoinColumn(name="suppliers_id")*/
	private Long supplierId;
	private Double price;
	private String approveId;
	private String batchId;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getSpace() {
		return space;
	}
	public void setSpace(String space) {
		this.space = space;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public String getApproveId() {
		return approveId;
	}
	public void setApproveId(String approveId) {
		this.approveId = approveId;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
