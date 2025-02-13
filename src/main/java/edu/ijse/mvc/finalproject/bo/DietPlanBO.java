package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dao.DietPlanDAO;
import edu.ijse.mvc.finalproject.dto.DietPlanDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DietPlanBO extends SuperBO{
    boolean add(DietPlanDto dietPlanDto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(DietPlanDto dietPlanDto) throws SQLException, ClassNotFoundException;

    ArrayList<DietPlanDto> loadTable() throws SQLException, ClassNotFoundException;

    ArrayList<String> getDietPlanIds() throws SQLException, ClassNotFoundException;

    String getNextDeitPlanId() throws SQLException, ClassNotFoundException;
}
