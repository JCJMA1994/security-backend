package com.system.failed.backendtienda.persistence.entity.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private  String path;

	private String httpMethod;

	private  boolean permitAll;

	@ManyToOne
	@JoinColumn(name = "module_id")
	private  Module module;
}
