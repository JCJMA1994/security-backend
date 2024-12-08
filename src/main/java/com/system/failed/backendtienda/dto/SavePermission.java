package com.system.failed.backendtienda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class SavePermission implements Serializable {

	@NotBlank
	private String role;
	@NotBlank
	private String operation;

}
