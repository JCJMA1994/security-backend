package com.system.failed.backendtienda.persistence.util;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;


@Getter
public enum RoleEnum {
	ADMINISTRATOR(Arrays.asList(RolePermissionEnum.READ_ALL_PRODUCTS,
			RolePermissionEnum.READ_ONE_PRODUCT,
			RolePermissionEnum.CREATE_ONE_PRODUCT,
			RolePermissionEnum.UPDATE_ONE_PRODUCT,
			RolePermissionEnum.DISABLE_ONE_PRODUCT,

			RolePermissionEnum.READ_ALL_CATEGORIES,
			RolePermissionEnum.READ_ONE_CATEGORY,
			RolePermissionEnum.CREATE_ONE_CATEGORY,
			RolePermissionEnum.UPDATE_ONE_CATEGORY,
			RolePermissionEnum.DISABLE_ONE_CATEGORY,

			RolePermissionEnum.READ_MY_PROFILE
	)),
	ASSISTANT_ADMINISTRATOR(Arrays.asList(RolePermissionEnum.READ_ALL_PRODUCTS,
			RolePermissionEnum.READ_ONE_PRODUCT,
			RolePermissionEnum.UPDATE_ONE_PRODUCT,

			RolePermissionEnum.READ_ALL_CATEGORIES,
			RolePermissionEnum.READ_ONE_CATEGORY,
			RolePermissionEnum.UPDATE_ONE_CATEGORY,

			RolePermissionEnum.READ_MY_PROFILE
	)),
	CUSTOMER(List.of(
			RolePermissionEnum.READ_MY_PROFILE
	));

	private final List<RolePermissionEnum> permissions;

	RoleEnum(List<RolePermissionEnum> permissions) {
		this.permissions = permissions;
	}

}
