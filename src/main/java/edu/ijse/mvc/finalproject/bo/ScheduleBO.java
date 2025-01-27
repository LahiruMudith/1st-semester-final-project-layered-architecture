package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.dto.AdminDto;
import edu.ijse.mvc.finalproject.dto.ExerciseDto;
import edu.ijse.mvc.finalproject.dto.ExerciseScheduleDto;
import edu.ijse.mvc.finalproject.dto.ScheduleDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ScheduleBO extends SuperBO{
    String getNextScheduleId() throws SQLException;

    ArrayList<ExerciseDto> getExercise() throws SQLException;

    ArrayList<AdminDto> getAdmin() throws SQLException;

    ArrayList<ExerciseScheduleDto> getSchedule() throws SQLException;

    boolean save(ScheduleDto scheduleDto);

    boolean delete(String id);

    boolean update(ScheduleDto scheduleDto);
}
