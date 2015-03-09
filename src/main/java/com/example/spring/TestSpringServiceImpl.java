package com.example.spring;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Message;

@Named
public class TestSpringServiceImpl implements TestSpringService,
		java.io.Serializable {
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public TestSpringServiceImpl() {
		System.out.println("init TestSpringServiceImpl");
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public String getSpringStr() {
		Message m = new Message();
		m.setName("name1");
		m.setText("text1");
		entityManager.persist(m);

		return " test spring ";
	}
}
