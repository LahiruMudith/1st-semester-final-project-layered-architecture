package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.ExerciseScheduleDAO;
import edu.ijse.mvc.finalproject.db.DBConnection;
import edu.ijse.mvc.finalproject.dto.ExerciseScheduleDto;
import edu.ijse.mvc.finalproject.entity.ExerciseSchedule;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseScheduleDAOImpl implements ExerciseScheduleDAO {

    @Override
    public ArrayList<ExerciseSchedule> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from exerciseSchedule");
        ArrayList<ExerciseSchedule> list = new ArrayList<>();

        while (resultSet.next()){
            ExerciseSchedule exerciseSchedule = new ExerciseSchedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getInt(6),
                    resultSet.getString(7)
            );
            list.add(exerciseSchedule);
        }
        return list;
    }

    @Override
    public boolean save(ExerciseSchedule entity) throws SQLException {
        return CrudUtil.execute("INSERT INTO exerciseSchedule VALUES(?,?,?,?,?,?,?)",
                            entity.getSchedule_id(),
                            entity.getExercise_id(),
                            entity.getExercise_name(),
                            entity.getSchedule_name(),
                            entity.getExercise_count(),
                            entity.getExercise_set(),
                            entity.getAdmin_id()
        );
    }

    @Override
    public boolean update(ExerciseSchedule entity) {
        return CrudUtil.execute("UPDATE exerciseSchedule SET exercise_id = ?, exercise_name = ?, schedule_name = ?, exercise_count = ?, exercise_set = ?, admin_id = ? WHERE schedule_id = ?",
                entity.getExercise_id(),
                entity.getExercise_name(),
                entity.getSchedule_name(),
                entity.getExercise_count(),
                entity.getExercise_set(),
                entity.getAdmin_id(),
                entity.getSchedule_id()
        );
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException {
        return "";
    }
}
