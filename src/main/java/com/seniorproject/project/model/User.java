package com.seniorproject.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User {
    private String id;
    private String email;
    private String phone;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
}
