package spring.test.rest.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import spring.test.dao.model.PhoneNumber;
import spring.test.dao.model.UserEntity;
import spring.test.rest.model.PhoneNumberDTO;

@Service
public class PhoneNumberMapperImp implements PhoneNumberMapper {

	public PhoneNumberDTO convertToRestPhoneNumber(PhoneNumber phoneNumber) {
		PhoneNumberDTO phoneNumberDTO= new PhoneNumberDTO();
		phoneNumberDTO.setPhoneNumber(phoneNumber.getPhoneNumber());
		phoneNumberDTO.setPhoneType(phoneNumber.getPhoneType());
		return phoneNumberDTO;
	}

	public PhoneNumber convertToDaoPhoneNumber(PhoneNumberDTO phoneNumberDTO,UserEntity userEntity) {
		PhoneNumber phoneNumber= new PhoneNumber();
		phoneNumber.setPhoneNumber(phoneNumberDTO.getPhoneNumber());
		phoneNumber.setPhoneType(phoneNumberDTO.getPhoneType());
		phoneNumber.setUserEntity(userEntity);
		return phoneNumber;
	}

	public List<PhoneNumber> convertToDaoPhoneNumbers(List<PhoneNumberDTO> phoneNumberDTOs,UserEntity userEntity) {
		List<PhoneNumber> phoneNumbers= null;
		if (phoneNumberDTOs!=null && phoneNumberDTOs.size()>0) {
			phoneNumbers= new ArrayList<PhoneNumber>();
			for (PhoneNumberDTO phoneNumberDTO : phoneNumberDTOs) {
				phoneNumbers.add(convertToDaoPhoneNumber(phoneNumberDTO, userEntity));
			}
		}
		return phoneNumbers;
	}

	public List<PhoneNumberDTO> convertToRestPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		List<PhoneNumberDTO> phoneNumberDTOs= null;
		if (phoneNumbers!=null&&phoneNumbers.size()>0) {
			phoneNumberDTOs= new ArrayList<PhoneNumberDTO>();
			for (PhoneNumber phoneNumber : phoneNumbers) {
				phoneNumberDTOs.add(convertToRestPhoneNumber(phoneNumber));
			}
		}
		return phoneNumberDTOs;
	}

}
