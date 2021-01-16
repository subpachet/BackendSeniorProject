package com.seniorproject.project.repository;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.FileStorage;
import com.seniorproject.project.repository.mapper.FileStorageMapper;
import com.seniorproject.project.repository.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Repository
@Slf4j
public class FileStorageRepository {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ID = "id";
    private static final String FILENAME = "file_name";
    private static final String PATH = "path";
    private static final String USER_ID = "user_id";
    private static final String TABLENAME = "filestorage";


    public List<FileStorage> getAllFiles(){
        StringJoiner sql  = new StringJoiner(" ");
        sql.add("SELECT");
        sql.add((getAllFields()));
        sql.add("FROM");
        sql.add(TABLENAME);

        log.info("{}",sql.toString());
        try {
            return namedParameterJdbcTemplate.query(sql.toString(),new FileStorageMapper());
        }catch (Exception ex){
            throw new ProjectException(1999);
        }
    }

    private String getAllFields(){
        StringJoiner allfields = new StringJoiner(",");
        allfields.add(ID);
        allfields.add(FILENAME);
        allfields.add(PATH);
        allfields.add(USER_ID);

        return allfields.toString();
    }

    public int insertFiles(FileStorage fileStorage){
        Map<String, Object> namedParams = new HashMap<>();
        namedParams.put(ID, fileStorage.getId());
        namedParams.put(FILENAME, fileStorage.getFileName());
        namedParams.put(PATH, fileStorage.getPath());
        namedParams.put(USER_ID, fileStorage.getUserID());

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

    private String getAllParams() {
        StringJoiner params = new StringJoiner(", :");
        params.add(":" + ID);
        params.add(FILENAME);
        params.add(PATH);
        params.add(USER_ID);

        return params.toString();
    }

    public List<FileStorage> getFileById(int id){
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
            return namedParameterJdbcTemplate.query(sql.toString(),params,new FileStorageMapper());
        }catch (Exception e){
            throw new ProjectException(3000);
        }
    }

    public List<FileStorage> deleteFileById(int id){
        Map<String,Object> params = new HashMap<>();
        params.put(ID,id);

        StringJoiner sql = new StringJoiner(" ");
        sql.add("DELETE");
        sql.add("FROM");
        sql.add(TABLENAME);
        sql.add("WHERE");
        sql.add(ID+ "=:id");

        if(namedParameterJdbcTemplate.update(sql.toString(),params) == 1){
            log.info("Delete File id {} success",id);
            return getAllFiles();
        }
        else{
            log.info("No File with id {}",id);
            throw new ProjectException(3000);
        }
    }
}
