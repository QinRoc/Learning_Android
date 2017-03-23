package com.roc.homework_day_08_pull_parse_sqlite;

import java.io.Serializable;

public class CustomerBean  implements Serializable{

	private String id;//逻辑主键
	private String name;
	private String gender;//数据库中：1 男 0 女
	private String birthday;
	private String cellphone;//电话号码
	private String email;//邮箱
	private String hobby;//爱好： 吃饭,睡觉,学java
	private String type;//客户类型：普通客户 VIP
	private String description;//简介

	public CustomerBean() {
		super();
	}

	public CustomerBean(String id, String name, String gender, String birthday,
					String cellphone, String email, String hobby, String type,
					String description) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.cellphone = cellphone;
		this.email = email;
		this.hobby = hobby;
		this.type = type;
		this.description = description;
	}



	@Override
	public String toString() {
		return "CustomerBean [id=" + id + ", name=" + name + ", gender=" + gender
				+ ", birthday=" + birthday + ", cellphone=" + cellphone
				+ ", email=" + email + ", hobby=" + hobby + ", type=" + type
				+ ", description=" + description + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
