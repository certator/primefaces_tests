package com.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.stereotype.Component;

//@ManagedBean(name = "testBean")
//@RequestScoped
@Component
public class TestSpringBean {

	public TestSpringBean() {
		System.out.println("Inside TestSpringBean constructor.");
	}

	public String getSpringStr() {
		return "test spring ";
	}
}
