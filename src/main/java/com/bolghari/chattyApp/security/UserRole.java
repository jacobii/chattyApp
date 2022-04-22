package com.bolghari.chattyApp.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.bolghari.chattyApp.security.UserPermission.*;

public enum UserRole {

USER(Sets.newHashSet(USER_READ, USER_READ)),
ADMIN(Sets.newHashSet(
        ADMIN_READ,
        ADMIN_WRITE,
        USER_READ,
        USER_WRITE
)),
GUEST(Sets.newHashSet(GUEST_READ));


    private final Set<UserPermission> permission;


    UserRole(Set<UserPermission> permission) {
        this.permission = permission;
    }

    public Set<UserPermission> getPermissions() {
        return permission;
    }

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
