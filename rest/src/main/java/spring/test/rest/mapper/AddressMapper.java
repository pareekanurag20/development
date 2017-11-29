package spring.test.rest.mapper;

import spring.test.dao.model.Address;
import spring.test.rest.model.AddressDTO;

public interface AddressMapper {

	public AddressDTO convertToRestAddress(Address address);
	
	public Address convertToDaoAddress(AddressDTO addressDTO);
}
