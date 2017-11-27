package com.thoughtworks.star.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class LoginRequestBody {
    private String username;
    private String password;
}
