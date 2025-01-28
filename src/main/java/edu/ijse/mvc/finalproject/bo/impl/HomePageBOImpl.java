package edu.ijse.mvc.finalproject.bo.impl;

import edu.ijse.mvc.finalproject.bo.HomePageBO;
import edu.ijse.mvc.finalproject.dao.*;
import edu.ijse.mvc.finalproject.dao.impl.PaymentDAOImpl;
import edu.ijse.mvc.finalproject.db.DBConnection;
import edu.ijse.mvc.finalproject.dto.MemberDto;
import edu.ijse.mvc.finalproject.dto.PaymentDetailDto;
import edu.ijse.mvc.finalproject.entity.Member;
import edu.ijse.mvc.finalproject.entity.Payment;
import edu.ijse.mvc.finalproject.entity.PaymentDetail;
import edu.ijse.mvc.finalproject.entity.PaymentPlan;
import edu.ijse.mvc.finalproject.util.CrudUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class HomePageBOImpl implements HomePageBO {
    PaymentDAO paymentDAO = (PaymentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);
    PaymentDetailsDAO paymentDetailsDAO = (PaymentDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT_DETAILS);
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MEMBER);


    @Override
    public String generateNewId() throws SQLException {
        return paymentDAO.generateNewId();
    }

    @Override
    public boolean markFee(PaymentDetailDto paymentDetailDto) {
        Connection connection = null;
        try{
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isSavedPaymentTable = paymentDAO.save(new Payment(
                    paymentDetailDto.getPayment_id(),
                    paymentDetailDto.getPayment_date(),
                    paymentDetailDto.getAdmin_id()
            ));
            if (!isSavedPaymentTable){
                connection.rollback();
                return false;
            }

            boolean isSavedPaymentDetailsTable = paymentDetailsDAO.save(new PaymentDetail(
                    paymentDetailDto.getPayment_id(),
                    paymentDetailDto.getMember_id(),
                    paymentDetailDto.getMember_name(),
                    paymentDetailDto.getPayment_date(),
                    paymentDetailDto.getPrice(),
                    paymentDetailDto.getPayment_method(),
                    paymentDetailDto.getMonth()
            ));
            if (!isSavedPaymentDetailsTable){
                connection.rollback();
                return false;
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MemberDto searchMember(String id) throws Exception {
        Member member = memberDAO.search(id);
        return new MemberDto(
                member.getMember_id(),
                member.getName(),
                member.getAddress(),
                member.getPhone_number(),
                member.getEmail(),
                member.getRegister_date(),
                member.getWeight(),
                member.getHeight(),
                member.getSchedule_id(),
                member.getPlan_id(),
                member.getDiet_plan_id()
        );
    }
}
