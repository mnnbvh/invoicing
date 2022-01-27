package com.xu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xu.bean.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Long>{

	@Query("from Customer where customer_name = ?1")
	public Customer findCustomerByName(String name);

}
