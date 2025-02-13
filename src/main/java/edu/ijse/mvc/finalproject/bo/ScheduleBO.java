package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.AdminDto;
import edu.ijse.mvc.finalproject.dto.ExerciseDto;
import edu.ijse.mvc.finalproject.dto.ExerciseScheduleDto;
import edu.ijse.mvc.finalproject.dto.ScheduleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ScheduleBO extends SuperBO{
    String getNextScheduleId() throws SQLException, ClassNotFoundException;

    ArrayList<ExerciseDto> getExercise() throws SQLException, ClassNotFoundException;

    ArrayList<AdminDto> getAdmin() throws SQLException, ClassNotFoundException;

    ArrayList<ExerciseScheduleDto> getSchedule() throws SQLException, ClassNotFoundException;

    boolean save(ScheduleDto scheduleDto);

    boolean delete(String id) throws SQLException, ClassNotFoundException;

    boolean update(ScheduleDto scheduleDto);

    String getNextExerciseId() throws SQLException, ClassNotFoundException;

    ArrayList<String> getScheduleName() throws SQLException, ClassNotFoundException;
}
