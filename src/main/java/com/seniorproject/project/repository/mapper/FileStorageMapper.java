package com.seniorproject.project.repository.mapper;

import com.seniorproject.project.model.FileStorage;
import com.seniorproject.project.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class FileStorageMapper implements RowMapper<FileStorage> {
    @Override
    public FileStorage mapRow(ResultSet resultSet, int i)throws SQLException{
        return new FileStorage()
                .setId(resultSet.getInt("id"))
                .setFileName(resultSet.getString("file_name"))
                .setPath(resultSet.getString("path"))
                .setUserID(resultSet.getString("user_id"));
    }
}
