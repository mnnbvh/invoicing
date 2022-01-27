package com.xu.service;

import java.util.List;

import com.xu.bean.Goods;
import com.xu.bean.Stock;
import com.xu.exception.ServiceException;

public interface StockService {

	public int stockAdd(Stock stock,Goods goods)throws ServiceException;
	public List<Stock> findAllStock()throws ServiceException;
	public List<Stock> findStockByPage(int pageNum)throws ServiceException;
	public Long findStockCount()throws ServiceException;
	public Long getTotalPage()throws ServiceException;
	public int updateStock(Long count, Long goodsId)throws ServiceException;
	public Stock findStockByGoodsId(Long goodsId)throws ServiceException;
	public int updateStockAreaByGoodsId(String area, Long goodsId)throws ServiceException;
	public Goods saveGoods(Goods goods)throws ServiceException;
	public Long findGoodsIdByGoodsName(String name)throws ServiceException;
	public int stockAdd(Stock stock)throws ServiceException;
	public int updateStockCount(String name, Long count)throws ServiceException;
}
