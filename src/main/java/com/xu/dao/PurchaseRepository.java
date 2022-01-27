package com.xu.dao;



import java.util.Date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.xu.bean.PurchaseOrder;

public interface PurchaseRepository extends JpaRepository<PurchaseOrder,Long>{
	
	@Query("select id from PurchaseOrder where inDate=?1")
	public Long findPurchaseOrderByTotal(Date indate);

	@Query("select flag from PurchaseOrder where purchase_order_id=?1")
	public String findFlagByOrderId(Long orderId);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update purchase_order set flag = ?1 where purchase_order_id = ?2",nativeQuery=true)
	public void updateFlagByOrderId(String string, Long orderId);
	
	
	
}
