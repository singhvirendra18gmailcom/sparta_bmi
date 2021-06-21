package com.bmi.sparta.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmi.sparta.models.UserDTO;
import com.bmi.sparta.models.UserDTOsList;
import com.bmi.sparta.repository.Users;
import com.bmi.sparta.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ModelMapper modelMapper;

	public UserDTO findById(Integer id) {
		Optional<Users> userEntity = usersRepository.findById(id);
		if (userEntity.isPresent()) {
			return modelMapper.map(userEntity.get(), UserDTO.class);
		} else {
			return new UserDTO();
		}
	}

	public UserDTOsList saveAll(List<UserDTO> userDtos) {
		UserDTOsList userdtoList = new UserDTOsList();
		int count = 0;
		for (UserDTO u : userDtos) {
			userdtoList.getUserDtos().add(save(u));
			userdtoList.setCount(++count);
		}
		return userdtoList;
	}

	public UserDTO save(UserDTO userDtos) {
		Users user = modelMapper.map(userDtos, Users.class);
		Users savedUser = usersRepository.save(user);
		return modelMapper.map(savedUser, UserDTO.class);
	}

	public UserDTO update(Integer id, UserDTO userDTO) {
		Optional<Users> userEntity = usersRepository.findById(id);
		if (userEntity.isPresent()) {
			Users user = userEntity.get();
			user.setName(userDTO.getName());
			user.setHeight(userDTO.getHeight());
			user.setWeight(userDTO.getWeight());
			user.setAge(userDTO.getAge());
			user = usersRepository.save(user);
			return modelMapper.map(user, UserDTO.class);
		}
		return null;

	}

	public UserDTO deleteById(Integer id) {
		Optional<Users> userEntity = usersRepository.findById(id);
		if (userEntity.isPresent()) {
			usersRepository.deleteById(id);
			return modelMapper.map(userEntity.get(), UserDTO.class);
		} else {
			return null;
		}
	}

	public UserDTOsList findAll() {
		Iterable<Users> users = usersRepository.findAll();
		UserDTOsList userdtoList = new UserDTOsList();
		int count = 0;
		for (Users u : users) {
			userdtoList.getUserDtos().add(modelMapper.map(u, UserDTO.class));
			userdtoList.setCount(++count);
		}
		return userdtoList;

	}

}
