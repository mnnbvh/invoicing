package com.xu.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xu.bean.SaleItem;
import com.xu.bean.SaleOrder;
import com.xu.bean.SaleReturnItem;
import com.xu.bean.SaleReturnOrder;
import com.xu.exception.ServiceException;
import com.xu.service.SaleReturnService;
import com.xu.service.SaleService;

@Controller
@RequestMapping("/salereturn")
@SessionAttributes(value={"cont","returnsList","cutPage","talPage"})
public class SaleReturnController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private List<SaleReturnItem> itemsList;
	private List<SaleReturnOrder> returnsList;
	private Long cont;
	private Long cutPage;
	private Long talPage;	
	@Resource
	private SaleReturnService saleReturnService;
	@Resource
	private SaleService saleService;
	
	/**
	 * 添加销售退货订单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/addOrder")
	public String addReturnOrder(HttpServletRequest request,Model model){
		logger.info("销售退货订单添加");
		Long orderId =Long.parseLong(request.getParameter("orderId"));
		try {
			List<SaleItem> saleItem = saleService.delPurchaseItemByPurchaseId(orderId);
			SaleOrder saleOrder = saleService.delOrderByPurchaseId(orderId);
			if("已发货".equals(saleOrder.getFlag())){
				saleService.updateStockBySaleReturnOrder(saleItem);
			}
			itemsList = saleReturnService.addSaleReturnOrder(saleOrder, saleItem);
			returnsList = saleReturnService.findReturnOrderByPage(0);
			cont = saleReturnService.findReturnOrderCount();
			cutPage = 1L;
			talPage = saleReturnService.getTotalPage();
			model.addAttribute("itemsList", itemsList);
			model.addAttribute("returnsList", returnsList);
			model.addAttribute("cont", cont);
			model.addAttribute("cutPage", cutPage);
			model.addAttribute("talPage", talPage);
			logger.info("销售退货订单添加成功");
			return "salereturn";
		} catch (Exception e) {
			e.printStackTrace();
			return "salereturn";
		}
		
	}
	
	/**
	 * 跳转到退货首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/forwordSaleReturn")
	public String forwordSale(Model model){
		try {
			returnsList = saleReturnService.findReturnOrderByPage(0);
			cont = saleReturnService.findReturnOrderCount();
			cutPage = 1L;
			talPage = saleReturnService.getTotalPage();
			model.addAttribute("itemsList", itemsList);
			model.addAttribute("returnsList", returnsList);
			model.addAttribute("cont", cont);
			model.addAttribute("cutPage", cutPage);
			model.addAttribute("talPage", talPage);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	catch (EmptyResultDataAccessException e) {
			return null;
		}			
		return "salereturn";
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
			returnsList = saleReturnService.findReturnOrderByPage((int)(pageNum-1));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cutPage = pageNum;
		model.addAttribute("cutPage", cutPage);
		model.addAttribute("returnsList", returnsList);
		return "salereturn";
		
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
				returnsList = saleReturnService.findReturnOrderByPage((int)(pageNum-2));
				cutPage = pageNum-1;
			}else if(pageNum == 1){
				returnsList = saleReturnService.findReturnOrderByPage((int)(pageNum-1));
				cutPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		model.addAttribute("cutPage", cutPage);
		model.addAttribute("returnsList", returnsList);
		return "salereturn";
		
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
			if(pageNum<=(talPage-1)){
				returnsList = saleReturnService.findReturnOrderByPage((int)(pageNum-0));
				cutPage = pageNum+1;
			}else if(pageNum == talPage){
				returnsList = saleReturnService.findReturnOrderByPage((int)(pageNum-1));
				cutPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("cutPage", cutPage);
		model.addAttribute("returnsList", returnsList);
		return "salereturn";
		
	}
}
