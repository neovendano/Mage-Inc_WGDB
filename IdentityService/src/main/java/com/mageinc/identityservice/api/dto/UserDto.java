package com.mageinc.identityservice.api.dto;

import com.mageinc.identityservice.repo.model.Status;

public class UserDto {
    String username;
    String password;
    String mail;
    int rate;
    Status status;
}
