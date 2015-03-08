package com.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "testBean")
@RequestScoped
public class TestBean {
	public String getTestStr() {
		return "test string";
	}
}
