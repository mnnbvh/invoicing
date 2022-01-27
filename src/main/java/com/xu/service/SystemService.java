package com.xu.service;

import java.util.List;

import com.xu.bean.Goods;
import com.xu.exception.ServiceException;

public interface SystemService {

	public List<Goods> findGoodsByPage(int i)throws ServiceException;

	public Long findGoodsCount()throws ServiceException;

	public Long getTotalPage()throws ServiceException;


}
