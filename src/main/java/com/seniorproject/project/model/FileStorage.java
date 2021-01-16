package com.seniorproject.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class FileStorage {
    private int id;
    private String fileName;
    private String path;
    private String userID;
}
