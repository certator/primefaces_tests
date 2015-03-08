package com.example.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.example.spring.TestSpringService;

import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;


@Named
@Scope("session") //need this, JSR-330 in Spring context is singleton by default

public class TestBean implements java.io.Serializable {
	public TestBean() {
		System.out.println("init TestBean");
	}

	@Inject
	private TestDepend testDepend;

	@Inject
	private TestSpringService testSpringBean;
	
	private String out_str = "s ";


	public String getOut_str() {
		return out_str;
	}

	public void setOut_str(String out_str) {
		this.out_str = out_str;
	}

	public void setTestSpringBean(TestSpringService testSpringBean) {
		this.testSpringBean = testSpringBean;
	}

	public TestDepend getTestDepend() {
		return testDepend;
	}

	public void setTestDepend(TestDepend testDepend) {
		this.testDepend = testDepend;
	}

	public String getTestStr() {
		if (testDepend == null)
			throw new RuntimeException("testDepend is null");
		if (testSpringBean == null)
			throw new RuntimeException("testSpringBean is null");
		return " test string " + testDepend.getDependStr()
				+ testSpringBean.getSpringStr();
	}
	
    public void buttonAction(ActionEvent actionEvent) {
    	System.out.println("buttonAction");
        out_str += " a ";
    }
	
}
