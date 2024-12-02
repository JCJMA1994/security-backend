package com.system.failed.backendtienda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SaveCategory implements Serializable {
	@NotBlank
	private String name;
}
