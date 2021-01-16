package com.seniorproject.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class AllUserResponse {
    private List<User> users;
}
