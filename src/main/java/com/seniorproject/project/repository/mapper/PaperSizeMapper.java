package com.seniorproject.project.repository.mapper;

import com.seniorproject.project.model.PaperSize;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaperSizeMapper implements RowMapper<PaperSize> {
    @Override
    public PaperSize mapRow(ResultSet resultSet, int i) throws SQLException {
        return new PaperSize()
                .setId(resultSet.getInt("id"))
                .setPaperCode(resultSet.getString("paper_code"))
                .setPaperDescription(resultSet.getString("paper_description"));
//                .setCost(resultSet.getDouble("cost"));
    }
}
