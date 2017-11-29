package spring.test.rest.service;

import java.util.List;

import spring.test.rest.model.UserDTO;




public interface UserService {
	
	UserDTO findById(int id);
	
	UserDTO findByName(String name);
	
	void saveUser(UserDTO user);
	
	void updateUser(UserDTO user,int id);
	
	void deleteUserById(int id);

	List<UserDTO> findAllUsers(); 
		
	public boolean isUserExist(UserDTO user);
	
}
