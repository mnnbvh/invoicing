
package com.xu.web;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xu.bean.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import com.xu.bean.Employee;
import com.xu.bean.Managers;
import com.xu.exception.ServiceException;
import com.xu.service.ManagerService;

@RestController
@WebServlet(urlPatterns="/managerlogin")
public class LoginServlet extends HttpServlet{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final long serialVersionUID = 1L;
	@Resource
	private ManagerService managerService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("username");
		String password = req.getParameter("password");
		String user = req.getParameter("user");

		if("manager".equals(user)){
			Managers managers = null;
			try {
				managers = managerService.login(name, password);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			if(managers!=null){			
				logger.info(name+"登录成功！！！");
				ServletContext app = req.getSession().getServletContext();
				Long count = 0L;
				try {
					count = managerService.findMessageCount();
				} catch (ServiceException e) {
					
					e.printStackTrace();
				}
				app.setAttribute("messageCount", count);
				app.setAttribute("user",managers);
				app.setAttribute("role",Role.Manager.value());
				req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
			}else{
				logger.info("登录失败，用户名或者密码错误");
				HttpSession session = req.getSession();
				session.setAttribute("msg","用户名或者密码错误");
				req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
			}
		}else if("employee".equals(user)){
			Employee employee = null;
			try {
				employee = managerService.employeelogin(name, password);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			if(employee!=null){			
				logger.info(name+"登录成功！！！");
				ServletContext app = req.getSession().getServletContext();
				try {
					Long count = managerService.findMessageCount();
					app.setAttribute("messageCount", count);
					app.setAttribute("user",employee);
					app.setAttribute("role",Role.Employee.value());
					req.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(req, resp);
				} catch (ServiceException e) {
					e.printStackTrace();
				}
			}else{
				logger.info("登录失败，用户名或者密码错误");
				HttpSession session = req.getSession();
				session.setAttribute("msg","用户名或者密码错误");
				req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
			}
		}else{
			logger.info("登录失败，没有选择用户类型");
			HttpSession session = req.getSession();
			session.setAttribute("msg","请选择用户类型");
			req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}
