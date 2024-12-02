package com.system.failed.backendtienda.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class APIError implements Serializable {

	private String backendMessage;

	private String message;

	private LocalDateTime timestamp;

	private String path;

	private String method;
}
