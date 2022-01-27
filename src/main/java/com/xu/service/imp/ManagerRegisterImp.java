package com.xu.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xu.bean.Managers;
import com.xu.dao.ManagerRepository;
import com.xu.service.ManagerRegisterService;



@Service
public class ManagerRegisterImp implements ManagerRegisterService{

	@Resource
	private ManagerRepository managerRepository;
	@Override
	public int Register(Managers manager) {
		managerRepository.save(manager);
		return 0;
	}

}
