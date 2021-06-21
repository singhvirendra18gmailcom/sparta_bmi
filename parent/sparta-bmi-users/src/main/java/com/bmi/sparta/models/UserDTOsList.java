package com.bmi.sparta.models;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;


@Component
public class UserDTOsList  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer count;
	private List<UserDTO> userDtos=new ArrayList<>();

	public UserDTOsList(Integer count, List<UserDTO> userDtos) {
		super();
		this.count = count;
		this.userDtos = userDtos;
	}

	public UserDTOsList() {
		super();
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<UserDTO> getUserDtos() {
		return userDtos;
	}

	public void setUserDtos(List<UserDTO> userDtos) {
		this.userDtos = userDtos;
	}
	
	

}
