package com.project.todoInfom.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.todoInfom.dto.UserDTO;
import com.project.todoInfom.exceptions.ObjectNotFoundExecution;
import com.project.todoInfom.model.User;
import com.project.todoInfom.repository.UserRepository;

@SpringBootTest
public class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repository;

	@Mock
	private ModelMapper mapper;

	private User user;
	private UserDTO userDto;
	private Optional<User> optionalUser;

	Long ID = 1L;
	String NAME = "Orochimaro";
	String EMAIL = "tioOrochimaru@gmai.com";
	String PASSWORD = "123";
	String ErrorReturnMesaage = "O id "+ID+" não foi encontrado pois não "
			+ "existe cadastro associado a este ID";

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	@DisplayName("Teste de listagem de usuario por id")
	void whenFindByIdThenReturnUserInstance() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUser);
		User response = service.findById(ID);
		
		assertNotNull(response);
		
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NAME, response.getNome());
		assertEquals(EMAIL, response.getEmail());

	}
	
	
	@Test
	@DisplayName("Teste de falha de listagem de usuario por ID ")
	void WhenFindByIdThenReturnAnObjectNotFundException() {
		Mockito.when(repository.findById(anyLong())).thenThrow(new ObjectNotFoundExecution(ErrorReturnMesaage));	
		
		try {
			service.findById(ID);
		} catch (Exception ex) {
			assertEquals(ObjectNotFoundExecution.class, ex.getClass());
			assertEquals(ErrorReturnMesaage, ex.getMessage());
 		}
		
	}

	@Test
	@DisplayName("Testando listagem de todos os usuarios")
	void findAll() {
       Mockito.when(repository.findAll()).thenReturn(List.of(user));
       
       List<User> response = service.ListAllBase();
       
       assertNotNull(response);
       assertEquals(1L, response.size());
       int INDEXInput = 0;
	assertEquals(User.class, response.get(INDEXInput).getClass());
       
       assertEquals(ID, response.get(INDEXInput).getId());
       assertEquals(NAME, response.get(INDEXInput).getNome());
       assertEquals(EMAIL, response.get(INDEXInput).getEmail());
       assertEquals(PASSWORD, response.get(INDEXInput).getPassword());


	}

	@Test
	void create() {

	}

	@Test
	void update() {

	}

	@Test
	void delete() {

	}

	private void startUser() {
		user = new User(ID, NAME, EMAIL, PASSWORD);
		userDto = new UserDTO(ID, NAME, EMAIL, PASSWORD);
		optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));

	}

}
