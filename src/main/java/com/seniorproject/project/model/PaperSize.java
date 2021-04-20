package com.seniorproject.project.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Accessors (chain = true)
public class PaperSize {
    @NotNull
    private int id;
    private String paperCode;
    private String paperDescription;
//    @JsonInclude(JsonInclude.Include.NON_NULL)
//    private double cost;
}
