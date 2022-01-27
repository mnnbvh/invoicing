package com.xu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xu.bean.Goods;

public interface  GoodsRepository extends JpaRepository<Goods,Long>{

	@Query("from Goods where goods_name=?1")
	public Goods findGoodsByName(String name);

	@Query("select id from Goods where goods_name=?1")
	public Long findGoodsIdByGoodsName(String name);

	@Query("select name from Goods where goods_id = ?1")
	public String findGoodsNameById(Long key);

}
