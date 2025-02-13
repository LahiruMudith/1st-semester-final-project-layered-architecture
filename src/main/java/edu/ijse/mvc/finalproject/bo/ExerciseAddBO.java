package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.ExerciseDto;

import java.sql.SQLException;

public interface ExerciseAddBO extends SuperBO{
    boolean save(ExerciseDto exerciseDto) throws SQLException, ClassNotFoundException;
}
