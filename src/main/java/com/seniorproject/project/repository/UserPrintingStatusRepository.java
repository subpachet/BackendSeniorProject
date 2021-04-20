package com.seniorproject.project.repository;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.FileStorage;
import com.seniorproject.project.model.PatchCostStatusRequest;
import com.seniorproject.project.model.UserPrintingStatus;
import com.seniorproject.project.repository.mapper.FileStorageMapper;
import com.seniorproject.project.repository.mapper.UserPrintingStatusMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static com.seniorproject.project.constant.GlobalConstant.STATUS_IN_QUEUE;

@Repository
@Slf4j
public class UserPrintingStatusRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ID = "id";
    private static final String STATUS = "status";
    private static final String PRINTING_COLOR_STATUS = "printing_color_status";
    private static final String PRINTING_TYPE_STATUS = "printing_type_status";
    private static final String PAPER_SIZE = "paper_size";
    private static final String PAGES_PER_SHEET = "pages_per_sheet";
    private static final String ADDITIONAL_PAPER_TYPE = "additional_paper_type";
    private static final String BINDING_TYPE = "binding_type";
    private static final String COVER_PAPER_COLOR = "cover_paper_color";
    private static final String ADDITIONAL_COVER_PAPER = "additional_cover_paper";
    private static final String TRANSPARENT_COVER = "transparent_cover";
    private static final String FILE_NAME = "file_name";
    private static final String COST = "cost";
    private static final String NUMBER_OF_COPIES = "number_of_copies";
    private static final String FILE_ID = "file_id";
    private static final String USER_ID = "user_id";
    private static final String FILE_PATH = "file_path";
    private static final String TABLENAME = "user_printing_status";

    private String getAllFields(){
        StringJoiner allfields = new StringJoiner(",");
        allfields.add(ID);
        allfields.add(STATUS);
        allfields.add(PRINTING_COLOR_STATUS);
        allfields.add(PRINTING_TYPE_STATUS);
        allfields.add(PAPER_SIZE);
        allfields.add(PAGES_PER_SHEET);
        allfields.add(ADDITIONAL_PAPER_TYPE);
        allfields.add(BINDING_TYPE);
        allfields.add(COVER_PAPER_COLOR);
        allfields.add(ADDITIONAL_COVER_PAPER);
        allfields.add(TRANSPARENT_COVER);
        allfields.add(FILE_NAME);
        allfields.add(COST);
        allfields.add(NUMBER_OF_COPIES);
        allfields.add(FILE_PATH);
        allfields.add(FILE_ID);
        allfields.add(USER_ID);


        return allfields.toString();
    }


    public List<UserPrintingStatus> getAllPrintingStatus(){
        StringJoiner sql  = new StringJoiner(" ");
        sql.add("SELECT");
        sql.add((getAllFields()));
        sql.add("FROM");
        sql.add(TABLENAME);

        log.info("{}",sql.toString());
        try {
            return namedParameterJdbcTemplate.query(sql.toString(),new UserPrintingStatusMapper());
        }catch (Exception ex){
            throw new ProjectException(1999);
        }
    }

    public String getAllParams(){
        StringJoiner params = new StringJoiner(", :");
        params.add(":" + ID);
        params.add(STATUS);
        params.add(PRINTING_COLOR_STATUS);
        params.add(PRINTING_TYPE_STATUS);
        params.add(PAPER_SIZE);
        params.add(PAGES_PER_SHEET);
        params.add(ADDITIONAL_PAPER_TYPE);
        params.add(BINDING_TYPE);
        params.add(COVER_PAPER_COLOR);
        params.add(ADDITIONAL_COVER_PAPER);
        params.add(TRANSPARENT_COVER);
        params.add(FILE_NAME);
        params.add(COST);
        params.add(NUMBER_OF_COPIES);
        params.add(FILE_PATH);
        params.add(FILE_ID);
        params.add(USER_ID);

        return params.toString();
    }

    public List<UserPrintingStatus> getPrintingStatusById(int id){
        Map<String,Object> params = new HashMap<>();
        params.put(ID,id);
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT");
        sql.add(getAllFields());
        sql.add("FROM");
        sql.add(TABLENAME);
        sql.add("WHERE");
        sql.add(ID);
        sql.add("= :id");

        log.info("{}",sql.toString());
        try{
            return namedParameterJdbcTemplate.query(sql.toString(),params,new UserPrintingStatusMapper());
        }catch (Exception e){
            throw new ProjectException(3000);
        }
    }

    public int insertPrintingStatus(UserPrintingStatus userPrintingStatus){
        Map<String, Object> namedParams = new HashMap<>();
        namedParams.put(ID, userPrintingStatus.getId());
        namedParams.put(STATUS, STATUS_IN_QUEUE);
        namedParams.put(PRINTING_COLOR_STATUS, userPrintingStatus.getPrintingColorStatus());
        namedParams.put(PRINTING_TYPE_STATUS,userPrintingStatus.getPrintingTypeStatus());
        namedParams.put(PAPER_SIZE,userPrintingStatus.getPaperSize());
        namedParams.put(PAGES_PER_SHEET,userPrintingStatus.getPagesPerSheet());
        namedParams.put(ADDITIONAL_PAPER_TYPE,userPrintingStatus.getAdditionalPaperType());
        namedParams.put(BINDING_TYPE,userPrintingStatus.getBindingType());
        namedParams.put(COVER_PAPER_COLOR,userPrintingStatus.getCoverPaperColor());
        namedParams.put(ADDITIONAL_COVER_PAPER,userPrintingStatus.getAdditionalCoverPaper());
        namedParams.put(TRANSPARENT_COVER,userPrintingStatus.getTransparentCover());
        namedParams.put(FILE_NAME,userPrintingStatus.getFileName());
        namedParams.put(COST,0);
        namedParams.put(NUMBER_OF_COPIES,userPrintingStatus.getNumberOfCopies());
        namedParams.put(FILE_PATH,userPrintingStatus.getFilePath());
        namedParams.put(FILE_ID,userPrintingStatus.getFileId());
        namedParams.put(USER_ID,userPrintingStatus.getUserId());

        StringJoiner sql = new StringJoiner(" ");
        sql.add("INSERT INTO");
        sql.add(TABLENAME);
        sql.add("(");
        sql.add(getAllFields());
        sql.add(")");
        sql.add("VALUES");
        sql.add("(");
        sql.add(getAllParams());
        sql.add(");");

        log.info("{}",sql.toString());
        try {
            return namedParameterJdbcTemplate.update(sql.toString(),namedParams);
        }catch (Exception ex){
            throw new ProjectException(1999);
        }
    }

    public List<UserPrintingStatus> deletePrintingStatusById(int id){
        Map<String,Object> params = new HashMap<>();
        params.put(ID,id);

        StringJoiner sql = new StringJoiner(" ");
        sql.add("DELETE");
        sql.add("FROM");
        sql.add(TABLENAME);
        sql.add("WHERE");
        sql.add(ID+ "=:id");

        if(namedParameterJdbcTemplate.update(sql.toString(),params) == 1){
            log.info("Delete Printing status id {} success",id);
            return getAllPrintingStatus();
        }
        else{
            log.info("No Printing status with id {}",id);
            throw new ProjectException(3000);
        }
    }

    public int patchUpdateCostStatus(int statusid, PatchCostStatusRequest request){
        Map<String, Object> namedParams = new HashMap<>();
        namedParams.put(ID, statusid);
        namedParams.put(STATUS, request.getStatus());
        namedParams.put(COST,request.getCost());

        StringJoiner sql = new StringJoiner(" ");
        sql.add("UPDATE");
        sql.add(TABLENAME);
        sql.add("SET");
        sql.add(COST);
        sql.add("= :"+COST+",");
        sql.add(STATUS);
        sql.add("= :"+STATUS);
        sql.add("WHERE");
        sql.add(ID);
        sql.add("= :"+ID);

        log.info("{}",sql.toString());
        try {
            return namedParameterJdbcTemplate.update(sql.toString(),namedParams);
        }catch (Exception ex){
            throw new ProjectException(1999);
        }

    }


}
