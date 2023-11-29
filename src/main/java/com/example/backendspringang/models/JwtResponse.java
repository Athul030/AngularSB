package com.example.backendspringang.models;

import com.example.backendspringang.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JwtResponse {

    private String token;

    private User user;
}
