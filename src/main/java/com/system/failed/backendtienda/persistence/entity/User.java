package com.system.failed.backendtienda.persistence.entity;

import com.system.failed.backendtienda.persistence.util.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "'user'")
@Setter
@Getter
public class User implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;
	private String name;
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (role == null) return null;
		if (role.getPermissions() == null) return null;
//		return role.getPermissions().stream()
//				.map(each -> {
//					String permission = each.name();
//					return new SimpleGrantedAuthority(permission);
//
//				}).collect(Collectors.toList());
		return role.getPermissions().stream()
				.map(Enum::name)
				.map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}
}
