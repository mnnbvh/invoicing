package com.xu.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * 
 * @author XKS22
 *管理员
 *id:管理员编号
 *name:管理员名字
 *password:登录密码
 *gender:性别
 *age:年龄
 *phone number:手机号码
 *deptId:部门id
 */
@Entity
@Table(name = "managers")
@Component
public class Managers {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="managers_id")
	private Long id;
	@Column(name="managers_name")
	private String name;
	@Column(name="managers_password")
	private String password;
	@Column(name="managers_gender")
	private String gender;
	@Column(name="managers_age")
	private Long age;
	@Column(name="managers_phonenumber")
	private String phonenumber;
	/*@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,optional=false,targetEntity=Dept.class)
	@JoinColumn(name="dept_name")
	private String deptName;*/
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	/*public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}*/
	
	
}
