package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.ExerciseDAO;
import edu.ijse.mvc.finalproject.dto.ExerciseDto;
import edu.ijse.mvc.finalproject.entity.Exercise;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ExerciseDAOImpl implements ExerciseDAO {
    @Override
    public ArrayList<Exercise> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from exercise");

        ArrayList<Exercise> exercises = new ArrayList<>();

        while (rst.next()){
            Exercise exerciseDto = new Exercise(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            exercises.add(exerciseDto);
        }
        return exercises;
    }

    @Override
    public boolean save(Exercise entity) {
        return false;
    }

    @Override
    public boolean update(Exercise entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException {
        return "";
    }

    @Override
    public Exercise search(String id) throws Exception {
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }
}
