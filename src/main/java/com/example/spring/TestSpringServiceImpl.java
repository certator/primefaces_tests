package com.example.spring;

import java.util.List;

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
		
		try {
			getMessageById(1L);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		return " test spring ";
	}
	
	@Transactional
	public List<Message> getMessages()
	{
		return (List<Message>)sessionFactory.getCurrentSession().createQuery("from Message").list();
		
	}
	
	public Message getMessageById(Long id)
	{
		return (Message) sessionFactory.getCurrentSession().get(Message.class, id);
	}

	@Override
	public Long saveMessage(Message m) {
		sessionFactory.getCurrentSession().save(m);
		return m.getId();
	}
	
}
