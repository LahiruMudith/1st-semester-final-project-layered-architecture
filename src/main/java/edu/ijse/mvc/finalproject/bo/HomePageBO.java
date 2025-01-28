package edu.ijse.mvc.finalproject.bo;

import edu.ijse.mvc.finalproject.db.DBConnection;
import edu.ijse.mvc.finalproject.dto.MemberDto;
import edu.ijse.mvc.finalproject.dto.PaymentDetailDto;
import edu.ijse.mvc.finalproject.dto.tm.PaymentDetailTM;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface HomePageBO extends SuperBO{
    String generateNewId() throws SQLException;

    boolean markFee(PaymentDetailDto paymentDetailDto);

    MemberDto searchMember(String id) throws Exception;

    String getPlanId(String text) throws Exception;

    ArrayList<PaymentDetailTM> loadTable(String text) throws Exception;
}
