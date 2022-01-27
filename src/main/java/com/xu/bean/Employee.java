package com.xu.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author XKS22
 *  员工
 *id：
 *name:姓名
 *password:密码
 *age:年龄
 *phone number:手机号码
 *deptId:部门ID
 *gender:性别
 */
@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="employee_id")
	private Long id;
	@Column(name="employee_name")
	private String name;
	@Column(name="employee_password")
	private String password;
	@Column(name="employee_gender")
	private String gender;
	@Column(name="employee_age")
	private Long age;
	@Column(name="employee_phonenumber")
	private String phonenumber;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Dept.class)
	@JoinColumn(name="dept_id")
	private Long deptId;*/
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	
}
