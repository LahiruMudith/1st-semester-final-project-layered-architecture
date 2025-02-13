package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.ScheduleBO;
import edu.ijse.mvc.finalproject.dao.*;
import edu.ijse.mvc.finalproject.db.DBConnection;
import edu.ijse.mvc.finalproject.dto.AdminDto;
import edu.ijse.mvc.finalproject.dto.ExerciseDto;
import edu.ijse.mvc.finalproject.dto.ExerciseScheduleDto;
import edu.ijse.mvc.finalproject.dto.ScheduleDto;
import edu.ijse.mvc.finalproject.entity.Admin;
import edu.ijse.mvc.finalproject.entity.Exercise;
import edu.ijse.mvc.finalproject.entity.ExerciseSchedule;
import edu.ijse.mvc.finalproject.entity.Schedule;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleBOImpl implements ScheduleBO {
    ScheduleDAO scheduleDAO = (ScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.SCHEDULE);
    ExerciseDAO exerciseDAO = (ExerciseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXERCISE);
    ExerciseScheduleDAO exerciseScheduleDAO = (ExerciseScheduleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.EXERCISE_SCHEDULE);
    AdminDAO adminDAO = (AdminDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ADMIN);
    @Override
    public String getNextScheduleId() throws SQLException, ClassNotFoundException {
        return scheduleDAO.generateNewId();
    }

    @Override
    public ArrayList<ExerciseDto> getExercise() throws SQLException, ClassNotFoundException {
        ArrayList<Exercise> exercises = exerciseDAO.getAll();
        ArrayList<ExerciseDto> exerciseDtos = new ArrayList<>();
        for (Exercise exercise : exercises) {
            exerciseDtos.add(new ExerciseDto(
                    exercise.getExercise_id(),
                    exercise.getExercise_name(),
                    exercise.getDescription()));
        };
        return exerciseDtos;
    }

    @Override
    public ArrayList<AdminDto> getAdmin() throws SQLException, ClassNotFoundException {
        ArrayList<Admin> list = adminDAO.getAll();
        ArrayList<AdminDto> adminDtos = new ArrayList<>();
        for (Admin admin : list) {
            adminDtos.add(new AdminDto(
                    admin.getAdmin_id(),
                    admin.getName(),
                    admin.getAddress(),
                    admin.getPassword()
            ));
        }
        return adminDtos;
    }

    @Override
    public ArrayList<ExerciseScheduleDto> getSchedule() throws SQLException, ClassNotFoundException {
        ArrayList<ExerciseSchedule> exerciseSchedules = exerciseScheduleDAO.getAll();
        ArrayList<ExerciseScheduleDto> exerciseScheduleDtos = new ArrayList<>();
        for (ExerciseSchedule exerciseSchedule : exerciseSchedules) {
            exerciseScheduleDtos.add(new ExerciseScheduleDto(
                    exerciseSchedule.getSchedule_id(),
                    exerciseSchedule.getExercise_id(),
                    exerciseSchedule.getExercise_name(),
                    exerciseSchedule.getSchedule_name(),
                    exerciseSchedule.getExercise_count(),
                    exerciseSchedule.getExercise_set(),
                    exerciseSchedule.getAdmin_id()
            ));
        }
        return exerciseScheduleDtos;
    }

    @Override
    public boolean save(ScheduleDto scheduleDto) {
        Connection connection = null;
        System.out.println("Schedule BO ekata awa");
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            for (ExerciseScheduleDto exerciseScheduleDto : scheduleDto.getExerciseScheduleDtos()) {
                System.out.println("for loop ekata awa");
                System.out.println(scheduleDto.getSchedule_id());
                boolean isSavedSheduleTable = scheduleDAO.save(new Schedule(
                        scheduleDto.getSchedule_id(),
                        scheduleDto.getName(),
                        scheduleDto.getAdmin_id()));
                if (!isSavedSheduleTable) {
                    connection.rollback();
                    return false;
                }
                System.out.println("Saved Schedule Table");

                boolean isSavedExerciseSheduleTable = exerciseScheduleDAO.save(new ExerciseSchedule(
                        scheduleDto.getSchedule_id(),
                        exerciseScheduleDto.getExercise_id(),
                        exerciseScheduleDto.getExercise_name(),
                        exerciseScheduleDto.getSchedule_name(),
                        exerciseScheduleDto.getExercise_count(),
                        exerciseScheduleDto.getExercise_set(),
                        exerciseScheduleDto.getAdmin_id()
                ));
                if (!isSavedExerciseSheduleTable) {
                    connection.rollback();
                    return false;
                }
                System.out.println(exerciseScheduleDto.getExercise_id() + "Saved Exercise Schedule Table");
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return scheduleDAO.delete(id);
    }

    @Override
    public boolean update(ScheduleDto scheduleDto) {
        Connection connection = null;
        System.out.println("Schedule BO ekata awa");

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            for (ExerciseScheduleDto exerciseScheduleDto : scheduleDto.getExerciseScheduleDtos()){
                System.out.println("For loop ekata awa");

                boolean isSavedSheduleTable = scheduleDAO.update(new Schedule(
                        scheduleDto.getSchedule_id(),
                        scheduleDto.getName(),
                        scheduleDto.getAdmin_id()
                ));
                if (!isSavedSheduleTable) {
                    connection.rollback();
                    return false;
                }

                boolean isSavedExerciseSheduleTable = exerciseScheduleDAO.save(new ExerciseSchedule(
                        scheduleDto.getSchedule_id(),
                        exerciseScheduleDto.getExercise_id(),
                        exerciseScheduleDto.getExercise_name(),
                        exerciseScheduleDto.getSchedule_name(),
                        exerciseScheduleDto.getExercise_count(),
                        exerciseScheduleDto.getExercise_set(),
                        exerciseScheduleDto.getAdmin_id()
                ));
                if (!isSavedExerciseSheduleTable) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getNextExerciseId() throws SQLException, ClassNotFoundException {
        return scheduleDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getScheduleName() throws SQLException, ClassNotFoundException {
        ArrayList<Schedule> schedules = scheduleDAO.getAll();
        ArrayList<String> names = new ArrayList<>();
        for (Schedule schedule : schedules){
            names.add(schedule.getName());
        }
        return names;
    }
}
