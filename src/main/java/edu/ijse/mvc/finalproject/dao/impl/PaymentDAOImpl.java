package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.PaymentDAO;
import edu.ijse.mvc.finalproject.dto.PaymentPlanDto;
import edu.ijse.mvc.finalproject.entity.Payment;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<Payment> getAll() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from payment");
        ArrayList<Payment> list = new ArrayList<>();

        while (resultSet.next()){
            Payment paymentDto = new Payment(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3)
            );
            list.add(paymentDto);
        }
        return list;
    }

    @Override
    public boolean save(Payment entity) {
        return false;
    }

    @Override
    public boolean update(Payment entity) {
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
}
