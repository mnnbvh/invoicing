package com.xu.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.xu.bean.Stock;

public interface StockRepository extends JpaRepository<Stock,Long>{

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update Stock set stock_count = ?1 where goods_id = ?2",nativeQuery=true)
	public void updateStockCountByGoodsId(Long count, Long goodsId);

	@Query("select counts from Stock where goods_id=?1")
	public Long findCountByGoodsId(Long goodsId);

	@Query("from Stock where goods_id=?1")
	public Stock findStockByGoodsId(Long id);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update Stock set stock_area = ?1 where goods_id = ?2",nativeQuery=true)
	public void updateStockAreaByGoodsId(String area, Long goodsId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "update Stock set stock_count = ?2 where goods_id = ?1",nativeQuery=true)
	public void updateStockCount(Long id, Long count);

	@Query("from Stock")
	public List<Stock> findAllGoodsCount();

}
