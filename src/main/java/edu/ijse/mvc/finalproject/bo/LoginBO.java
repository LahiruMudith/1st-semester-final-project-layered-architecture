package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.AdminDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoginBO extends SuperBO{
    ArrayList<AdminDto> loadAdminData() throws SQLException;
}
