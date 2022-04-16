package com.bolghari.chatapp.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.bolghari.chatapp.security.UserPermission.*;

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

    public Set<UserPermission> getPermission() {
        return permission;
    }
}
