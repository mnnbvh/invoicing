package com.xu.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.xu.bean.SaleItem;
import com.xu.bean.SaleOrder;
import com.xu.exception.ServiceException;
import com.xu.service.SaleService;
import com.xu.service.StockService;

@Controller
@RequestMapping("/sale")
@SessionAttributes(value={"cunt","crrentPage","ttalPage","saleorderList","itemList"})
public class SaleController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private List<SaleItem> itemList = new ArrayList<SaleItem>();
	private List<SaleOrder> saleorderList = new ArrayList<SaleOrder>();
	private Long cunt;
	private Long crrentPage;
	private Long ttalPage;
	@Resource
	private SaleService saleService;
	@Resource
	private StockService stockService;
	/**
	 * 此方法用于跳转到销售订单页面
	 * @return
	 */
	@RequestMapping(value="/gotosale")
	public String turn(Model model){
		try {
			saleorderList = saleService.findSaleOrderByPage(0);
			cunt = saleService.findSaleOrderCount();
			crrentPage = 1L;
			ttalPage = saleService.getTotalPage();
			model.addAttribute("crrentPage", crrentPage);
			model.addAttribute("ttalPage", ttalPage);
			model.addAttribute("saleorderList", saleorderList);
			model.addAttribute("cunt", cunt);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "sale";
	}
	
	
	/**
	 * 此方法用于跳转到发货页面
	 * @return
	 */
	@RequestMapping(value="/gotooutsale")
	public String turns(Model model){
		try {
			saleorderList = saleService.findSaleOrderByPage(0);
			cunt = saleService.findSaleOrderCount();
			crrentPage = 1L;
			ttalPage = saleService.getTotalPage();
			model.addAttribute("crrentPage", crrentPage);
			model.addAttribute("ttalPage", ttalPage);
			model.addAttribute("saleorderList", saleorderList);
			model.addAttribute("cunt", cunt);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "out-salestock";
	}
	
	/**
	 * 此方法用于发货功能
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/outstock")
	public String outStock(HttpServletRequest request,Model model){
		logger.info("销售订单发货");
		Long orderId = Long.parseLong(request.getParameter("orderId"));
		try {
			String flag = saleService.findFlagByOrderId(orderId);
			if("未发货".equals(flag)){
				List<SaleItem> saleItemList = saleService.findSaleOrderByOrderId(orderId);
				for(SaleItem item:saleItemList){
					stockService.updateStockCount(item.getName(),item.getCount());
				}
				saleService.updateFlagByOrderId("已发货",orderId);
				saleorderList = saleService.findSaleOrderByPage(0);
				model.addAttribute("saleorderList", saleorderList);
				logger.info("销售订单发货成功");
			}
		} catch (ServiceException e) {
		}
		return "out-salestock";
	}
	/**
	 * 此方法用于添加销售商品
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String addSaleItem(HttpServletRequest request,Model model){
		Long customerId = Long.parseLong(request.getParameter("customerId"));
		String name = request.getParameter("name");
		Double price = Double.parseDouble(request.getParameter("price"));
		Long count =Long.parseLong(request.getParameter("count"));		
		SaleItem saleItem = new SaleItem();
		saleItem.setCount(count);
		saleItem.setName(name);
		saleItem.setPrice(price);
		saleItem.setCustomerId(customerId);
		itemList.add(saleItem);
		model.addAttribute("itemList",itemList);
		return "sale";
	}
	
	/**
	 * 此方法用于跳转到添加销售订单页面
	 * @return
	 */
	@RequestMapping("/forwordOrder")
	public String forwords(){		
		return "saleOrder";
	}
	
	/**
	 * 添加销售订单
	 * @param request
	 * @param model
	 * @return
	 */
    @RequestMapping("/addSaleOrder")
    public String saveSaleOrder(HttpServletRequest request,Model model){
    	String pay = request.getParameter("pay");
    	SaleOrder saleOrder = new SaleOrder();
    	saleOrder.setFlag("未发货");
    	saleOrder.setPay(pay);
    	saleOrder.setSaleDate(new Date());
    	try {
			int i = saleService.addSaleOrder(saleOrder,itemList);
			if(i==1){
				itemList.clear();
				saleorderList = saleService.findSaleOrderByPage(0);
				cunt = saleService.findSaleOrderCount();
				crrentPage = 1L;
				ttalPage = saleService.getTotalPage();
				model.addAttribute("crrentPage", crrentPage);
				model.addAttribute("ttalPage", ttalPage);
				model.addAttribute("saleorderList", saleorderList);
				model.addAttribute("cunt", cunt);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "sale";
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
			saleorderList = saleService.findSaleOrderByPage((int)(pageNum-1));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crrentPage = pageNum;
		model.addAttribute("currentPage", crrentPage);
		model.addAttribute("saleorderList", saleorderList);
		return "sale";
		
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
				saleorderList = saleService.findSaleOrderByPage((int)(pageNum-2));
				crrentPage = pageNum-1;
			}else if(pageNum == 1){
				saleorderList = saleService.findSaleOrderByPage((int)(pageNum-1));
				crrentPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		model.addAttribute("currentPage", crrentPage);
		model.addAttribute("saleorderList", saleorderList);
		return "sale";
		
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
			if(pageNum<=(ttalPage-1)){
				saleorderList = saleService.findSaleOrderByPage((int)(pageNum-0));
				crrentPage = pageNum+1;
			}else if(pageNum == ttalPage){
				saleorderList = saleService.findSaleOrderByPage((int)(pageNum-1));
				crrentPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("currentPage", crrentPage);
		model.addAttribute("saleorderList", saleorderList);
		return "sale";
		
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
			saleorderList = saleService.findSaleOrderByPage((int)(pageNum-1));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crrentPage = pageNum;
		model.addAttribute("currentPage", crrentPage);
		model.addAttribute("saleorderList", saleorderList);
		return "out-salestock";
		
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
				saleorderList = saleService.findSaleOrderByPage((int)(pageNum-2));
				crrentPage = pageNum-1;
			}else if(pageNum == 1){
				saleorderList = saleService.findSaleOrderByPage((int)(pageNum-1));
				crrentPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		model.addAttribute("currentPage", crrentPage);
		model.addAttribute("saleorderList", saleorderList);
		return "out-salestock";
		
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
			if(pageNum<=(ttalPage-1)){
				saleorderList = saleService.findSaleOrderByPage((int)(pageNum-0));
				crrentPage = pageNum+1;
			}else if(pageNum == ttalPage){
				saleorderList = saleService.findSaleOrderByPage((int)(pageNum-1));
				crrentPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("currentPage", crrentPage);
		model.addAttribute("saleorderList", saleorderList);
		return "out-salestock";
		
	}
}
