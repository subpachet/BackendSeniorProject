package com.seniorproject.project.repository;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.ColorType;
import com.seniorproject.project.model.User;
import com.seniorproject.project.repository.mapper.ColorTypeMapper;
import com.seniorproject.project.repository.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringJoiner;

@Repository
@Slf4j
public class ColorTypeRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ID ="id";
    private static final String COLOR_CODE ="color_code";
    private static final String COLOR_DESCRIPTION = "color_description";
    private static final String TABLENAME = "color_type";


    public List<ColorType> getAllColorType(){
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT");
        sql.add(getAllFields());
        sql.add("FROM");
        sql.add(TABLENAME);

        log.info("{}",sql.toString());
        try {
            return namedParameterJdbcTemplate.query(sql.toString(),new ColorTypeMapper());
        }catch (Exception ex){
            throw new ProjectException(1899);
        }
    }

    private String getAllFields(){
        StringJoiner allfield = new StringJoiner(",");
        allfield.add(ID);
        allfield.add(COLOR_CODE);
        allfield.add(COLOR_DESCRIPTION);

        return allfield.toString();
    }
}
