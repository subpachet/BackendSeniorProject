package com.seniorproject.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors(chain = true)
public class ColorType {
    @NotNull
    private int id;
    private String colorCode;
    private String colorDescription;
}
