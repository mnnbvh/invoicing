package com.xu.service;

import java.util.List;

import com.xu.bean.PurchaseItem;
import com.xu.bean.PurchaseOrder;
import com.xu.bean.ReturnItem;
import com.xu.bean.ReturnOrder;
import com.xu.exception.ServiceException;

public interface ReturnPurchaseService {
	public List<ReturnItem> addReturnOrder(PurchaseOrder purchaseOrder,List<PurchaseItem> purchaseItem)throws ServiceException;
	public List<ReturnOrder> findAllReturnOrder()throws ServiceException;
	public List<ReturnOrder> findReturnOrderByPage(int pageNum)throws ServiceException;
	public Long findReturnOrderCount()throws ServiceException;
	public Long getTotalPage()throws ServiceException;
}
