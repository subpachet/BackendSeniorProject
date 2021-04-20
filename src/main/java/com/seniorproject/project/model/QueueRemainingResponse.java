package com.seniorproject.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class QueueRemainingResponse {
    private int totalRemaining;
    private List<UserPrintingStatus> printingList;
}
