package com.seniorproject.project.repository;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.User;
import com.seniorproject.project.repository.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import org.yaml.snakeyaml.events.Event;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Repository
@Slf4j
public class UserRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ID ="id";
    private static final String EMAIL ="email";
    private static final String PHONE = "phone";
    private static final String USERNAME ="username";
    private static final String PASSWORD ="password";
    private static final String FIRSTNAME ="firstname";
    private static final String LASTNAME ="lastname";
    private static final String TABLENAME = "users";

    public int insertUser(User user) {
        Map<String, Object> namedParams = new HashMap<>();
        namedParams.put(ID, user.getId());
        namedParams.put(EMAIL, user.getEmail());
        namedParams.put(PHONE, user.getPhone());
        namedParams.put(USERNAME, user.getUserName());
        namedParams.put(PASSWORD, user.getPassword());
        namedParams.put(FIRSTNAME, user.getFirstName());
        namedParams.put(LASTNAME, user.getLastName());

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
        try{
            return namedParameterJdbcTemplate.update(sql.toString(),namedParams);
        }catch (Exception ex){
            throw new ProjectException(1899);
        }
    }

    public List<User> getAllUser(){
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT");
        sql.add(getAllFields());
        sql.add("FROM");
        sql.add(TABLENAME);

        log.info("{}",sql.toString());
        try {
            return namedParameterJdbcTemplate.query(sql.toString(),new UserMapper());
        }catch (Exception ex){
            throw new ProjectException(1899);
        }
    }

    public List<User> findByUserName(String username){
        Map<String,Object> params = new HashMap<>();
        params.put(USERNAME,username);
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT");
        sql.add(getAllFields());
        sql.add("FROM");
        sql.add(TABLENAME);
        sql.add("WHERE");
        sql.add(USERNAME + " = :"+ USERNAME);

        log.info("{}",sql.toString());
        try {
            return namedParameterJdbcTemplate.query(sql.toString(),params,new UserMapper());
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new ProjectException(1899);
        }

    }

    public List<User> findByEmail(String email){
        Map<String,Object> params = new HashMap<>();
        params.put(EMAIL,email);
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT");
        sql.add(getAllFields());
        sql.add("FROM");
        sql.add(TABLENAME);
        sql.add("WHERE");
        sql.add(EMAIL + " = :"+ EMAIL);

        log.info("{}",sql.toString());
        try {
            return namedParameterJdbcTemplate.query(sql.toString(),params,new UserMapper());
        }catch (Exception ex){
            throw new ProjectException(1899);
        }

    }

    public User updateUserById(String id, User user){
        Map<String,Object> params = new HashMap<>();
        StringJoiner sql = new StringJoiner(" ");
        sql.add("UPDATE");
        sql.add(TABLENAME);
        sql.add("SET");
        if(user.getEmail() != null){
            sql.add(EMAIL + "=:email,");
            params.put(EMAIL, user.getEmail());
        }
        if (user.getPhone() != null){
            sql.add(PHONE +"=:phone,");
            params.put(PHONE,user.getPhone());
        }
        if (user.getUserName() != null){
            sql.add(USERNAME +"=:username,");
            params.put(USERNAME,user.getUserName());
        }
        if (user.getPassword() != null){
            sql.add(PASSWORD +"=:password,");
            params.put(PASSWORD,user.getPassword());
        }
        if (user.getFirstName() != null){
            sql.add(FIRSTNAME +"=:firstname,");
            params.put(FIRSTNAME,user.getFirstName());
        }
        if (user.getLastName() != null){
            sql.add(LASTNAME +"=:lastname");
            params.put(LASTNAME,user.getLastName());
        }
        sql.add("WHERE id = :id");

        params.put(ID,id);

        int isSuccess;

        log.info("{}",sql.toString());
        try{
            isSuccess= namedParameterJdbcTemplate.update(sql.toString(),params);
        }catch (Exception ex){
            throw new ProjectException(1899);
        }

        if(isSuccess == 1){
            return getUserById(id).get(0);
        }
        else{
            throw new ProjectException(1001);
        }

    }

    public List<User> deleteUserById(String id){
        Map<String,Object> params = new HashMap<>();
        params.put(ID,id);

        StringJoiner sql = new StringJoiner(" ");
        sql.add("DELETE");
        sql.add("FROM");
        sql.add(TABLENAME);
        sql.add("WHERE");
        sql.add(ID+ "=:id");

        if(namedParameterJdbcTemplate.update(sql.toString(),params) == 1){
            log.info("Delete User id {} success",id);
            return getAllUser();
        }
        else{
            log.info("No User with id {}",id);
            throw new ProjectException(2002);
        }
     }

    public List<User> getUserById(String id){
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
            return namedParameterJdbcTemplate.query(sql.toString(),params,new UserMapper());
        }catch (Exception e){
            throw new ProjectException(2002);
        }
    }

    private String getAllFields(){
        StringJoiner allfield = new StringJoiner(",");
        allfield.add(ID);
        allfield.add(EMAIL);
        allfield.add(PHONE);
        allfield.add(USERNAME);
        allfield.add(PASSWORD);
        allfield.add(FIRSTNAME);
        allfield.add(LASTNAME);

        return allfield.toString();
    }

    private String getAllParams(){
        StringJoiner params = new StringJoiner(", :");
        params.add(":"+ID);
        params.add(EMAIL);
        params.add(PHONE);
        params.add(USERNAME);
        params.add(PASSWORD);
        params.add(FIRSTNAME);
        params.add(LASTNAME);

        return params.toString();
    }
}
