package com.seniorproject.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserFileResponse {
    private String userName;
    private String firstName;
    private String lastName;
    private List<FileStorage> fileStorages;

}
