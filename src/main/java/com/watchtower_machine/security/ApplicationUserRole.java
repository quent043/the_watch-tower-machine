package com.watchtower_machine.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.watchtower_machine.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    BASIC_USER(Sets.newHashSet(USER_READ, USER_WRITE, SPOT_READ)),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE, SPOT_ADD, SPOT_EDIT, SPOT_READ)),
    WRITER(Sets.newHashSet(USER_WRITE)),
    READER(Sets.newHashSet(USER_READ, SPOT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());//    USER_WRITE("user:write"); getPermission() renvoie "user:write"

        permissions.add(new SimpleGrantedAuthority(("ROLE_" + this.name())));
        return permissions;
    }
}
