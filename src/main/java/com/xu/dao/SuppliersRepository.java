package com.xu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xu.bean.Suppliers;

public interface SuppliersRepository extends JpaRepository<Suppliers,Long>{

	@Query("from Suppliers where supplier_name = ?1")
	public Suppliers findSuppliersByName(String name);

}
