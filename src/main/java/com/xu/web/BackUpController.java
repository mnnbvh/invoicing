package com.xu.web;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.xu.util.DataBaseBackUp;

@Controller
@RequestMapping("/backups")
@SessionAttributes(value={})
public class BackUpController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 此方法用于备份
	 * @param model
	 * @return
	 */
	@Autowired
	private DataBaseBackUp dataBaseBackUp;
	@RequestMapping("/backupes")
	public String dbBackUp(Model model){
		logger.info("开始备份");
		dataBaseBackUp.backup();
		model.addAttribute("msg","数据备份成功");
		logger.info("备份完成");
		return "backup";
	}
	
	/**
	 * 此方法用于恢复数据
	 */
	@RequestMapping("/restore")
	public String dbRestore(HttpServletRequest request,Model model){
		logger.info("开始数据恢复");
		String fileName = request.getParameter("fileName");
		dataBaseBackUp.restore(fileName);
		model.addAttribute("msg","数据恢复成功");
		logger.info("数据恢复完成");
		return "backup";
	}
	
	@RequestMapping("/gotuBackup")
	public String forwordBackUp(){
		
		return "backup";
	}
}
