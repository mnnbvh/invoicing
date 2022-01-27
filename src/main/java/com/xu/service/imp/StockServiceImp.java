package com.xu.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.xu.bean.Goods;
import com.xu.bean.Message;
import com.xu.bean.Stock;
import com.xu.dao.GoodsRepository;
import com.xu.dao.MessageRepository;
import com.xu.dao.StockRepository;
import com.xu.exception.ServiceException;
import com.xu.service.StockService;
@Service
public class StockServiceImp implements StockService{

	@Resource
	private StockRepository stockRepository;
	@Resource
	private GoodsRepository goodsRepository;
	@Resource
	private Message message;
	@Resource
	private MessageRepository messageRepository;
	
	public int isCount(){
		List<Stock> map = stockRepository.findAllGoodsCount();
		for(Stock stock:map){
			if(stock.getCounts()<100){
				String name = goodsRepository.findGoodsNameById(stock.getGoodsId());
				message.setDate(new Date());
				message.setMsg(name+"库存不足100，现在剩余"+stock.getCounts()+"件！！！");
				message.setFlag("未查看");
				messageRepository.save(message);
				
			}
		}
		return 1;
	}
	@Override
	public int stockAdd(Stock stock, Goods goods) throws ServiceException {
		Goods gods = goodsRepository.findGoodsByName(goods.getName());
		if(gods==null){			
			gods = goodsRepository.save(goods);
		}
		stock.setGoodsId(gods.getId());
		Stock stok = findStockByGoodsId(gods.getId());
		/**
		 * 判断库存是否不足
		 */
		isCount();
		if(stok==null){			
			stockRepository.save(stock);
		}else{
			stockRepository.updateStockCountByGoodsId(stok.getCounts()+stock.getCounts(),stock.getGoodsId());
		}
		return 1;
	}
	@Override
	public List<Stock> findAllStock() throws ServiceException {
		isCount();
		List<Stock> list = stockRepository.findAll();
		return list;
	}
	@Override
	public List<Stock> findStockByPage(int pageNum) throws ServiceException {
		Pageable pageable = new PageRequest(pageNum,10);
		isCount();
		Page<Stock> page = stockRepository.findAll(pageable);
		List<Stock> list = new ArrayList<Stock>();
		for(Stock s:page){
			list.add(s);
		}
		return list;
	}
	@Override
	public Long findStockCount() throws ServiceException {
		long count = stockRepository.count();
		return count;
	}
	@Override
	public Long getTotalPage() throws ServiceException {
		Long count = findStockCount();
		Long totalPage;
		if(count%10==0){
			totalPage = count/10;
		}else{
			totalPage = count/10 +1;
		}
		return totalPage;
		
	}
	@Override
	public int updateStock(Long goodsId,Long count) throws ServiceException {
		Long counts = stockRepository.findCountByGoodsId(goodsId);
		isCount();
		stockRepository.updateStockCountByGoodsId(counts-count,goodsId);
		return 0;
	}
	@Override
	public Stock findStockByGoodsId(Long goodsId) throws ServiceException {
		isCount();
		return stockRepository.findStockByGoodsId(goodsId);
	}
	@Override
	public int updateStockAreaByGoodsId(String area, Long goodsId) throws ServiceException {
		isCount();
		stockRepository.updateStockAreaByGoodsId(area,goodsId);
		return 1;
	}
	@Override
	public Goods saveGoods(Goods goods) throws ServiceException {
		Goods goods2 = goodsRepository.findGoodsByName(goods.getName());
		if(goods2==null){			
			return goodsRepository.save(goods);
		}
		return goods2;
	}
	@Override
	public Long findGoodsIdByGoodsName(String name) throws ServiceException {
		
		return goodsRepository.findGoodsIdByGoodsName(name);
	}
	@Override
	public int stockAdd(Stock stock) throws ServiceException {
		Stock stoks = findStockByGoodsId(stock.getGoodsId());
		isCount();
		if(stoks==null){			
			stockRepository.save(stock);
		}else{			
			stockRepository.updateStockCountByGoodsId(stoks.getCounts()+stock.getCounts(),stock.getGoodsId());
		}
		return 1;
	}
	@Override
	public int updateStockCount(String name, Long id) throws ServiceException {
		Goods goods = goodsRepository.findGoodsByName(name);
		isCount();
		stockRepository.updateStockCount(goods.getId(),id);
		return 1;
	}

}
