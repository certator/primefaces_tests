package com.example.spring;

import java.util.List;



import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.example.model.Message;
import com.example.model.UserRole;
 
 
//@Service("dbUserDetailService")
@Named(value="dbUserDetailsService")
public class DBUserDetailService implements UserDetailsService, java.io.Serializable {

	@Inject
	SessionFactory sessionFactory;
	
	public DBUserDetailService() {
//		createTestUser();
	}
	
	////http://stackoverflow.com/questions/17346679/transactional-on-postconstruct-method
    @Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;	
	
	@PostConstruct
	private void init() {
		TransactionTemplate tmpl = new TransactionTemplate(txManager);
		tmpl.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				// PUT YOUR CALL TO SERVICE HERE
				createTestUser(sessionFactory.getCurrentSession());
			}
		});
	}
	
//	@PostConstruct
//	private void init() {
//		Session session = sessionFactory.openSession();
//		createTestUser(session);
//		session.close();
//	}
	
	public void createTestUser(Session session) {
		
		com.example.model.User user = new com.example.model.User();
		user.setUsername("test");
		String password = "123";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(password));
		user.setEnabled(true);
		session.save(user);

		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUser(user);
		session.save(role);
		
//		session.close();

	}
 

	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(final String username) 
		throws UsernameNotFoundException {
 
//		User user = userDao.findByUserName(username);
		List<com.example.model.User> result_set = (List<com.example.model.User>)sessionFactory.getCurrentSession().createQuery("from User where username=?").setParameter(0, username).list();
		if (result_set.size() == 0)
			throw new UsernameNotFoundException(username);
		com.example.model.User user = result_set.get(0);
		List<GrantedAuthority> authorities = 
                                      buildUserAuthority(user.getUserRole());
 
		return buildUserForAuthentication(user, authorities);
 
	}
 
	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.example.model.User user, 
		List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(), 
			user.isEnabled(), true, true, true, authorities);
	}
 
	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
 
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
 
		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
 
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
 
		return Result;
	}
 
}