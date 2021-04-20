package com.seniorproject.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ColorTypeResponseModel {
    private List<ColorType> colorTypes;
}
