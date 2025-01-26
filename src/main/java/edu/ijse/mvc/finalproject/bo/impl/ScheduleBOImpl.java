package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.ScheduleBO;
import edu.ijse.mvc.finalproject.dao.DAOFactory;
import edu.ijse.mvc.finalproject.dao.ScheduleDAO;
import edu.ijse.mvc.finalproject.dto.ExerciseDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleBOImpl implements ScheduleBO {
    ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SCHEDULE);
    @Override
    public String getNextScheduleId() throws SQLException {
        return scheduleDAO.generateNewId();
    }

    @Override
    public ArrayList<ExerciseDto> getExercise() {
        return null;
    }
}
