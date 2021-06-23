package com.bmi.sparta.resources;

import java.util.List;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bmi.sparta.models.UserDTO;
import com.bmi.sparta.models.UserDTOsList;
import com.bmi.sparta.services.UsersService;
import com.sun.istack.Nullable;

import javassist.expr.NewArray;


@RestController
public class UsersResource {

	org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UsersService usersService;

	@GetMapping("/users/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") String id) {
		Integer userId = null;
		try {
			userId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return new ResponseEntity<>("id:" + id + "is not a number",HttpStatus.BAD_REQUEST);
		}
		logger.info(" find user for user id " + id);
		UserDTO userDto = usersService.findById(userId);
		if (null != userDto) {
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		}
		logger.debug(" No User found for user id: " + id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/users")
	public ResponseEntity<UserDTOsList> findAll() {
		UserDTOsList userDtos = usersService.findAll();
		if (userDtos.getCount() > 0) {
			return new ResponseEntity<UserDTOsList>(userDtos, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("/user")
	private ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
		return new ResponseEntity<UserDTO>(usersService.save(userDTO), HttpStatus.CREATED);
	}

	@PostMapping("/users")
	private ResponseEntity<UserDTOsList> saveAll(@RequestBody List<UserDTO> userDTOs) {
		return new ResponseEntity<UserDTOsList>(usersService.saveAll(userDTOs), HttpStatus.CREATED);

	}

	@PutMapping("/users/{id}")
	private ResponseEntity<?> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
		
		Integer userId = null;
		try {
			userId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return new ResponseEntity<>("id:" + id + "is not a number",HttpStatus.BAD_REQUEST);
		}
		
		UserDTO userDto = usersService.update(userId, userDTO);
		if (null != userDto) {
			return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserDTO>(userDto, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/users/{id}")
	private ResponseEntity<?> deleteById(@PathVariable String id) {
		
		Integer userId = null;
		try {
			userId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return new ResponseEntity<>("id:" + id + "is not a number",HttpStatus.BAD_REQUEST);
		}
		
		boolean exists = usersService.existsById(userId);
		if(exists) {
			usersService.deleteById(userId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
