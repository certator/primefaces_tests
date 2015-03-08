package com.example;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ManagedBean(name = "testBean")
@RequestScoped
public class TestBean {

	@ManagedProperty(value = "#{testDepend}")
	private TestDepend testDepend;

	@Inject
	private TestSpringBean testSpringBean;

	public TestSpringBean getTestSpringBean() {
		return testSpringBean;
	}

//	@Autowired
	public void setTestSpringBean(TestSpringBean testSpringBean) {
		this.testSpringBean = testSpringBean;
	}

	public TestDepend getTestDepend() {
		return testDepend;
	}

	public void setTestDepend(TestDepend testDepend) {
		this.testDepend = testDepend;
	}

	public String getTestStr() {
		return "test string " + testDepend.getDependStr()
				+ testSpringBean.getSpringStr();
	}
}
