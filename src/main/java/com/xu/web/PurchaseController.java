package com.xu.web;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xu.bean.Goods;
import com.xu.bean.PurchaseItem;
import com.xu.bean.PurchaseOrder;
import com.xu.bean.Stock;
import com.xu.exception.ServiceException;
import com.xu.service.ManagerService;
import com.xu.service.StockService;

@Controller
@RequestMapping("/purchase")
@SessionAttributes(value={"list","orderList","count","totalPage","currentPage"})
public class PurchaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private List<PurchaseItem> list = new ArrayList<PurchaseItem>();
	private List<PurchaseOrder> orderList = new ArrayList<PurchaseOrder>();
	private Long count;
	private Long currentPage;
	private Long totalPage;
	private Goods goods;
	private Stock stock;
	private PurchaseItem purchaseItem;	
	private PurchaseOrder purchaseOrder;
	@Resource
	private ManagerService managerService;
	@Resource
	private StockService stockService;
	/*
	 * 此方法用于添加采购商品
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request,HttpServletResponse response,Model model){
		logger.info("开始添加商品");
		Long suppliersId = Long.parseLong(request.getParameter("suppliersId"));
		String name = request.getParameter("name");
		Double price = Double.parseDouble(request.getParameter("price"));
		Long count =Long.parseLong(request.getParameter("count"));		
		String unit = request.getParameter("unit");
		String space = request.getParameter("space");
		Long supplierId = Long.parseLong(request.getParameter("supplierId"));
		String approveId = request.getParameter("approveId");
		String batchId = request.getParameter("batchId");		
		goods = new Goods();
		goods.setName(name);
		goods.setApproveId(approveId);
		goods.setBatchId(batchId);
		goods.setSpace(space);
		goods.setSupplierId(supplierId);
		goods.setUnit(unit);		
		goods.setPrice(price);
		try {
			stockService.saveGoods(goods);
		} catch (ServiceException e) {			
			e.printStackTrace();
		}		
		purchaseItem = new PurchaseItem();
		purchaseItem.setCount(count);
		purchaseItem.setPrice(price);
		purchaseItem.setName(name);
		purchaseItem.setSupplierId(suppliersId);
		list.add(purchaseItem);
		model.addAttribute("list", list);
		logger.info("成功添加商品");
		return "article-list";
	}
	
	/**
	 * 此方法用于跳转到入库首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/forwordAddStock")
	public String fowordAddStock(Model model){
		try {
			orderList = managerService.findPurchaseOrderByPage(0);
			count = managerService.findPurchaseOrderCount();
			currentPage = 1L;
			totalPage = managerService.getTotalPage();
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("orderList", orderList);
			model.addAttribute("count", count);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "add-stock";
		
	}
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addstock")
	public String addStocks(HttpServletRequest request,Model model){
		logger.info("采购商品添加至库存");
		Long orderId = Long.parseLong(request.getParameter("orderId"));
		try {
			String flag = managerService.findFlagByOrderId(orderId);
			if("未入库".equals(flag)){
				List<PurchaseItem> itemList = managerService.findPurchaseItemByPurchaseOrderId(orderId);
				for(PurchaseItem item:itemList){
					stock = new Stock();
					stock.setCounts(item.getCount());
					stock.setGoodsId(stockService.findGoodsIdByGoodsName(item.getName()));
					stockService.stockAdd(stock);
				}
				list.clear();
				managerService.updateFlagByOrderId("已入库",orderId);
				orderList = managerService.findPurchaseOrderByPage(0);
				model.addAttribute("orderList", orderList);
				logger.info("采购商品添加库存成功");
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "add-stock";		
	}
	/**
	 * 此方法用于跳转到采购订单页面
	 * @return
	 */
	@RequestMapping(value="/pur")
	public String turn(Model model){
		try {
			orderList = managerService.findPurchaseOrderByPage(0);
			count = managerService.findPurchaseOrderCount();
			currentPage = 1L;
			totalPage = managerService.getTotalPage();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("orderList", orderList);
		model.addAttribute("count", count);
		return "article-list";
	}
	
	/**
	 * 此方法用于跳转到添加商品页面
	 * @return
	 */
	@RequestMapping("/sub")
	public String forword(){		
		return "purchase";
	}
	
	/**
	 * 此方法用于采购订单的提交
	 * @param request
	 * @return
	 */
	@RequestMapping("/submit")
	public String submit(HttpServletRequest request,Model model){
		logger.info("提交采购订单");
		String pay = request.getParameter("pay");
		purchaseOrder = new PurchaseOrder();
		purchaseOrder.setPay(pay);
		purchaseOrder.setInDate(new Date());
		purchaseOrder.setFlag("未入库");
		try {
			int i = managerService.addPurchaseOrder(purchaseOrder, list);
			if(i==1){
				list.removeAll(list);
				/*orderList.removeAll(orderList);*/
				orderList = managerService.findPurchaseOrderByPage(0);
				count = managerService.findPurchaseOrderCount();
				currentPage = 1L;
				totalPage = managerService.getTotalPage();
				model.addAttribute("currentPage", currentPage);
				model.addAttribute("totalPage", totalPage);
				model.addAttribute("orderList", orderList);
				model.addAttribute("count", count);
				logger.info("提交采购订单成功");
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "article-list";
	}
	
	/**
	 * 分页功能中 首页 尾页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipage")
	public String getPurchase(HttpServletRequest request,Model model){		
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			orderList = managerService.findPurchaseOrderByPage((int)(pageNum-1));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentPage = pageNum;
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("orderList", orderList);
		return "article-list";
		
	}
	
	/**
	 * 分页功能中上一页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipageDown")
	public String getPurchaseDown(HttpServletRequest request,Model model){
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			if(pageNum>1){
				orderList = managerService.findPurchaseOrderByPage((int)(pageNum-2));
				currentPage = pageNum-1;
			}else if(pageNum == 1){
				orderList = managerService.findPurchaseOrderByPage((int)(pageNum-1));
				currentPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("orderList", orderList);
		return "article-list";
		
	}
	
	/**
	 * 分页功能中下一页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipageUp")
	public String getPurchaseUp(HttpServletRequest request,Model model){
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			if(pageNum<=(totalPage-1)){
				orderList = managerService.findPurchaseOrderByPage((int)(pageNum-0));
				currentPage = pageNum+1;
			}else if(pageNum == totalPage){
				orderList = managerService.findPurchaseOrderByPage((int)(pageNum-1));
				currentPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("orderList", orderList);
		return "article-list";
		
	}
	
	
	/**
	 * 分页功能中 首页 尾页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipage1")
	public String getPurchase1(HttpServletRequest request,Model model){		
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			orderList = managerService.findPurchaseOrderByPage((int)(pageNum-1));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		currentPage = pageNum;
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("orderList", orderList);
		return "add-stock";
		
	}
	
	/**
	 * 分页功能中上一页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipageDown1")
	public String getPurchaseDown1(HttpServletRequest request,Model model){
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			if(pageNum>1){
				orderList = managerService.findPurchaseOrderByPage((int)(pageNum-2));
				currentPage = pageNum-1;
			}else if(pageNum == 1){
				orderList = managerService.findPurchaseOrderByPage((int)(pageNum-1));
				currentPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("orderList", orderList);
		return "add-stock";
		
	}
	
	/**
	 * 分页功能中下一页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipageUp1")
	public String getPurchaseUp1(HttpServletRequest request,Model model){
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			if(pageNum<=(totalPage-1)){
				orderList = managerService.findPurchaseOrderByPage((int)(pageNum-0));
				currentPage = pageNum+1;
			}else if(pageNum == totalPage){
				orderList = managerService.findPurchaseOrderByPage((int)(pageNum-1));
				currentPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("orderList", orderList);
		return "add-stock";
		
	}
}
