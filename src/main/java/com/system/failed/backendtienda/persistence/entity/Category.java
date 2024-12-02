package com.system.failed.backendtienda.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private CategoryStatus status;

	public static enum CategoryStatus {
		ENABLED, DISABLED
	}

}
