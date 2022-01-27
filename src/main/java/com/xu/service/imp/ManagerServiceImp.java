package com.xu.service.imp;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.Resource;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xu.bean.Employee;
import com.xu.bean.Managers;
import com.xu.bean.Message;
import com.xu.bean.PurchaseItem;
import com.xu.bean.PurchaseOrder;

import com.xu.dao.EmployeeRepository;
import com.xu.dao.GoodsRepository;
import com.xu.dao.ManagerRepository;
import com.xu.dao.MessageRepository;
import com.xu.dao.PurchaseItemRepository;
import com.xu.dao.PurchaseRepository;
import com.xu.dao.StockRepository;
import com.xu.exception.ServiceException;
import com.xu.service.ManagerService;
import com.xu.util.EncoderByMd5;

@Service
public class ManagerServiceImp implements ManagerService{

	@Resource
	private ManagerRepository managerRepository;
	@Resource
	private PurchaseRepository purchaseRepository;
	@Resource
	private PurchaseItemRepository purchaseItemRepository;
	@Resource
	private EmployeeRepository employeeRepository;
	@Resource
	private StockRepository stockRepository;
	@Resource
	private GoodsRepository goodsRepository;
	@Resource
	private MessageRepository messageReository;
	@Override
	public int register(Managers manager) throws ServiceException {
		managerRepository.save(manager);
		return 0;
	}
	@Override
	public Managers login(String name, String password) throws ServiceException {
		Managers man = managerRepository.findManagersByName(name);
		if(man!=null){
		String md5 = EncoderByMd5.Md5(password);
		if(md5.equals(man.getPassword())){
			return man;
		}else{
			return null;
		}}else{
			return null;
		}
		
	}
	@Override
	public int addPurchaseOrder(PurchaseOrder purchase, List<PurchaseItem> purchaseItem) throws ServiceException {
		Double total =0D;
		for(PurchaseItem item:purchaseItem){
			Double price = item.getPrice();
			Long count = item.getCount();
			Double i = price * count;
			total += i;
		}
		purchase.setSuppliersId(purchaseItem.get(0).getSupplierId());
		purchase.setTotal(total);
		PurchaseOrder order = purchaseRepository.save(purchase);		
		for(PurchaseItem item:purchaseItem){
			item.setPurchaseOrderId(order.getId());
			purchaseItemRepository.save(item);
		}
		
		return 1;
	}
	
	@Override
	public List<PurchaseOrder> findAllPurchaseOrder() {
		List<PurchaseOrder> list = purchaseRepository.findAll();
		return list;
	}
	
	@Override
	public Long findPurchaseOrderCount() {
		return purchaseRepository.count();
	}
	
	
	@Override
	public List<PurchaseOrder> findPurchaseOrderByPage(int pageNum) {	
		Pageable pageable = new PageRequest(pageNum, 10);
		Page<PurchaseOrder> page =purchaseRepository.findAll(pageable);
		List<PurchaseOrder> list = new ArrayList<PurchaseOrder>();
		for(PurchaseOrder p:page){
			list.add(p);
		}
		return list;
	}
	@Override
	public Long getTotalPage(){
		long count = findPurchaseOrderCount();
		Long totalPage;
		if(count%10==0){
			totalPage = count/10;
		}else{
			totalPage = count/10 +1;
		}
		return totalPage;
	}
	@Override
	public PurchaseOrder delOrderByPurchaseId(Long orderId) {
		
			PurchaseOrder purchaseOrder = purchaseRepository.findOne(orderId);
			purchaseRepository.delete(orderId);
		
		return purchaseOrder;
	}
	@Override
	public List<PurchaseItem> delPurchaseItemByPurchaseId(Long orderId) throws ServiceException {
		List<PurchaseItem> purchaseItem = purchaseItemRepository.findPurchaseItemByPurchaseId(orderId);
		purchaseItemRepository.delete(orderId);
		return purchaseItem;
	}
	@Override
	public List<PurchaseItem> findPurchaseItemByPurchaseOrderId(Long orderId) throws ServiceException {
		
		return purchaseItemRepository.findPurchaseItemByPurchaseId(orderId);
	}
	@Override
	public String findFlagByOrderId(Long orderId) throws ServiceException {
		
		return purchaseRepository.findFlagByOrderId(orderId);
	}
	@Override
	public int updateFlagByOrderId(String string, Long orderId) throws ServiceException {
		purchaseRepository.updateFlagByOrderId(string,orderId);
		return 1;
	}
	@Override
	public Employee employeelogin(String name, String password) throws ServiceException {
		Employee man = employeeRepository.findEmployeeByName(name);
		if(man!=null){
			String md5 = EncoderByMd5.Md5(password);
			if(md5.equals(man.getPassword())){
				return man;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}
	@Override
	public int employeeregister(Employee employee) throws ServiceException {
		employeeRepository.save(employee);
		return 0;
	}
	@Override
	public int updateStockByReturnPurchaseItem(List<PurchaseItem> purchaseItem) throws ServiceException {
		for(PurchaseItem pi:purchaseItem){
			Long goodsId = goodsRepository.findGoodsIdByGoodsName(pi.getName());
			Long count = stockRepository.findCountByGoodsId(goodsId);
			stockRepository.updateStockCountByGoodsId(count-pi.getCount(), goodsId);
		}
		return 0;
	}
	@Override
	public Employee updateEmplyeeByName(Employee employee) throws ServiceException {
		employeeRepository.updateEmployeeByName(employee.getAge(),employee.getGender(),employee.getPhonenumber(),employee.getName());
		return employeeRepository.findEmployeeByName(employee.getName());
	}
	@Override
	public Managers updateManagersByName(Managers managers) throws ServiceException {
		managerRepository.updateManagersByName(managers.getAge(),managers.getGender(),managers.getPhonenumber(),managers.getName());
		return managerRepository.findManagersByName(managers.getName());
	}
	@Override
	public Long findMessageCount() throws ServiceException {
		
		return messageReository.findConts();
	}
	@Override
	public List<Message> findAllMessage() throws ServiceException {
		
		return messageReository.findAll();
	}
	@Override
	public Message findMessageById(Long id) throws ServiceException {
		
		return messageReository.findOne(id);
	}
	@Override
	public int updateEmployeeById(Long id) throws ServiceException {
		messageReository.updateMessageById(id);
		return 1;
	}
	@Override
	public List<Message> findMessageByPage(int i) throws ServiceException {
		Pageable pageable = new PageRequest(i, 10);
		Page<Message> page =messageReository.findAll(pageable);
		List<Message> list = new ArrayList<Message>();
		for(Message p:page){
			list.add(p);
		}
		return list;
	}
	@Override
	public Long getMessageTotalPage() throws ServiceException {
		long count = findMessageCounts();
		Long totalPage;
		if(count%10==0){
			totalPage = count/10;
		}else{
			totalPage = count/10 +1;
		}
		return totalPage;
	}
	@Override
	public Long findMessageCounts() throws ServiceException {
		
		return messageReository.count();
	}

}
