package br.com.sebastiao.junior.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sebastiao.junior.infrastructure.service.CRUDServiceImpl;
import br.com.sebastiao.junior.jpa.entity.User;
import br.com.sebastiao.junior.jpa.repository.UserRepository;
import br.com.sebastiao.junior.service.UserService;

@Service
public class UserServiceImpl extends CRUDServiceImpl<User, UserRepository, Integer> implements UserService {

	@Autowired
	public UserServiceImpl(UserRepository repository) {
		super(repository);
		this.beforeInsert(this::addCreationDate);
	}
	
	private void addCreationDate(User user) {
		user.setCreatedAt(new Date());
	}
}
