package com.xu.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xu.bean.Customer;
import com.xu.bean.Goods;
import com.xu.bean.Suppliers;
import com.xu.exception.ServiceException;
import com.xu.service.QueryService;

@Controller
@RequestMapping("/query")
@SessionAttributes(value={"goods","name","count","map"})
public class QueryController {

	@Resource
	private QueryService queryService;
	/**
	 * 跳转到商品查询首页
	 * @return
	 */
	@RequestMapping("/goodes")
	public String gotoQueryGoods(){
		
		return "queryGoods";
	}
	
	/**
	 * 跳转到客户查询首页
	 * @return
	 */
	@RequestMapping("/customers")
	public String gotoQueryCustomer(){
		
		return "queryCustomer";
	}
	
	/**
	 * 跳转到供应商查询首页
	 * @return
	 */
	@RequestMapping("/supplierss")
	public String gotoQuerySuppliers(){
		
		return "querySuppliers";
	}
	
	/**
	 * 跳转到商品订单查询首页
	 * @return
	 */
	@RequestMapping("/goodsOrder")
	public String gotoQueryGoodsOrder(){
		
		return "querySaleOrder";
	}
	
	/**
	 * 跳转到商品订单查询首页
	 * @return
	 */
	@RequestMapping("/allgoodsOrder")
	public String gotoQueryAllGoodsOrder(){
		
		return "queryAllSaleOrder";
	}
	
	/**
	 * 销售统计
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryAllOrder")
	public String queryAllOrders(HttpServletRequest request,Model model){
		String min = request.getParameter("logmin");
		String max = request.getParameter("logmax");
		try {
			Map<String,Long> map = queryService.findAllOrderCount(min,max);
			model.addAttribute("map", map);
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		return "queryAllSaleOrder";
	}
	
	/**
	 * 查询某种商品在某个时间段的销量
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryOrder")
	public String queryGoodsOrders(HttpServletRequest request,Model model){
		String min = request.getParameter("logmin");
		String max = request.getParameter("logmax");
		String orderName = request.getParameter("orderName");
		try {
			Long count = queryService.findGoodsOrderByTimeAndName(min,max,orderName);
			model.addAttribute("name",orderName);
			model.addAttribute("count", count);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "querySaleOrder";
	}
	
	/**
	 * 查询商品
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryGoods")
	public String querygoods(HttpServletRequest request,Model model){
		String name = request.getParameter("goodName");
		try {
			Goods goods = queryService.findGoodsByName(name);
			if (goods != null) {
				model.addAttribute("goods",goods);

			}
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		return "queryGoods";
	}
	
	/**
	 * 查询客户
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/queryEmployee")
	public String querycustomer(HttpServletRequest request,Model model){
		String name = request.getParameter("customerName");
		try {
			Customer customer = queryService.findCustomerByName(name);
			model.addAttribute("customer",customer);
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		return "queryCustomer";
	}
	
	/**
	 * 查询供应商
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/querySuppliers")
	public String querysuppliers(HttpServletRequest request,Model model){
		String name = request.getParameter("suppliersName");
		try {
			Suppliers suppliers = queryService.findSuppliersByName(name);
			model.addAttribute("suppliers",suppliers);
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		return "querySuppliers";
	}
}
