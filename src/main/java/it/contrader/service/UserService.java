package it.contrader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.contrader.converter.UserConverter;
import it.contrader.dao.UserRepository;
import it.contrader.dto.UserDTO;
import it.contrader.model.User;

/**
 * Estende AbstractService con parametri User e UserDTO. 
 * Implementa il metodo di login ed eredita quelli Abstract. 
 * 
 * @author Vittorio Valent & Girolamo Murdaca
 * 
 * @see AbstractService
 * @see ServiceDTO
 */
@Service
public class UserService extends AbstractService<User,UserDTO> {
	
	//ALL crud methods in AbstractService
	
	@Autowired
	private UserConverter converter;
	@Autowired
	private UserRepository crudRepository;
	
	//LOGIN method
	public UserDTO findByUsernameAndPassword(String username, String password) {
		return converter.toDTO(crudRepository.findByUsernameAndPassword(username, password));
	}

}
