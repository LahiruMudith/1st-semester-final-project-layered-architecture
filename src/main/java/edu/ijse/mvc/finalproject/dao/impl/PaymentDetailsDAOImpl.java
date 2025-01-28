package edu.ijse.mvc.finalproject.dao.impl;

import edu.ijse.mvc.finalproject.dao.PaymentDetailsDAO;
import edu.ijse.mvc.finalproject.dto.tm.PaymentDetailTM;
import edu.ijse.mvc.finalproject.entity.PaymentDetail;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDetailsDAOImpl implements PaymentDetailsDAO {
    @Override
    public ArrayList<PaymentDetail> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(PaymentDetail entity) throws SQLException {
        return CrudUtil.execute("insert into paymentdetail values (?,?,?,?,?,?,?)",
                entity.getPayment_id(),
                entity.getMember_id(),
                entity.getMember_name(),
                entity.getPayment_date(),
                entity.getPrice(),
                entity.getPayment_method(),
                entity.getMonth()
        );
    }

    @Override
    public boolean update(PaymentDetail entity) {
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
    public PaymentDetail search(String id) throws Exception {
        return null;
    }

    @Override
    public int getCount() throws SQLException, ClassNotFoundException {
        return 0;
    }

    @Override
    public ArrayList<PaymentDetail> getPaymentDetails(String id) throws Exception {
        ResultSet rst = CrudUtil.execute("select * from paymentdetail where member_id = ?", id);

        ArrayList<PaymentDetail> paymentDetails = new ArrayList<>();
        while (rst.next()){
            PaymentDetail paymentDetailTM = new PaymentDetail(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getDouble(5),
                    rst.getString(6),
                    rst.getString(7)
            );
            paymentDetails.add(paymentDetailTM);
        }
        return paymentDetails;
    }
}
