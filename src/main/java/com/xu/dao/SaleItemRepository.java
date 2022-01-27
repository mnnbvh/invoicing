package com.xu.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xu.bean.SaleItem;


public interface SaleItemRepository extends JpaRepository<SaleItem,Long>{
	@Query("from SaleItem where saleOrderId=?1")
	public List<SaleItem> findSaleItemByOrderId(Long orderId);

	@Query(value = "select sale_item_count from sale_item where sale_item_name=?1 and sale_order_id in ?2",nativeQuery=true)
	public List<BigInteger> findCountByName(String orderName, List<Long> list);

	@Query(value ="select distinct sale_item_name from sale_item",nativeQuery=true)
	public List<String> findAllName();

}
