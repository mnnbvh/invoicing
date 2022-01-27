package com.xu.service;


import java.util.List;

import com.xu.bean.Employee;
import com.xu.bean.Managers;
import com.xu.bean.Message;
import com.xu.bean.PurchaseItem;
import com.xu.bean.PurchaseOrder;
import com.xu.exception.ServiceException;

public interface ManagerService {

	public int register(Managers manager)throws ServiceException;
	public Managers login(String name,String password)throws ServiceException;
	public int addPurchaseOrder(PurchaseOrder purchase,List<PurchaseItem> purchaseItem)throws ServiceException;
	public List<PurchaseOrder> findAllPurchaseOrder()throws ServiceException;
	public List<PurchaseOrder> findPurchaseOrderByPage(int currentPage)throws ServiceException;
	public Long findPurchaseOrderCount()throws ServiceException;
	public Long getTotalPage()throws ServiceException;
	public PurchaseOrder delOrderByPurchaseId(Long orderId)throws ServiceException;
	public List<PurchaseItem> delPurchaseItemByPurchaseId(Long orderId)throws ServiceException;
	public List<PurchaseItem> findPurchaseItemByPurchaseOrderId(Long orderId)throws ServiceException;
	public String findFlagByOrderId(Long orderId)throws ServiceException;
	public int updateFlagByOrderId(String string, Long orderId)throws ServiceException;
	public Employee employeelogin(String name, String password)throws ServiceException;
	public int employeeregister(Employee employee)throws ServiceException;
	public int  updateStockByReturnPurchaseItem(List<PurchaseItem> purchaseItem)throws ServiceException;
	public Employee updateEmplyeeByName(Employee employee)throws ServiceException;
	public Managers updateManagersByName(Managers managers)throws ServiceException;
	public Long findMessageCount()throws ServiceException;
	public List<Message> findAllMessage()throws ServiceException;
	public Message findMessageById(Long id)throws ServiceException;
	public int updateEmployeeById(Long id)throws ServiceException;
	public List<Message> findMessageByPage(int i)throws ServiceException;
	public Long getMessageTotalPage()throws ServiceException;
	public Long findMessageCounts()throws ServiceException;
}
