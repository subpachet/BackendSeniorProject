package com.seniorproject.project.repository;

import com.seniorproject.project.exception.ProjectException;
import com.seniorproject.project.model.ColorType;
import com.seniorproject.project.model.PaperSize;
import com.seniorproject.project.repository.mapper.ColorTypeMapper;
import com.seniorproject.project.repository.mapper.PaperSizeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringJoiner;

@Repository
@Slf4j
public class PaperSizeRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String ID ="id";
    private static final String PAPER_CODE ="paper_code";
    private static final String PAPER_DESCRIPTION = "paper_description";
    private static final String COST = "cost";
    private static final String TABLENAME = "paper_size";



    public List<PaperSize> getAllPaperSize(){
        StringJoiner sql = new StringJoiner(" ");
        sql.add("SELECT");
        sql.add(getAllFields());
        sql.add("FROM");
        sql.add(TABLENAME);
        sql.add("WHERE");
        sql.add("paper_code LIKE '%A%'");

        log.info("{}",sql.toString());
        try {
            return namedParameterJdbcTemplate.query(sql.toString(),new PaperSizeMapper());
        }catch (Exception ex){
            log.info(ex.getMessage());
            throw new ProjectException(1899);
        }
    }

    private String getAllFields(){
        StringJoiner allfield = new StringJoiner(",");
        allfield.add(ID);
        allfield.add(PAPER_CODE);
        allfield.add(PAPER_DESCRIPTION);
//        allfield.add(COST);

        return allfield.toString();
    }
}
