package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.ExerciseDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ScheduleBO extends SuperBO{
    String getNextScheduleId() throws SQLException;

    ArrayList<ExerciseDto> getExercise();
}
