package com.bmi.sparta.resources;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
			return ResponseEntity.badRequest().body("id:" + id + "is not a number");
		}
		logger.info(" find user for user id " + id);
		UserDTO userDto = usersService.findById(userId);
		if (null != userDto) {
			return ResponseEntity.ok(userDto);
		}
		logger.debug(" No User found for user id: " + id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/users")
	public ResponseEntity<UserDTOsList> findAll() {
		UserDTOsList userDtos = usersService.findAll();
		if (userDtos.getCount() > 0) {
			return ResponseEntity.ok(userDtos);
		}
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/user")
	private ResponseEntity<?> save(@RequestBody UserDTO userDTO) {
		UserDTO savedUser=usersService.save(userDTO);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}

	@PostMapping("/users")
	private ResponseEntity<UserDTOsList> saveAll(@RequestBody List<UserDTO> userDTOs) {
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		usersService.saveAll(userDTOs);
		return ResponseEntity.created(location).build();
		
	}

	@PutMapping("/users/{id}")
	private ResponseEntity<?> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
		
		Integer userId = null;
		try {
			userId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("id:" + id + "is not a number");
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
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
