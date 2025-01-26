package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.EmployeeDto;
import edu.ijse.mvc.finalproject.dto.FitnessCenterDto;
import edu.ijse.mvc.finalproject.dto.PositionItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO{
    boolean save(EmployeeDto employeeDto);

    boolean delete(String text);

    boolean update(EmployeeDto employeeDto);

    ArrayList<FitnessCenterDto> getCenterDetails() throws SQLException;

    ArrayList<EmployeeDto> getEmployee() throws SQLException;

    ArrayList<PositionItemDto> getPositions() throws SQLException;

    String getNextEmployeeId() throws SQLException;
}
