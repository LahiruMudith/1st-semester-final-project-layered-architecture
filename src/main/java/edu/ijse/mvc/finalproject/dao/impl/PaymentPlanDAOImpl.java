package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.PaymentDAO;
import edu.ijse.mvc.finalproject.dao.PaymentPlanDAO;
import edu.ijse.mvc.finalproject.entity.Payment;
import edu.ijse.mvc.finalproject.entity.PaymentPlan;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentPlanDAOImpl implements PaymentPlanDAO {
    @Override
    public ArrayList<PaymentPlan> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from payment_plan");
        ArrayList<PaymentPlan> list = new ArrayList<>();

        while (resultSet.next()){
            PaymentPlan paymentDto = new PaymentPlan(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3)
            );
            list.add(paymentDto);
        }
        return list;
    }

    @Override
    public boolean save(PaymentPlan entity) {
        return false;
    }

    @Override
    public boolean update(PaymentPlan entity) {
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
    public PaymentPlan search(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("select * from payment_plan where plan_id = ?", id);

        if (rst.next()){
            PaymentPlan paymentPlan = new PaymentPlan(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3)
            );
            return paymentPlan;
        }
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }
}
