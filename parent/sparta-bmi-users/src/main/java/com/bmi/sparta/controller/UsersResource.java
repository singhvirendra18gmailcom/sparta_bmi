package com.bmi.sparta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
public class UsersResource {

	@Autowired
	private UsersService usersService;

	@GetMapping("/users/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		UserDTO userDto = usersService.findById(Integer.parseInt(id));
		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<UserDTOsList> findAll() {
		UserDTOsList userDtos = usersService.findAll();
		return new ResponseEntity<UserDTOsList>(userDtos, HttpStatus.OK);
	}

	@PostMapping("/users")
	private ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO) {
		return new ResponseEntity<UserDTO>(usersService.save(userDTO), HttpStatus.CREATED);
	}

	@PostMapping("/users")
	private ResponseEntity<UserDTOsList> saveAll(@RequestBody List<UserDTO> userDTOs) {
		return new ResponseEntity<UserDTOsList>(usersService.saveAll(userDTOs), HttpStatus.CREATED);

	}

	@PutMapping("/users/{id}")
	private ResponseEntity<UserDTO> update(@RequestBody UserDTO userDTO, @PathVariable String id) {
		UserDTO userDto = usersService.update(Integer.parseInt(id), userDTO);
		if (null != userDto) {
			return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserDTO>(userDto, HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/users/{id}")
	private ResponseEntity<UserDTO> deleteById(@PathVariable String id) {
		UserDTO userDto = usersService.deleteById(Integer.parseInt(id));		
		if (null != userDto) {
			return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserDTO>(userDto, HttpStatus.NOT_FOUND);
		}}
}
