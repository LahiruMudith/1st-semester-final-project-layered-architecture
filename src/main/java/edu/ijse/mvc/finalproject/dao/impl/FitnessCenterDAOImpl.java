package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.FitnessCenterDAO;
import edu.ijse.mvc.finalproject.dto.FitnessCenterDto;
import edu.ijse.mvc.finalproject.entity.FitnessCenter;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FitnessCenterDAOImpl implements FitnessCenterDAO {
    @Override
    public ArrayList<FitnessCenter> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from fitness_center");
        ArrayList<FitnessCenter> list = new ArrayList<>();

        while (resultSet.next()){
            FitnessCenter centerDto = new FitnessCenter(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            list.add(centerDto);
        }
        return list;
    }

    @Override
    public boolean save(FitnessCenter entity) {
        return false;
    }

    @Override
    public boolean update(FitnessCenter entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String generateNewId() {
        return "";
    }
}
