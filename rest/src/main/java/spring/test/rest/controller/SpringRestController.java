package spring.test.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import spring.test.rest.model.UserDTO;
import spring.test.rest.service.UserService;


@RestController
public class SpringRestController {

	@Autowired
	private UserService userService;  //Service which will do all data retrieval/manipulation work

	
	//-------------------Retrieve All Users--------------------------------------------------------
	
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> listAllUsers() {
		List<UserDTO> users = userService.findAllUsers();
		if(users.isEmpty()){
			return new ResponseEntity<List<UserDTO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<UserDTO>>(users, HttpStatus.OK);
	}


	//-------------------Retrieve Single User--------------------------------------------------------
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUser(@PathVariable("id") int id) {
		System.out.println("Fetching User with id " + id);
		UserDTO user = userService.findById(id);
		if (user == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}

	
	
	//-------------------Create a User--------------------------------------------------------
	
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody UserDTO user, 	UriComponentsBuilder ucBuilder) {
		System.out.println("Creating User " + user.getName());

		if (userService.isUserExist(user)) {
			System.out.println("A User with name " + user.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	
	//------------------- Update a User --------------------------------------------------------
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<UserDTO> updateUser(@PathVariable("id") int id, @RequestBody UserDTO user) {
		System.out.println("Updating User " + id);
		
		UserDTO currentUser = userService.findById(id);
		
		if (currentUser==null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());
		
		userService.updateUser(currentUser,id);
		return new ResponseEntity<UserDTO>(currentUser, HttpStatus.OK);
	}

	//------------------- Delete a User --------------------------------------------------------
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") int id) {
		System.out.println("Fetching & Deleting User with id " + id);

		UserDTO user = userService.findById(id);
		if (user == null) {
			System.out.println("Unable to delete. User with id " + id + " not found");
			return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
		}

		userService.deleteUserById(id);
		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}

	
	//------------------- Delete All User --------------------------------------------------------
	
	@RequestMapping(value = "/user", method = RequestMethod.DELETE)
	public ResponseEntity<UserDTO> deleteAllUsers() {
		System.out.println("Deleting All Users");

		
		return new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT);
	}

}
