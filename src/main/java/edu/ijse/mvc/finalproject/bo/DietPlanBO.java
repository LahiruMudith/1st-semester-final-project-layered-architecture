package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dao.DietPlanDAO;
import edu.ijse.mvc.finalproject.dto.DietPlanDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DietPlanBO extends SuperBO{
    boolean add(DietPlanDto dietPlanDto);

    boolean delete(String id);

    boolean update(DietPlanDto dietPlanDto);

    ArrayList<DietPlanDto> loadTable() throws SQLException;
}
