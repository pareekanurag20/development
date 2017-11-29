package spring.test.rest.mapper;

import org.springframework.stereotype.Service;

import spring.test.dao.model.Address;
import spring.test.rest.model.AddressDTO;

@Service
public class AddressMapperImpl implements AddressMapper {

	public AddressDTO convertToRestAddress(Address address) {
		AddressDTO addressDTO= new AddressDTO();
		addressDTO.setAddressId(address.getAddressId());
		addressDTO.setCity(address.getCity());
		addressDTO.setCountry(address.getCountry());
		addressDTO.setHouseNumber(address.getHouseNumber());
		addressDTO.setPostCode(address.getPostCode());
		addressDTO.setStreatName(address.getStreatName());
		return addressDTO;
	}

	public Address convertToDaoAddress(AddressDTO addressDTO) {
		Address address= new Address();
		address.setAddressId(addressDTO.getAddressId());
		address.setCity(addressDTO.getCity());
		address.setCountry(addressDTO.getCountry());
		address.setHouseNumber(addressDTO.getHouseNumber());
		address.setPostCode(addressDTO.getPostCode());
		address.setStreatName(addressDTO.getStreatName());
		return address;
	}

}
