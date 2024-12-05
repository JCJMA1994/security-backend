package com.system.failed.backendtienda.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SaveUser implements Serializable {

	@Size(min = 6, max = 50)
	private String name;
	private String username;

	@Size(min = 8, max = 50)
	private String password;

	private String repeatedPassword;
}
