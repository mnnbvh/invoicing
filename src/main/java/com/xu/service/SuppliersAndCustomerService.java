package com.xu.service;

import java.util.List;

import com.xu.bean.Customer;
import com.xu.bean.Employee;
import com.xu.bean.Suppliers;
import com.xu.exception.ServiceException;

public interface SuppliersAndCustomerService {

	public int saveSuppliers(Suppliers suppliers)throws ServiceException;

	public List<Suppliers> findSuppliersByPage(int i)throws ServiceException;

	public Long findSuppliersCount()throws ServiceException;

	public Long getTotalPage()throws ServiceException;

	public int delSuppliersById(Long id)throws ServiceException;

	public List<Customer> findCustomerByPage(int i)throws ServiceException;

	public Long findCustomerCount()throws ServiceException;

	public Long getCustomerTotalPage()throws ServiceException;

	public int saveCustomer(Customer customer)throws ServiceException;

	public int delCustomerById(Long id)throws ServiceException;

	public List<Employee> findEmplyeeByPage(int i)throws ServiceException;

	public Long findEmployeeCount()throws ServiceException;

	public Long getEmployeeTotalPage()throws ServiceException;

	public int delEmployeeById(Long id)throws ServiceException;


}
