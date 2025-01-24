package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.SignInDto;

public interface SignInBO extends SuperBO{
    boolean save(SignInDto signInDto);
    String generateNewId();
}
