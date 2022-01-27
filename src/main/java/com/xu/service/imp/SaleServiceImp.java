package com.xu.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.xu.bean.SaleItem;
import com.xu.bean.SaleOrder;
import com.xu.dao.GoodsRepository;
import com.xu.dao.SaleItemRepository;
import com.xu.dao.SaleOrderRepository;
import com.xu.dao.StockRepository;
import com.xu.exception.ServiceException;
import com.xu.service.SaleService;

@Service
public class SaleServiceImp implements SaleService{

	@Resource
	private SaleOrderRepository saleOrderRepository;
	@Resource
	private SaleItemRepository saleItemRepository;
	@Resource
	private StockRepository stockRepository;
	@Resource
	private GoodsRepository goodsRepository;
	@Override
	public List<SaleOrder> findSaleOrderByPage(int pageNum) throws ServiceException {
		Pageable pageable = new PageRequest(pageNum, 10);
		Page<SaleOrder> page =saleOrderRepository.findAll(pageable);
		List<SaleOrder> list = new ArrayList<SaleOrder>();
		for(SaleOrder p:page){
			list.add(p);
		}
		return list;
	}
	@Override
	public Long findSaleOrderCount() throws ServiceException {
		
		return saleOrderRepository.count();
	}
	@Override
	public Long getTotalPage() throws ServiceException {
		long count = findSaleOrderCount();
		Long totalPage;
		if(count%10==0){
			totalPage = count/10;
		}else{
			totalPage = count/10 +1;
		}
		return totalPage;
		
	}
	@Override
	public int addSaleOrder(SaleOrder saleOrder, List<SaleItem> itemList) throws ServiceException {
		Double total =0D;
		for(SaleItem item:itemList){
			Double price = item.getPrice();
			Long count = item.getCount();
			Double i = price * count;
			total += i;
		}
		saleOrder.setCustomerId(itemList.get(0).getCustomerId());
		saleOrder.setTotal(total);
		SaleOrder saleOrder2 = saleOrderRepository.save(saleOrder);
		for(SaleItem item:itemList){
			item.setSaleOrderId(saleOrder2.getId());
			saleItemRepository.save(item);
		}
		return 1;
	}
	@Override
	public List<SaleItem> delPurchaseItemByPurchaseId(Long orderId) throws ServiceException {
		List<SaleItem> saleItem = saleItemRepository.findSaleItemByOrderId(orderId);
		saleItemRepository.delete(orderId);
		return saleItem;
	}
	@Override
	public SaleOrder delOrderByPurchaseId(Long orderId) throws ServiceException {
		SaleOrder saleOrder = saleOrderRepository.findOne(orderId);
		saleOrderRepository.delete(orderId);
		return saleOrder;
	}
	@Override
	public String findFlagByOrderId(Long orderId) throws ServiceException {
		
		return saleOrderRepository.findFlagByOrderId(orderId);
	}
	@Override
	public List<SaleItem> findSaleOrderByOrderId(Long orderId) throws ServiceException {
		
		return saleItemRepository.findSaleItemByOrderId(orderId);
	}
	@Override
	public int updateFlagByOrderId(String string, Long orderId) throws ServiceException {
		saleOrderRepository.updateFlagByOrderId(string,orderId);
		
		return 1;
	}
	@Override
	public int updateStockBySaleReturnOrder(List<SaleItem> saleItem) throws ServiceException {
		for(SaleItem si:saleItem){
			Long goodsId = goodsRepository.findGoodsIdByGoodsName(si.getName());
			Long count = stockRepository.findCountByGoodsId(goodsId);
			stockRepository.updateStockCountByGoodsId(count+si.getCount(),goodsId);
		}
		return 0;
	}
	
	

}
