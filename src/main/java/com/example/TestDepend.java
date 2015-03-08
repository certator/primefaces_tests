package com.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "testDepend")
@RequestScoped
public class TestDepend {
	public String getDependStr() {
		return "test depend";
	}
}
