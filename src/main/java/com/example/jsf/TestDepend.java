package com.example.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Named
@Scope("session") //need this, JSR-330 in Spring context is singleton by default

public class TestDepend implements java.io.Serializable {
	public TestDepend() {
		System.out.println("init TestDepend");
	}
	public String getDependStr() {
		return " test depend ";
	}
}
