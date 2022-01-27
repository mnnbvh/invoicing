package com.xu.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xu.bean.PurchaseItem;
import com.xu.bean.PurchaseOrder;
import com.xu.bean.ReturnItem;
import com.xu.bean.ReturnOrder;
import com.xu.dao.ReturnItemRepository;
import com.xu.dao.ReturnOrderRepository;
import com.xu.exception.ServiceException;
import com.xu.service.ReturnPurchaseService;
@Service
public class ReturnPurchaseServiceImp implements ReturnPurchaseService{
	private List<ReturnItem> list = new ArrayList<ReturnItem>();
	@Resource
	private ReturnOrderRepository returnOrderRepository;
	@Resource
	private ReturnItemRepository returnItemRepository;
	@Override
	public List<ReturnItem> addReturnOrder(PurchaseOrder purchaseOrder,List<PurchaseItem> purchaseItem) throws ServiceException {
		ReturnOrder returnOrder = new ReturnOrder();
		returnOrder.setOutDate(new Date());
		returnOrder.setPay(purchaseOrder.getPay());
		returnOrder.setSuppliersId(purchaseOrder.getSuppliersId());
		returnOrder.setTotal(purchaseOrder.getTotal());		
		ReturnOrder order = returnOrderRepository.save(returnOrder);
		for(PurchaseItem pitem: purchaseItem){
			ReturnItem returnItem = new ReturnItem();
			returnItem.setCount(pitem.getCount());
			returnItem.setGoodsName(pitem.getName());
			returnItem.setPrice(pitem.getPrice());	
			returnItem.setReturnOrderId(order.getId());
			list.add(returnItem);
		}		
		return returnItemRepository.save(list);
	}

	@Override
	public List<ReturnOrder> findAllReturnOrder() throws ServiceException {
		List<ReturnOrder> list = returnOrderRepository.findAll();
		return list;
	}

	@Override
	public List<ReturnOrder> findReturnOrderByPage(int pageNum) throws ServiceException {
		Pageable pageable = new PageRequest(pageNum,10);
		List<ReturnOrder> list = new ArrayList<ReturnOrder>();
		Page<ReturnOrder> page = returnOrderRepository.findAll(pageable);
		for(ReturnOrder p:page){
			list.add(p);
		}
		return list;
	}

	@Override
	public Long findReturnOrderCount() throws ServiceException {
		return returnOrderRepository.count();
	}

	@Override
	public Long getTotalPage() throws ServiceException {
		Long count = findReturnOrderCount();
		Long totalPage;
		if(count%10==0){
			totalPage = count/10;
		}else{
			totalPage = count/10 +1;
		}
		return totalPage;
	}

}
