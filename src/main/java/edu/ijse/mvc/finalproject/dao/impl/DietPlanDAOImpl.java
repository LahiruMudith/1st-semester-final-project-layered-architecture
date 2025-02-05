package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.DietPlanDAO;
import edu.ijse.mvc.finalproject.entity.DietPlan;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DietPlanDAOImpl implements DietPlanDAO {
    @Override
    public ArrayList<DietPlan> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from diet_plan");

        ArrayList<DietPlan> dietPlans = new ArrayList<>();
        while (rst.next()){
            DietPlan dietPlan = new DietPlan(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
            dietPlans.add(dietPlan);
        }
        System.out.println("DietPlanDAOImpl.getAll: " + dietPlans);
        return dietPlans;
    }

    @Override
    public boolean save(DietPlan entity) {
        return CrudUtil.execute("insert into diet_plan values (?,?,?,?,?)",
                entity.getDiet_plan_id(),
                entity.getAdmin_id(),
                entity.getName(),
                entity.getDuration(),
                entity.getDescription()
        );
    }

    @Override
    public boolean update(DietPlan entity) {
        return CrudUtil.execute("update diet_plan set admin_id=?, name=?, duration=?, description=? WHERE diet_plan_id=?",
                entity.getAdmin_id(),
                entity.getName(),
                entity.getDuration(),
                entity.getDescription(),
                entity.getDiet_plan_id()
        );
    }

    @Override
    public boolean delete(String id) {
        return CrudUtil.execute("delete from diet_plan where diet_plan_id = ?", id);
    }

    @Override
    public String generateNewId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select diet_plan_id from diet_plan order by diet_plan_id desc limit 1");


        if (rst.next()){
            String lastId = rst.getString(1);
            String substring = lastId.substring(2);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("DP%03d", newIdIndex);
        }
        return null;
    }

    @Override
    public DietPlan search(String id) throws Exception {
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.execute("select count(diet_plan_id) from diet_plan");

        int count = 0;
        while (rst.next()){
            count = rst.getInt(1);
        }
        return count;
    }
}
