package com.example.rest;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Message;
import com.example.spring.TestSpringService;


@Named
@RestController
public class TestRest {
	
	public TestRest() {
		System.out.println("init TestRest");

	}

	@Inject
	private TestSpringService testSpringBean;

    @Transactional
	@RequestMapping(value="/get_msg/{id}", method = RequestMethod.GET)
    public @ResponseBody Message get_msg(@PathVariable Long id) {
        return testSpringBean.getMessageById(id);
    }
	
}
