package com.seniorproject.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserPrintingRequest {
    private FileStorage fileStorage;
    private UserPrintingStatus userPrintingStatus;
}
