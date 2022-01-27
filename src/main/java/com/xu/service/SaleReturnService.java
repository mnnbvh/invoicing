package com.xu.service;

import java.util.List;

import com.xu.bean.SaleItem;
import com.xu.bean.SaleOrder;
import com.xu.bean.SaleReturnItem;
import com.xu.bean.SaleReturnOrder;
import com.xu.exception.ServiceException;

public interface SaleReturnService {

	public List<SaleReturnItem> addSaleReturnOrder(SaleOrder saleOrder, List<SaleItem> saleItem)throws ServiceException;

	public List<SaleReturnOrder> findReturnOrderByPage(int i)throws ServiceException;

	public Long findReturnOrderCount()throws ServiceException;

	public Long getTotalPage()throws ServiceException;

}
