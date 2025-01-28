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
        return CrudUtil.execute("insert into payment values (?,?,?)",
                entity.getPayment_id(),
                entity.getPayment_date(),
                entity.getAdmin_id()
        );
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
        ResultSet resultSet = CrudUtil.execute("select payment_id from payment order by payment_id desc limit 1");

        if (resultSet.next()){
            String lastId = resultSet.getString(1);
            String substring = lastId.substring(1);
            int i = Integer.parseInt(substring);
            int newIdIndex = i + 1;
            return String.format("P%03d", newIdIndex);
        }
        return null;
    }

    @Override
    public Payment search(String id) throws Exception {
        return null;
    }
}
