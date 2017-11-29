package spring.test.rest.mapper;

import java.util.List;

import spring.test.dao.model.PhoneNumber;
import spring.test.dao.model.UserEntity;
import spring.test.rest.model.PhoneNumberDTO;

public interface PhoneNumberMapper {

	public PhoneNumberDTO convertToRestPhoneNumber(PhoneNumber phoneNumber);
	
	public PhoneNumber convertToDaoPhoneNumber(PhoneNumberDTO phoneNumberDTO,UserEntity userEntity);
	
	public List<PhoneNumber> convertToDaoPhoneNumbers(List<PhoneNumberDTO>phoneNumberDTOs,UserEntity userEntity);
	
	public List<PhoneNumberDTO> convertToRestPhoneNumbers(List<PhoneNumber> phoneNumbers);
}
