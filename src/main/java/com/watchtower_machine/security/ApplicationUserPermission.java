package com.watchtower_machine.security;

public enum ApplicationUserPermission {
    SPOT_EDIT("spot:edit"),
    SPOT_READ("spot:read"),
    SPOT_ADD("spot:add"),
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
