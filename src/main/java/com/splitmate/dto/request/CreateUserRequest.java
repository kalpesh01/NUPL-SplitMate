package com.splitmate.dto.request;

import lombok.Data;

@Data
public class CreateUserRequest {
    private String name;
    private String email;
}

