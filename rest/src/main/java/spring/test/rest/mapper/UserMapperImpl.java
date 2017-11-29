package spring.test.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.test.dao.model.Address;
import spring.test.dao.model.PhoneNumber;
import spring.test.dao.model.UserEntity;
import spring.test.rest.model.AddressDTO;
import spring.test.rest.model.PhoneNumberDTO;
import spring.test.rest.model.UserDTO;


@Service
public class UserMapperImpl implements UserMapper{
	
	@Autowired
	private AddressMapper addressMapper;

	@Autowired
	private PhoneNumberMapper phoneNumberMapper;
	
	public UserEntity covertToDaoUser(UserDTO userDTO) {
		UserEntity daoUser= new UserEntity();
		daoUser.setAge(userDTO.getAge());
		daoUser.setId(userDTO.getId());
		daoUser.setName(userDTO.getName());
		daoUser.setSalary(userDTO.getSalary());
		List<PhoneNumber> phoneNumbers= phoneNumberMapper.convertToDaoPhoneNumbers(userDTO.getPhoneNumbers(), daoUser);
		Address address=addressMapper.convertToDaoAddress(userDTO.getAddress());
		daoUser.setAddress(address);
		daoUser.setPhoneNumbers(phoneNumbers);
		return daoUser;
	}

	public UserDTO convertToRestUser(UserEntity daoUser) {
		UserDTO userDTO= new UserDTO();
		userDTO.setAge(daoUser.getAge());
		userDTO.setId(daoUser.getId());
		userDTO.setName(daoUser.getName());
		userDTO.setSalary(daoUser.getSalary());
		AddressDTO addressDTO=addressMapper.convertToRestAddress(daoUser.getAddress());
		List<PhoneNumberDTO> phoneNumberDTOs= phoneNumberMapper.convertToRestPhoneNumbers(daoUser.getPhoneNumbers());
		userDTO.setPhoneNumbers(phoneNumberDTOs);
		userDTO.setAddress(addressDTO);
		return userDTO;
	}

	public List<UserEntity> convertListToDao(List<UserDTO> users) {
		List<UserEntity> daoUsers= null;
		if (users!=null && users.size()>0) {
			daoUsers= new ArrayList<UserEntity>();
			for (UserDTO user : users) {
				daoUsers.add(covertToDaoUser(user));
			}
		}
		return daoUsers;
	}

	public List<UserDTO> convertListToRest(List<UserEntity> users) {
		List<UserDTO> restUsers= null;
		if (users!=null && users.size()>0) {
			 restUsers= new ArrayList<UserDTO>();
			for (UserEntity user : users) {
				restUsers.add(convertToRestUser(user));
			}
		}
		
		return restUsers;
	}

}
