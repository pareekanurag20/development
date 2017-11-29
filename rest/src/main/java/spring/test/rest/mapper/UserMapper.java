package spring.test.rest.mapper;

import java.util.List;

import spring.test.dao.model.UserEntity;
import spring.test.rest.model.UserDTO;


public interface UserMapper {

	public UserEntity covertToDaoUser(UserDTO userDTO);
	public UserDTO convertToRestUser(UserEntity userEntity);
	public List<UserEntity> convertListToDao(List<UserDTO> userDTOs);
	public List<UserDTO> convertListToRest(List<UserEntity> userEntities);
}
