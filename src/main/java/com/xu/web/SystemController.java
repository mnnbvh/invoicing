package com.xu.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xu.bean.Customer;
import com.xu.bean.Employee;
import com.xu.bean.Goods;
import com.xu.bean.Suppliers;
import com.xu.exception.ServiceException;
import com.xu.service.SuppliersAndCustomerService;
import com.xu.service.SystemService;

@Controller
@RequestMapping("/sys")
@SessionAttributes(value={"goodsList","couns","totaPage","curtPage"})
public class SystemController {
	private List<Goods> goodsList;
	private List<Suppliers> suppliersList;
	private List<Customer> customerList;
	private List<Employee> employeeList;
	private Long couns;
	private Long totaPage;
	private Long curtPage;
	private Suppliers suppliers;
	private Customer customer;
	@Resource
	private SystemService systemService;
	@Resource
	private SuppliersAndCustomerService suppliersAndCustomerService;
	
	@RequestMapping("/gotoIndex")
	public String index(){
		
		return "index";
	}
	/**
	 * 查看所有商品
	 * @param model
	 * @return
	 */
	@RequestMapping("/goods")
	public String goods(Model model){
		try {
			goodsList = systemService.findGoodsByPage(0);
			couns = systemService.findGoodsCount();
			curtPage = 1L;
			totaPage = systemService.getTotalPage();
			model.addAttribute("couns", couns);
			model.addAttribute("curtPage", curtPage);
			model.addAttribute("totaPage", totaPage);
			model.addAttribute("goodsList", goodsList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "allGoods";
	}
	
	/**
	 * 跳转到供应商资料首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotoSuppliers")
	public String forwordsuppliers(Model model){
		try {
			suppliersList = suppliersAndCustomerService.findSuppliersByPage(0);
			couns = suppliersAndCustomerService.findSuppliersCount();
			curtPage = 1L;
			totaPage = suppliersAndCustomerService.getTotalPage();
			model.addAttribute("couns", couns);
			model.addAttribute("curtPage", curtPage);
			model.addAttribute("totaPage", totaPage);
			model.addAttribute("goodsList", suppliersList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "suppliers-list";
	}
	
	/**
	 * 添加供应商
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/suppliersadd")
	public String supplierAdd(HttpServletRequest request,Model model){
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String zip = request.getParameter("zip");
		String telPhone = request.getParameter("telPhone");
		String linkMan = request.getParameter("linkMan");
		String linkTel = request.getParameter("linkTel");
		String bank = request.getParameter("bank");
		Long bankAccount = Long.parseLong(request.getParameter("bankAccount"));
		String email = request.getParameter("email");
		suppliers = new Suppliers();
		suppliers.setName(name);
		suppliers.setAddress(address);
		suppliers.setZip(zip);
		suppliers.setTelPhone(telPhone);
		suppliers.setLinkMan(linkMan);
		suppliers.setLinkTel(linkTel);
		suppliers.setBank(bank);
		suppliers.setBankAccount(bankAccount);
		suppliers.setEmail(email);
		try {
			suppliersAndCustomerService.saveSuppliers(suppliers);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		try {
			suppliersList = suppliersAndCustomerService.findSuppliersByPage(0);
			couns = suppliersAndCustomerService.findSuppliersCount();
			curtPage = 1L;
			totaPage = suppliersAndCustomerService.getTotalPage();
			model.addAttribute("couns", couns);
			model.addAttribute("curtPage", curtPage);
			model.addAttribute("totaPage", totaPage);
			model.addAttribute("goodsList", suppliersList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "suppliers-list";
	}
	
	/**
	 * 删除供应商
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/delSuppliers")
	public String delSuppliers(HttpServletRequest request,Model model){
		Long id = Long.parseLong(request.getParameter("orderId"));
		try {
			suppliersAndCustomerService.delSuppliersById(id);
			suppliersList = suppliersAndCustomerService.findSuppliersByPage(0);
			couns = suppliersAndCustomerService.findSuppliersCount();
			model.addAttribute("couns", couns);
			model.addAttribute("goodsList", suppliersList);
			return "suppliers-list";
		} catch (Exception e) {
			e.printStackTrace();
			return "suppliers-list";
		}
		
	}
	/**
	 * 跳转到客户资料首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotoCustomer")
	public String forwordCustomer(Model model){
		try {
			customerList = suppliersAndCustomerService.findCustomerByPage(0);
			couns = suppliersAndCustomerService.findCustomerCount();
			curtPage = 1L;
			totaPage = suppliersAndCustomerService.getCustomerTotalPage();
			model.addAttribute("couns", couns);
			model.addAttribute("curtPage", curtPage);
			model.addAttribute("totaPage", totaPage);
			model.addAttribute("goodsList", customerList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "customer-list";
	}
	
	/**
	 * 添加客户
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/customeradd")
	public String customerAdd(HttpServletRequest request,Model model){
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String zip = request.getParameter("zip");
		String telPhone = request.getParameter("telPhone");
		String linkMan = request.getParameter("linkMan");
		String linkTel = request.getParameter("linkTel");
		String bank = request.getParameter("bank");
		Long bankAccount = Long.parseLong(request.getParameter("bankAccount"));
		String email = request.getParameter("email");
		customer = new Customer();
		customer.setName(name);
		customer.setAddress(address);
		customer.setZip(zip);
		customer.setTelPhone(telPhone);
		customer.setLinkMan(linkMan);
		customer.setLinkTel(linkTel);
		customer.setBank(bank);
		customer.setBankAccount(bankAccount);
		customer.setEmail(email);
		try {
			suppliersAndCustomerService.saveCustomer(customer);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		try {
			customerList = suppliersAndCustomerService.findCustomerByPage(0);
			couns = suppliersAndCustomerService.findCustomerCount();
			curtPage = 1L;
			totaPage = suppliersAndCustomerService.getCustomerTotalPage();
			model.addAttribute("couns", couns);
			model.addAttribute("curtPage", curtPage);
			model.addAttribute("totaPage", totaPage);
			model.addAttribute("goodsList", customerList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "customer-list";
	}
	
	/**
	 * 删除客户
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/delCustomer")
	public String delCustomer(HttpServletRequest request,Model model){
		Long id = Long.parseLong(request.getParameter("orderId"));
		try {
			suppliersAndCustomerService.delCustomerById(id);
			customerList = suppliersAndCustomerService.findCustomerByPage(0);
			couns = suppliersAndCustomerService.findCustomerCount();
			model.addAttribute("couns", couns);
			model.addAttribute("goodsList", customerList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "customer-list";
	}
	
	/**
	 * 跳转到员工资料首页
	 * @param model
	 * @return
	 */
	@RequestMapping("/gotoEmployee")
	public String forwordEmployee(Model model){
		try {
			employeeList = suppliersAndCustomerService.findEmplyeeByPage(0);
			couns = suppliersAndCustomerService.findEmployeeCount();
			curtPage = 1L;
			totaPage = suppliersAndCustomerService.getEmployeeTotalPage();
			model.addAttribute("couns", couns);
			model.addAttribute("curtPage", curtPage);
			model.addAttribute("totaPage", totaPage);
			model.addAttribute("goodsList", employeeList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "employee-list";
	}
	
	/**
	 * 删除员工
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/delEmployee")
	public String delEmployee(HttpServletRequest request,Model model){
		Long id = Long.parseLong(request.getParameter("orderId"));
		try {
			suppliersAndCustomerService.delEmployeeById(id);
			employeeList = suppliersAndCustomerService.findEmplyeeByPage(0);
			couns = suppliersAndCustomerService.findEmployeeCount();
			model.addAttribute("couns", couns);
			model.addAttribute("goodsList", employeeList);
			return "employee-list";
		} catch (Exception e) {
			e.printStackTrace();
			return "employee-list";
		}
		
	}
	
	/**
	 * goods分页功能中 首页 尾页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipage")
	public String getPurchase(HttpServletRequest request,Model model){		
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			goodsList = systemService.findGoodsByPage((int)(pageNum-1));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		curtPage = pageNum;
		model.addAttribute("curtPage", curtPage);
		model.addAttribute("goodsList", goodsList);
		return "allGoods";
		
	}
	
	/**
	 * goods分页功能中上一页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipageDown")
	public String getPurchaseDown(HttpServletRequest request,Model model){
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			if(pageNum>1){
				goodsList = systemService.findGoodsByPage((int)(pageNum-2));
				curtPage = pageNum-1;
			}else if(pageNum == 1){
				goodsList = systemService.findGoodsByPage((int)(pageNum-1));
				curtPage = pageNum;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}		
		model.addAttribute("curtPage", curtPage);
		model.addAttribute("goodsList", goodsList);
		return "allGoods";
		
	}
	
	/**
	 * goods分页功能中下一页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipageUp")
	public String getPurchaseUp(HttpServletRequest request,Model model){
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			if(pageNum<=(totaPage-1)){
				goodsList = systemService.findGoodsByPage((int)(pageNum-0));
				curtPage = pageNum+1;
			}else if(pageNum == totaPage){
				goodsList = systemService.findGoodsByPage((int)(pageNum-1));
				curtPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("curtPage", curtPage);
		model.addAttribute("goodsList", goodsList);
		return "allGoods";
		
	}
	
	/**
	 * suppliers分页功能中 首页 尾页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipage1")
	public String getPurchase1(HttpServletRequest request,Model model){		
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			suppliersList = suppliersAndCustomerService.findSuppliersByPage((int)(pageNum-1));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		curtPage = pageNum;
		model.addAttribute("curtPage", curtPage);
		model.addAttribute("goodsList", suppliersList);
		return "suppliers-list";
		
	}
	
	/**
	 * suppliers分页功能中上一页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipageDown1")
	public String getPurchaseDown1(HttpServletRequest request,Model model){
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			if(pageNum>1){
				suppliersList = suppliersAndCustomerService.findSuppliersByPage((int)(pageNum-2));
				curtPage = pageNum-1;
			}else if(pageNum == 1){
				suppliersList = suppliersAndCustomerService.findSuppliersByPage((int)(pageNum-1));
				curtPage = pageNum;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}		
		model.addAttribute("curtPage", curtPage);
		model.addAttribute("goodsList", suppliersList);
		return "suppliers-list";
		
	}
	
	/**
	 * suppliers分页功能中下一页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipageUp1")
	public String getPurchaseUp1(HttpServletRequest request,Model model){
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			if(pageNum<=(totaPage-1)){
				suppliersList = suppliersAndCustomerService.findSuppliersByPage((int)(pageNum-0));
				curtPage = pageNum+1;
			}else if(pageNum == totaPage){
				suppliersList = suppliersAndCustomerService.findSuppliersByPage((int)(pageNum-1));
				curtPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("curtPage", curtPage);
		model.addAttribute("goodsList", suppliersList);
		return "suppliers-list";
		
	}
	
	/**
	 * customer分页功能中 首页 尾页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipage2")
	public String getPurchase2(HttpServletRequest request,Model model){		
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			customerList = suppliersAndCustomerService.findCustomerByPage((int)(pageNum-1));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		curtPage = pageNum;
		model.addAttribute("curtPage", curtPage);
		model.addAttribute("goodsList", customerList);
		return "customer-list";
		
	}
	
	/**
	 * customer分页功能中上一页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipageDown2")
	public String getPurchaseDown2(HttpServletRequest request,Model model){
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			if(pageNum>1){
				customerList = suppliersAndCustomerService.findCustomerByPage((int)(pageNum-2));
				curtPage = pageNum-1;
			}else if(pageNum == 1){
				customerList = suppliersAndCustomerService.findCustomerByPage((int)(pageNum-1));
				curtPage = pageNum;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}		
		model.addAttribute("curtPage", curtPage);
		model.addAttribute("goodsList", customerList);
		return "customer-list";
		
	}
	
	/**
	 * customer分页功能中下一页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/multipageUp2")
	public String getPurchaseUp2(HttpServletRequest request,Model model){
		Long pageNum =Long.parseLong(request.getParameter("curPage"));
		try {
			if(pageNum<=(totaPage-1)){
				customerList = suppliersAndCustomerService.findCustomerByPage((int)(pageNum-0));
				curtPage = pageNum+1;
			}else if(pageNum == totaPage){
				customerList = suppliersAndCustomerService.findCustomerByPage((int)(pageNum-1));
				curtPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("curtPage", curtPage);
		model.addAttribute("goodsList", customerList);
		return "customer-list";
		
	}
}
