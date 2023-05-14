package com.juri.XNXGAMES_Auth.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

public class UserInfoDTO {

    @Getter
    @ToString
    @RequiredArgsConstructor
    static public class Request {
        private final String firstName;
        private final String lastName;
        private final String email;
    }

}
