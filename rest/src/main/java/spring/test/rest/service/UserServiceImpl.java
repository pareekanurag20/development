package spring.test.rest.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.test.dao.model.UserEntity;
import spring.test.dao.service.UserDao;
import spring.test.rest.mapper.UserMapper;
import spring.test.rest.model.UserDTO;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
	
	private static final Logger LOGGER= LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserMapper userMapper;
	

	public List<UserDTO> findAllUsers() {
		List<UserEntity> userEntities= userDao.getAllUser();
		List<UserDTO> userDTOs= userMapper.convertListToRest(userEntities);
		
		return userDTOs;
	}
	
	public UserDTO findById(int id) {
		UserDTO userDTO=null;
		UserEntity userEntity=userDao.getUserById(id);
		if (userEntity==null) {
			LOGGER.error("user entity is null from data base for id [{}]",id);
		}else {
			userDTO=userMapper.convertToRestUser(userEntity);
		}
		return userDTO;
	}
	
	public UserDTO findByName(String name) {
		UserDTO userDTO=null;
		UserEntity userEntity=userDao.getUserByName(name);
		if (userEntity==null) {
			LOGGER.error("user entity is null from data base for name [{}]",name);
		}else {
			userDTO=userMapper.convertToRestUser(userEntity);
		}
		return userDTO;
	}
	
	public void saveUser(UserDTO user) {
		UserEntity userEntity=userMapper.covertToDaoUser(user);
		userDao.insert(userEntity);
		LOGGER.error("user persisted in data base",user);
	}

	public void updateUser(UserDTO user,int id) {
		UserEntity userEntity=userMapper.covertToDaoUser(user);
		userDao.updateUser(userEntity,id);
		LOGGER.error("user updated in data base ",user);
	}

	public void deleteUserById(int id) {
		userDao.deleteUser(id);
	}

	public boolean isUserExist(UserDTO user) {
		return findByName(user.getName())!=null;
	}

}
