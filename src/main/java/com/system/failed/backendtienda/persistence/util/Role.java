package com.system.failed.backendtienda.persistence.util;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Role {
	ROLE_ADMIN(Arrays.asList(RolePermission.READ_ALL_PRODUCTS,
			RolePermission.READ_ONE_PRODUCT,
			RolePermission.CREATE_ONE_PRODUCT,
			RolePermission.UPDATE_ONE_PRODUCT,
			RolePermission.DISABLE_ONE_PRODUCT,

			RolePermission.READ_ALL_CATEGORIES,
			RolePermission.READ_ONE_CATEGORY,
			RolePermission.CREATE_ONE_CATEGORY,
			RolePermission.UPDATE_ONE_CATEGORY,
			RolePermission.DISABLE_ONE_CATEGORY,

			RolePermission.READ_MY_PROFILE
	)),
	ROLE_ASSISTANT_ADMIN(Arrays.asList(RolePermission.READ_ALL_PRODUCTS,
			RolePermission.READ_ONE_PRODUCT,
			RolePermission.UPDATE_ONE_PRODUCT,

			RolePermission.READ_ALL_CATEGORIES,
			RolePermission.READ_ONE_CATEGORY,
			RolePermission.UPDATE_ONE_CATEGORY,

			RolePermission.READ_MY_PROFILE
	)),
	ROLE_CUSTOMER(List.of(
			RolePermission.READ_MY_PROFILE
	));

	private final List<RolePermission> permissions;

	Role(List<RolePermission> permissions) {
		this.permissions = permissions;
	}


}
