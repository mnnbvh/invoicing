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
 *	采购退货
 * id：采购退货编号
 * suppliersId:供应商ID
 * pay:支付方式
 * outDate:退货时间
 * total:总金额
 */
@Entity
@Table(name="return_order")
public class ReturnOrder {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="return_order_id")
	private Long id;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Suppliers.class)
	@JoinColumn(name="suppliers_id")*/
	private Long suppliersId;
	@Column(name="return_order_pay")
	private String pay;
	@Column(name="return_order_outDate")
	private Date outDate;
	@Column(name="return_order_total")
	private Double total;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSuppliersId() {
		return suppliersId;
	}
	public void setSuppliersId(Long suppliersId) {
		this.suppliersId = suppliersId;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public Date getOutDate() {
		return outDate;
	}
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
}
