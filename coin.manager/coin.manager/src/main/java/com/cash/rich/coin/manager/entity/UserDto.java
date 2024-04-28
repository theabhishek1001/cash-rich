package com.cash.rich.coin.manager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer mobile;
}
