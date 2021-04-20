package com.seniorproject.project.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserPrintingStatus implements Comparable<UserPrintingStatus> {
    private int id;
    private String status;
    private int printingColorStatus;
    private int printingTypeStatus;
    private String paperSize;
    private int pagesPerSheet;
    private String additionalPaperType;
    private String bindingType;
    private String coverPaperColor;
    private String additionalCoverPaper;
    private int transparentCover;
    private int fileId;
    private String userId;
    private String fileName;
    private double cost;
    private int numberOfCopies;
    private String filePath;

    @Override
    public int compareTo(UserPrintingStatus o) {
        return this.fileId - o.fileId;
    }
}
