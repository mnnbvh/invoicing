package com.xu.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xu.bean.PurchaseItem;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem,Long>{

	@Query("from PurchaseItem where purchaseOrderId=?1")
	public List<PurchaseItem> findPurchaseItemByPurchaseId(Long orderId);


}
