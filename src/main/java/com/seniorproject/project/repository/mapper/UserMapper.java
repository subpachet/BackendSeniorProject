package com.seniorproject.project.repository.mapper;

import com.seniorproject.project.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new User()
                .setId(resultSet.getString("id"))
                .setEmail(resultSet.getString("email"))
                .setPhone(resultSet.getString("phone"))
                .setUserName(resultSet.getString("username"))
                .setPassword(resultSet.getString("password"))
                .setFirstName(resultSet.getString("firstname"))
                .setLastName(resultSet.getString("lastname"));
    }
}
