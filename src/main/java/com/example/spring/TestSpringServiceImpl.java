package com.example.spring;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Message;

@Named

public class TestSpringServiceImpl implements TestSpringService,
		java.io.Serializable {

	@Inject
	SessionFactory sessionFactory;

	public TestSpringServiceImpl() {
		System.out.println("init TestSpringServiceImpl");
	}

	@Transactional
	public String getSpringStr() {
		Message m = new Message();
		m.setName("name1");
		m.setText("text1");
		
		System.out.println("sf: ");
		System.out.println(sessionFactory);
		sessionFactory.getCurrentSession().save(m);
		for (Object it: sessionFactory.getCurrentSession().createQuery("from Message").list())
		{
			System.out.println(it);

		}
		
		

		return " test spring ";
	}
}
