package com.seniorproject.project.repository.mapper;

import com.seniorproject.project.model.UserPrintingStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserPrintingStatusMapper implements RowMapper<UserPrintingStatus> {

    @Override
    public UserPrintingStatus mapRow(ResultSet resultSet, int i) throws SQLException {
        return new UserPrintingStatus()
                .setId(resultSet.getInt("id"))
                .setStatus(resultSet.getString("status"))
                .setPrintingColorStatus(resultSet.getInt("printing_color_status"))
                .setPrintingTypeStatus(resultSet.getInt("printing_type_status"))
                .setPaperSize(resultSet.getString("paper_size"))
                .setPagesPerSheet(resultSet.getInt("pages_per_sheet"))
                .setAdditionalPaperType(resultSet.getString("additional_paper_type"))
                .setBindingType(resultSet.getString("binding_type"))
                .setCoverPaperColor(resultSet.getString("cover_paper_color"))
                .setAdditionalCoverPaper(resultSet.getString("additional_cover_paper"))
                .setTransparentCover(resultSet.getInt("transparent_cover"))
                .setFileId(resultSet.getInt("file_id"))
                .setUserId(resultSet.getString("user_id"))
                .setFileName(resultSet.getString("file_name"))
                .setCost(resultSet.getDouble("cost"))
                .setNumberOfCopies(resultSet.getInt("number_of_copies"))
                .setFilePath(resultSet.getString("file_path"));
    }
}
