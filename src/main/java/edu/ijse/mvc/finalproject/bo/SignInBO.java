package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.SignInDto;

import java.sql.SQLException;

public interface SignInBO extends SuperBO{
    boolean save(SignInDto signInDto);
    String generateNewId() throws SQLException;
}
