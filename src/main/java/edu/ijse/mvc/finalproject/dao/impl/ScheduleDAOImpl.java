package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.ScheduleDAO;
import edu.ijse.mvc.finalproject.entity.Schedule;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScheduleDAOImpl implements ScheduleDAO {
    @Override
    public ArrayList<Schedule> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from schedule");
        ArrayList<Schedule> list = new ArrayList<>();

        while (resultSet.next()){
            Schedule schedule = new Schedule(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
            list.add(schedule);
        }
        return list;
    }

    @Override
    public boolean save(Schedule entity) {
        return false;
    }

    @Override
    public boolean update(Schedule entity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public String generateNewId() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select schedule_id from schedule order by schedule_id desc limit 1");

        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(3);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("SCH%03d", newIdIndex);
        }
        return null;
    }
}
