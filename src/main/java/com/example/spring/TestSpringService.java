package com.example.spring;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.web.context.WebApplicationContext;

import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.model.Message;

public interface TestSpringService {

	public String getSpringStr();
	public List<Message> getMessages();
	public Message getMessageById(Long id);
	public Long saveMessage(Message m);
	
}
