package com.xu.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xu.bean.Employee;
import com.xu.bean.Managers;
import com.xu.bean.Message;
import com.xu.exception.ServiceException;
import com.xu.service.ManagerService;

@Controller
@RequestMapping("/log")
@SessionAttributes(value={"messageList","message","cunt","crrentPage","ttalPage"})
public class LoginAndLogOutController {

	@Resource
	private ManagerService managerService;
	private Long cunt;
	private Long crrentPage;
	private Long ttalPage;
	private List<Message> messageList = new ArrayList<Message>();
	
	
	@RequestMapping("/in")
	public String forwordIn(){
		
		return "login";
		
	}
	
	@RequestMapping("/checkMessage")
	public String seeMessage(HttpServletRequest request,Model model){
		Long id = Long.parseLong(request.getParameter("id"));
		try {
			Message message = managerService.findMessageById(id);
			managerService.updateEmployeeById(id);
			model.addAttribute("message",message);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "seemessage";
	}
	
	@RequestMapping("/out")
	public String forwordOut(HttpServletRequest req){
		ServletContext app = req.getSession().getServletContext();
		app.removeAttribute("user");
		return "login";
	}
	
	@RequestMapping("/gotoInformation")
	public String forwordInformation(){		
		
		return "information";
	}
	
	/**
	 * 获取所有消息
	 * @param model
	 * @return
	 */
	@RequestMapping("/getMessage")
	public String getAllMessage(Model model){
		try {
			messageList = managerService.findAllMessage();
			cunt = managerService.findMessageCounts();
			crrentPage = 1L;
			ttalPage = managerService.getMessageTotalPage();
			model.addAttribute("crrentPage", crrentPage);
			model.addAttribute("ttalPage", ttalPage);
			model.addAttribute("messageList", messageList);
			model.addAttribute("cunt", cunt);
			model.addAttribute("messageList", messageList);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		return "message";
	}
	
	/**
	 * 修改个人信息
	 * @param req
	 * @return
	 */
	@RequestMapping("/updatInformation")
	public String updateInformation(HttpServletRequest req){
		String gender = req.getParameter("gender");
		Long age = Long.parseLong(req.getParameter("age"));
		String phonenumber = req.getParameter("phonenumber");
		ServletContext app = req.getSession().getServletContext();
		Object attribute = app.getAttribute("user");
		HttpSession session = req.getSession();
		if(attribute instanceof Employee){
			((Employee) attribute).setGender(gender);
			((Employee) attribute).setAge(age);
			((Employee) attribute).setPhonenumber(phonenumber);
			try {
				Employee employee = managerService.updateEmplyeeByName((Employee)attribute);
				app.setAttribute("user",employee);
				session.setAttribute("mess", "信息成功修改");
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			return "information";
		}else if(attribute instanceof Managers){
			((Managers) attribute).setAge(age);
			((Managers) attribute).setGender(gender);
			((Managers) attribute).setPhonenumber(phonenumber);
			try {
				Managers managers = managerService.updateManagersByName((Managers) attribute);
				app.setAttribute("user",managers);
				session.setAttribute("mess", "信息成功修改");
			} catch (ServiceException e) {
				e.printStackTrace();
			}
			return "information";
		}else{
			session.setAttribute("mess", "信息成功失败，请重新输入");
			return "information";
		}
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
			messageList = managerService.findMessageByPage((int)(pageNum-1));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		crrentPage = pageNum;
		model.addAttribute("currentPage", crrentPage);
		model.addAttribute("messageList", messageList);
		return "message";
		
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
				messageList = managerService.findMessageByPage((int)(pageNum-2));
				crrentPage = pageNum-1;
			}else if(pageNum == 1){
				messageList = managerService.findMessageByPage((int)(pageNum-1));
				crrentPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		model.addAttribute("currentPage", crrentPage);
		model.addAttribute("messageList", messageList);
		return "message";
		
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
				messageList = managerService.findMessageByPage((int)(pageNum-0));
				crrentPage = pageNum+1;
			}else if(pageNum == ttalPage){
				messageList = managerService.findMessageByPage((int)(pageNum-1));
				crrentPage = pageNum;
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("currentPage", crrentPage);
		model.addAttribute("messageList", messageList);
		return "message";
		
	}
	
}
