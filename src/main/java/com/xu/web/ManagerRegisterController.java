package com.xu.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xu.bean.Employee;
import com.xu.bean.Managers;
import com.xu.exception.ServiceException;
import com.xu.service.ManagerService;

@Controller
@RequestMapping("/manager")
public class ManagerRegisterController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private ManagerService managerRegisterService;
	
	private Managers manager;
	private Employee employee;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public @ResponseBody int data(HttpServletRequest request,HttpServletResponse response) {
		String user = request.getParameter("user");
		if("manager".equals(user)){
			logger.info("开始注册");
			manager = new Managers();
			manager.setName(request.getParameter("name"));
			manager.setPassword(request.getParameter("password"));
			manager.setGender(request.getParameter("gender"));
			manager.setAge(Long.parseLong(request.getParameter("age")));
			manager.setPhonenumber(request.getParameter("phonenumber"));
			try {
				logger.info("注册成功");
				return managerRegisterService.register(manager);
				
			} catch (ServiceException e) {
				e.printStackTrace();
				return 1;
			}
		}else if("employee".equals(user)){
			logger.info("开始注册");
			employee = new Employee();
			employee.setName(request.getParameter("name"));
			employee.setPassword(request.getParameter("password"));
			employee.setGender(request.getParameter("gender"));
			employee.setAge(Long.parseLong(request.getParameter("age")));
			employee.setPhonenumber(request.getParameter("phonenumber"));
			try {
				logger.info("注册成功");
				return managerRegisterService.employeeregister(employee);
				
			} catch (ServiceException e) {
				e.printStackTrace();
				return 1;
			}
		}
		return 1;
	}
	
	@RequestMapping(value="/pur")
	public String turn(){
		
		return "article-list";
	}
}
