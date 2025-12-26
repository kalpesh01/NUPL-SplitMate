package com.splitmate.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCodes {

    USERS_NOT_FOUND(1, "No users found for given input"),
    EXPENSE_NOT_FOUND(2, "No expense record found for given input"),
    GROUP_NOT_FOUND(3, "No group found for given input"),
    SPLIT_EXPENSE_NOT_FOUND(4, "No split expense found for given input"),
    EMAIL_ALREADY_EXIST(5,"User already exist with the given Email"),
    INVALID_USER_LIST(6,"Invalid userId in usersId list"),
    INTERNAL_SERVER_ERROR(7,"Internal Server Error");

    private final int code;
    private final String message;

    public int getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }
}
