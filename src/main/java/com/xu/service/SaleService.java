package com.xu.service;

import java.util.List;

import com.xu.bean.SaleItem;
import com.xu.bean.SaleOrder;
import com.xu.exception.ServiceException;

public interface SaleService {

	public List<SaleOrder> findSaleOrderByPage(int pageNum)throws ServiceException;

	public Long findSaleOrderCount()throws ServiceException;

	public Long getTotalPage()throws ServiceException;

	public int addSaleOrder(SaleOrder saleOrder, List<SaleItem> itemList)throws ServiceException;

	public List<SaleItem> delPurchaseItemByPurchaseId(Long orderId)throws ServiceException;

	public SaleOrder delOrderByPurchaseId(Long orderId)throws ServiceException;

	public String findFlagByOrderId(Long orderId)throws ServiceException;

	public List<SaleItem> findSaleOrderByOrderId(Long orderId)throws ServiceException;

	public int updateFlagByOrderId(String string, Long orderId)throws ServiceException;

	public int updateStockBySaleReturnOrder(List<SaleItem> saleItem)throws ServiceException;
}
