package com.ijse.gdse72.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    private String u_id;
    private String username;
    private String password;
    private String role;
}
