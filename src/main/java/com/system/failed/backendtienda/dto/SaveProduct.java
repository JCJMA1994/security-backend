package com.system.failed.backendtienda.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class SaveProduct implements Serializable {
	@NotBlank
	private String name;

	@DecimalMin("0.01")
	private BigDecimal price;

	@Min(1)
	private Long categoryId;

	private ProductStatus status;


	public static enum ProductStatus {
		ENABLED, DISABLED
	}
}
