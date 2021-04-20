package com.seniorproject.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PatchCostStatusRequest {
    private double cost;
    private String status;
}
