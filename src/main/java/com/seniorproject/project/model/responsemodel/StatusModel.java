package com.seniorproject.project.model.responsemodel;

import lombok.Data;

@Data
public class StatusModel {
    private int code;
    private String message;

    public void setCode(int code){
        this.code = code;
        if(code == 1000){
            setMessage("Success");
        }
        else if(code ==1001){
            setMessage("Record not found");
        }
        else if(code == 2000){
            setMessage("Exception Error");
        }
        else if(code == 2001){
            setMessage("Unable to create user");
        }
        else if (code == 2002){
            setMessage("Unable to delete this account");
        }
        else if (code == 3000){
            setMessage("File not found");
        }
        else if (code == 3001){
            setMessage("Unable to create file");
        }
        else if (code == 3002){
            setMessage("Unable to delete file");
        }
        else if (code == 3003){
            setMessage("This user doesn't have any files yet");
        }
        else{
            setMessage("There is something wrong with your request");
        }

    }

}
