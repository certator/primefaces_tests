package com.example.spring;

import javax.faces.bean.ManagedBean;

import org.springframework.web.context.WebApplicationContext;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Named
public class TestSpringServiceImpl implements TestSpringService {

	public TestSpringServiceImpl() {
		System.out.println("init TestSpringServiceImpl");
	}

	public String getSpringStr() {
		return " test spring ";
	}
}
