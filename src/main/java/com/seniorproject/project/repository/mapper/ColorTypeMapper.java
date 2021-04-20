package com.seniorproject.project.repository.mapper;

import com.seniorproject.project.model.ColorType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColorTypeMapper implements RowMapper<ColorType> {
    @Override
    public ColorType mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ColorType()
                .setId(resultSet.getInt("id"))
                .setColorCode(resultSet.getString("color_code"))
                .setColorDescription(resultSet.getString("color_description"));
    }
}
