package com.example.jsf;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Message;
import com.example.spring.TestSpringService;

import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.springframework.transaction.annotation.Propagation;


@Named
//@Scope("session") //need this, JSR-330 in Spring context is singleton by default
@ViewScoped
public class TestBean implements java.io.Serializable {
	public TestBean() {
		System.out.println("init TestBean");
	}

	@Inject
	private TestDepend testDepend;

	@Inject
	private TestSpringService testSpringBean;
	
	public TestSpringService getTestSpringBean() {
		return testSpringBean;
	}

	private String out_str = "s ";

	private List<Message> messages;

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

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
    	getTestStr();
        out_str += " a ";
        
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("test");
//        EntityManager em = factory.createEntityManager();
//        em.getTransaction().begin();
//        Message m = new Message();
//        m.setName("name1");
//        m.setText("text1");
//        em.persist(m);
//        em.getTransaction().commit();
        
    }
	
}
