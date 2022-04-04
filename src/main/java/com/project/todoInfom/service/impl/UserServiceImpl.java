package com.project.todoInfom.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.todoInfom.dto.UserDTO;
import com.project.todoInfom.exceptions.ObjectNotFoundExecution;
import com.project.todoInfom.model.User;
import com.project.todoInfom.repository.UserRepository;
import com.project.todoInfom.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public User findById(Long id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundExecution(" O id "+id+" não foi encontrado pois não "
				+ "existe cadastro associado a este ID"));
 	}
 
	@Override
	public List<User> ListAllBase() {
 		return repository.findAll();
	}

	@Override
	public User creaet(UserDTO obj) {
 		return repository.save(mapper.map(obj, User.class));
	}
}
