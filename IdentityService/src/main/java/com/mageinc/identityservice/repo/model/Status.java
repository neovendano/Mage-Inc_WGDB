package com.mageinc.identityservice.repo.model;

import org.springframework.security.core.GrantedAuthority;

public enum Status implements GrantedAuthority {
    user, moderator, admin, developer;

    @Override
    public String getAuthority() {
        return toString();
    }
}