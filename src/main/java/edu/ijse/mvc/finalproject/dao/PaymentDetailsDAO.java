package edu.ijse.mvc.finalproject.dao;

import edu.ijse.mvc.finalproject.entity.PaymentDetail;

import java.util.ArrayList;

public interface PaymentDetailsDAO extends CrudDAO<PaymentDetail> {
    ArrayList<PaymentDetail> getPaymentDetails(String id) throws Exception;
}
