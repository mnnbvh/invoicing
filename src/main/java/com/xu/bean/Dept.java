package com.xu.bean;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author 15170863661
 *	部门
 */
@Entity
@Table(name="dept")
public class Dept {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="dept_id")
	private Long id;
	@Column(name="dept_name")
	private String name;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Employee.class)
	private Set<Employee> emp;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,targetEntity=Managers.class)
	private Set<Managers> man;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Employee> getEmp() {
		return emp;
	}
	public void setEmp(Set<Employee> emp) {
		this.emp = emp;
	}
	public Set<Managers> getMan() {
		return man;
	}
	public void setMan(Set<Managers> man) {
		this.man = man;
	}
	
	
}
